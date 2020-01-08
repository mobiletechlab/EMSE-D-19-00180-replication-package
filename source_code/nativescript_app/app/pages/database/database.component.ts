import { Component, OnInit } from "@angular/core";
import BenchmarkItem from "../../models/BenchmarkItem";
const randomBytes = require('nativescript-randombytes');
const Sqlite = require("nativescript-sqlite");

let DATABASE_CONNECTION: any;

//const globalAny:any = global;

@Component({
    selector: "ns-database",
    moduleId: module.id,
    templateUrl: "./database.component.html",
})
export class DatabaseComponent implements OnInit {
    //items: Item[];

    private DATABASE_NAME: string = "nativescript_sqlite.db";
    private DATABASE_LOCATION: string = "default";
    private DATABASE_128_RUNS: number = 5000;
    private DATABASE_2048_RUNS: number = 1000;
    private dataset128: number[] = [];
    private dataset2048: number[] = [];

    private message = "";

    private DATABASE_TABLES: {
        Benchmark128: {
            TABLE_NAME: string,
            COLUMN_NAME_KEY: string,
            COLUMN_NAME_VALUE: string
        },
        Benchmark2048: {
            TABLE_NAME: string,
            COLUMN_NAME_KEY: string,
            COLUMN_NAME_VALUE: string
        }
    } = {
            Benchmark128: {
                TABLE_NAME: 'Benchmark128',
                COLUMN_NAME_KEY: 'key',
                COLUMN_NAME_VALUE: 'value'
            },
            Benchmark2048: {
                TABLE_NAME: 'Benchmark2048',
                COLUMN_NAME_KEY: 'key',
                COLUMN_NAME_VALUE: 'value'
            }
        };

    private SQL_CREATE_SCHEMA_128: string = `CREATE TABLE IF NOT EXISTS ${this.DATABASE_TABLES.Benchmark128.TABLE_NAME} ( ${this.DATABASE_TABLES.Benchmark128.COLUMN_NAME_KEY} INTEGER PRIMARY KEY, ${this.DATABASE_TABLES.Benchmark128.COLUMN_NAME_VALUE} BLOB )`;

    private SQL_CREATE_SCHEMA_2048: string = `CREATE TABLE IF NOT EXISTS ${this.DATABASE_TABLES.Benchmark2048.TABLE_NAME} ( ${this.DATABASE_TABLES.Benchmark2048.COLUMN_NAME_KEY} INTEGER PRIMARY KEY, ${this.DATABASE_TABLES.Benchmark2048.COLUMN_NAME_VALUE} BLOB )`;

    constructor() {
    }

    ngOnInit(): void {
    }

    generateDatasets() {
        (global as any).global.Buffer = (global as any).global.Buffer || require('buffer').Buffer;

        this.message = "Started generating, please wait...";

        setTimeout(() => {
            for (let i = 0; i <= this.DATABASE_128_RUNS; i++) {
                this.dataset128[i] = randomBytes(128);
            }

            for (let i = 0; i <= this.DATABASE_2048_RUNS; i++) {
                this.dataset2048[i] = randomBytes(2048);
            }

            this.message = "Done generating!";
        }, 10);


    }

    openDatabase(args: any): void {
        new Sqlite(this.DATABASE_NAME, function (err, db) {
            if (err) console.error(err);
            DATABASE_CONNECTION = db;
        });
    }

    resetDatabase() {
        Promise.all(
            [
                DATABASE_CONNECTION.execSQL(`DROP TABLE IF EXISTS ${this.DATABASE_TABLES.Benchmark128.TABLE_NAME}`, []),
                DATABASE_CONNECTION.execSQL(`DROP TABLE IF EXISTS ${this.DATABASE_TABLES.Benchmark2048.TABLE_NAME}`, [])
            ]
        ).then(() => {
            alert("Database reset.");
        }).catch((error) => {
            alert(`Error: ${JSON.stringify(error)}`);
        })

    }

    setupDatabaseTables(): void {
        Promise.all(
            [
                DATABASE_CONNECTION.execSQL(this.SQL_CREATE_SCHEMA_128, []),
                DATABASE_CONNECTION.execSQL(this.SQL_CREATE_SCHEMA_2048, [])
            ]
        ).then(res => alert("Database setup complete.")).catch(err => console.error('err', err))
    }

    readDataTask() {
        return Promise.all(
            [
                DATABASE_CONNECTION.all(`SELECT * FROM ${this.DATABASE_TABLES.Benchmark128.TABLE_NAME}`, []),
                DATABASE_CONNECTION.all(`SELECT * FROM ${this.DATABASE_TABLES.Benchmark2048.TABLE_NAME}`, [])
            ]
        );
    }

    writeDataTask() {
        return new Promise((resolve, reject) => {
            let promises = [];

            for (let i = 0; i <= this.DATABASE_128_RUNS; i++) {
                promises.push(DATABASE_CONNECTION.execSQL(`INSERT INTO ${this.DATABASE_TABLES.Benchmark128.TABLE_NAME} (${this.DATABASE_TABLES.Benchmark128.COLUMN_NAME_KEY}, ${this.DATABASE_TABLES.Benchmark128.COLUMN_NAME_VALUE}) VALUES (?,?)`, [i, this.dataset128[i]]));
            }

            for (let i = 0; i <= this.DATABASE_2048_RUNS; i++) {
                promises.push(DATABASE_CONNECTION.execSQL(`INSERT INTO ${this.DATABASE_TABLES.Benchmark2048.TABLE_NAME} (${this.DATABASE_TABLES.Benchmark2048.COLUMN_NAME_KEY}, ${this.DATABASE_TABLES.Benchmark2048.COLUMN_NAME_VALUE}) VALUES (?,?)`, [i, this.dataset2048[i]]));
            }

            Promise.all(promises)
                .then((result) => {
                    console.log('Done writing data');
                    resolve();
                })
                .catch((error) => {
                    console.log(error);
                    reject();
                });
        });
    }

    runTask(
        { observer, currentRun, }: { observer: any, currentRun: BenchmarkItem }
    ): void {
        currentRun.startTime = new Date();
        this.writeDataTask()
            .then(() => {
                this.readDataTask()
                    .then(() => {
                        currentRun.completionTime = new Date();
                        currentRun.resultValue = `Run ${currentRun.runNumber} completed (data inserted and read)`;
                        observer.next(currentRun);
                    });
            })
            .catch((error) => {
                console.error(error);
                currentRun.completionTime = new Date();
                currentRun.resultValue = `Run ${currentRun.runNumber} errored`;
                currentRun.error = true;
                observer.next(currentRun);
            });
    }
}