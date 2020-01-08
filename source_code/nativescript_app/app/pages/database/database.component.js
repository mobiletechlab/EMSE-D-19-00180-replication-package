"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var randomBytes = require('nativescript-randombytes');
var Sqlite = require("nativescript-sqlite");
var DATABASE_CONNECTION;
//const globalAny:any = global;
var DatabaseComponent = /** @class */ (function () {
    function DatabaseComponent() {
        //items: Item[];
        this.DATABASE_NAME = "nativescript_sqlite.db";
        this.DATABASE_LOCATION = "default";
        this.DATABASE_128_RUNS = 5000;
        this.DATABASE_2048_RUNS = 1000;
        this.dataset128 = [];
        this.dataset2048 = [];
        this.message = "";
        this.DATABASE_TABLES = {
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
        this.SQL_CREATE_SCHEMA_128 = "CREATE TABLE IF NOT EXISTS " + this.DATABASE_TABLES.Benchmark128.TABLE_NAME + " ( " + this.DATABASE_TABLES.Benchmark128.COLUMN_NAME_KEY + " INTEGER PRIMARY KEY, " + this.DATABASE_TABLES.Benchmark128.COLUMN_NAME_VALUE + " BLOB )";
        this.SQL_CREATE_SCHEMA_2048 = "CREATE TABLE IF NOT EXISTS " + this.DATABASE_TABLES.Benchmark2048.TABLE_NAME + " ( " + this.DATABASE_TABLES.Benchmark2048.COLUMN_NAME_KEY + " INTEGER PRIMARY KEY, " + this.DATABASE_TABLES.Benchmark2048.COLUMN_NAME_VALUE + " BLOB )";
    }
    DatabaseComponent.prototype.ngOnInit = function () {
    };
    DatabaseComponent.prototype.generateDatasets = function () {
        var _this = this;
        global.global.Buffer = global.global.Buffer || require('buffer').Buffer;
        this.message = "Started generating, please wait...";
        setTimeout(function () {
            for (var i = 0; i <= _this.DATABASE_128_RUNS; i++) {
                _this.dataset128[i] = randomBytes(128);
            }
            for (var i = 0; i <= _this.DATABASE_2048_RUNS; i++) {
                _this.dataset2048[i] = randomBytes(2048);
            }
            _this.message = "Done generating!";
        }, 10);
    };
    DatabaseComponent.prototype.openDatabase = function (args) {
        new Sqlite(this.DATABASE_NAME, function (err, db) {
            if (err)
                console.error(err);
            DATABASE_CONNECTION = db;
        });
    };
    DatabaseComponent.prototype.resetDatabase = function () {
        Promise.all([
            DATABASE_CONNECTION.execSQL("DROP TABLE IF EXISTS " + this.DATABASE_TABLES.Benchmark128.TABLE_NAME, []),
            DATABASE_CONNECTION.execSQL("DROP TABLE IF EXISTS " + this.DATABASE_TABLES.Benchmark2048.TABLE_NAME, [])
        ]).then(function () {
            alert("Database reset.");
        }).catch(function (error) {
            alert("Error: " + JSON.stringify(error));
        });
    };
    DatabaseComponent.prototype.setupDatabaseTables = function () {
        Promise.all([
            DATABASE_CONNECTION.execSQL(this.SQL_CREATE_SCHEMA_128, []),
            DATABASE_CONNECTION.execSQL(this.SQL_CREATE_SCHEMA_2048, [])
        ]).then(function (res) { return alert("Database setup complete."); }).catch(function (err) { return console.error('err', err); });
    };
    DatabaseComponent.prototype.readDataTask = function () {
        return Promise.all([
            DATABASE_CONNECTION.all("SELECT * FROM " + this.DATABASE_TABLES.Benchmark128.TABLE_NAME, []),
            DATABASE_CONNECTION.all("SELECT * FROM " + this.DATABASE_TABLES.Benchmark2048.TABLE_NAME, [])
        ]);
    };
    DatabaseComponent.prototype.writeDataTask = function () {
        var _this = this;
        return new Promise(function (resolve, reject) {
            var promises = [];
            for (var i = 0; i <= _this.DATABASE_128_RUNS; i++) {
                promises.push(DATABASE_CONNECTION.execSQL("INSERT INTO " + _this.DATABASE_TABLES.Benchmark128.TABLE_NAME + " (" + _this.DATABASE_TABLES.Benchmark128.COLUMN_NAME_KEY + ", " + _this.DATABASE_TABLES.Benchmark128.COLUMN_NAME_VALUE + ") VALUES (?,?)", [i, _this.dataset128[i]]));
            }
            for (var i = 0; i <= _this.DATABASE_2048_RUNS; i++) {
                promises.push(DATABASE_CONNECTION.execSQL("INSERT INTO " + _this.DATABASE_TABLES.Benchmark2048.TABLE_NAME + " (" + _this.DATABASE_TABLES.Benchmark2048.COLUMN_NAME_KEY + ", " + _this.DATABASE_TABLES.Benchmark2048.COLUMN_NAME_VALUE + ") VALUES (?,?)", [i, _this.dataset2048[i]]));
            }
            Promise.all(promises)
                .then(function (result) {
                console.log('Done writing data');
                resolve();
            })
                .catch(function (error) {
                console.log(error);
                reject();
            });
        });
    };
    DatabaseComponent.prototype.runTask = function (_a) {
        var _this = this;
        var observer = _a.observer, currentRun = _a.currentRun;
        currentRun.startTime = new Date();
        this.writeDataTask()
            .then(function () {
            _this.readDataTask()
                .then(function () {
                currentRun.completionTime = new Date();
                currentRun.resultValue = "Run " + currentRun.runNumber + " completed (data inserted and read)";
                observer.next(currentRun);
            });
        })
            .catch(function (error) {
            console.error(error);
            currentRun.completionTime = new Date();
            currentRun.resultValue = "Run " + currentRun.runNumber + " errored";
            currentRun.error = true;
            observer.next(currentRun);
        });
    };
    DatabaseComponent = __decorate([
        core_1.Component({
            selector: "ns-database",
            moduleId: module.id,
            templateUrl: "./database.component.html",
        }),
        __metadata("design:paramtypes", [])
    ], DatabaseComponent);
    return DatabaseComponent;
}());
exports.DatabaseComponent = DatabaseComponent;
