import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { SQLite, SQLiteObject } from '@ionic-native/sqlite';
import * as randomBytes from 'random-bytes';
import BenchmarkItem from '../../models/BenchmarkItem';
import { resolveDefinition } from '../../../node_modules/@angular/core/src/view/util';

@Component({
  selector: 'page-database',
  templateUrl: 'database.html',
})
export class DatabasePage {

  private message = "";
  private DATABASE_NAME: string = "ionic_sqlite.db";
  private DATABASE_LOCATION: string = "default";
  private DATABASE_128_RUNS: number = 5000;
  private DATABASE_2048_RUNS: number = 1000;
  private dataset128: number[] = [];
  private dataset2048: number[] = [];

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

  private DATABASE_CONNECTION: SQLiteObject;

  constructor(public navCtrl: NavController, public navParams: NavParams, private sqlite: SQLite) {
    this.openDatabase();
  }

  generateDatasets() {

    this.message = "Generating data. Please wait...";

    setTimeout(() => {
      for (let i = 0; i <= this.DATABASE_128_RUNS; i++) {
        this.dataset128[i] = randomBytes.sync(128);
      }

      for (let i = 0; i <= this.DATABASE_2048_RUNS; i++) {
        this.dataset2048[i] = randomBytes.sync(2048);
      }

      console.log(this.dataset2048[987]);

      this.message = "Done generating data.";
    }, 10);
  }

  openDatabase(): void {
    this.sqlite.create({
      name: this.DATABASE_NAME,
      location: this.DATABASE_LOCATION,

    })
      .then((db: SQLiteObject) => {
        this.DATABASE_CONNECTION = db;
        console.log(db);
      })
      .catch((error) => {
        console.error(error);
      });
  }

  setupDatabaseTables(): void {
    Promise.all(
      [
        this.DATABASE_CONNECTION.executeSql(this.SQL_CREATE_SCHEMA_128, []),
        this.DATABASE_CONNECTION.executeSql(this.SQL_CREATE_SCHEMA_2048, [])
      ]
    ).then(res => console.log(res)).catch(err => console.error('err', err))
  }

  resetDatabase() {
    Promise.all(
      [
        this.DATABASE_CONNECTION.executeSql(`DROP TABLE IF EXISTS ${this.DATABASE_TABLES.Benchmark128.TABLE_NAME}`, []),
        this.DATABASE_CONNECTION.executeSql(`DROP TABLE IF EXISTS ${this.DATABASE_TABLES.Benchmark2048.TABLE_NAME}`, [])
      ]
    ).then(() => {
      alert("Database reset");
    }).catch((error) => {
      alert(`Error: ${JSON.stringify(error)}`);
    })

  }

  readDataTask() {
    return Promise.all(
      [
        this.DATABASE_CONNECTION.executeSql(`SELECT * FROM ${this.DATABASE_TABLES.Benchmark128.TABLE_NAME}`, []),
        this.DATABASE_CONNECTION.executeSql(`SELECT * FROM ${this.DATABASE_TABLES.Benchmark2048.TABLE_NAME}`, [])
      ]
    );
  }

  writeDataTask() {
    return new Promise((resolve, reject) => {
      let promises = [];

      for (let i = 0; i < this.DATABASE_128_RUNS; i++) {
        promises.push(this.DATABASE_CONNECTION.executeSql(`INSERT INTO ${this.DATABASE_TABLES.Benchmark128.TABLE_NAME} (${this.DATABASE_TABLES.Benchmark128.COLUMN_NAME_KEY}, ${this.DATABASE_TABLES.Benchmark128.COLUMN_NAME_VALUE}) VALUES (?,?)`, [i, this.dataset128[i]]));
      }

      for (let i = 0; i < this.DATABASE_2048_RUNS; i++) {
        promises.push(this.DATABASE_CONNECTION.executeSql(`INSERT INTO ${this.DATABASE_TABLES.Benchmark2048.TABLE_NAME} (${this.DATABASE_TABLES.Benchmark2048.COLUMN_NAME_KEY}, ${this.DATABASE_TABLES.Benchmark2048.COLUMN_NAME_VALUE}) VALUES (?,?)`, [i, this.dataset2048[i]]));
      }

      Promise.all(promises)
        .then((result) => {
          this.readDataTask()
            .then(() => {
              console.log('***** Done reading and writing data ******');
              resolve();
            });
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
        currentRun.completionTime = new Date();
        currentRun.resultValue = `Run ${currentRun.runNumber} completed (data inserted)`;
        observer.next(currentRun);
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
