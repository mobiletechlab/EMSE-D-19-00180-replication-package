import React, { Component } from 'react';
import {
    Platform,
    Button,
    StyleSheet,
    Text,
    View,
    StatusBar,
    TouchableOpacity
} from 'react-native';

import whilst from 'async/whilst';

import Benchmarker from '../Components/Benchmarker';

import SQLite from 'react-native-sqlite-storage';
//SQLite.DEBUG(true);
SQLite.enablePromise(true);

import { generateSecureRandom } from 'react-native-securerandom';

const DATABASE_NAME = "rn_sqlite.db";
const DATABASE_LOCATION = "default";
const DATABASE_128_RUNS = 5000;
const DATABASE_2048_RUNS = 1000;
let DATABASE_CONNECTION;
const dataset128 = [];
const dataset2048 = [];

const DATABASE_TABLES = {
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

const SQL_CREATE_SCHEMA_128 = `CREATE TABLE IF NOT EXISTS ${DATABASE_TABLES.Benchmark128.TABLE_NAME} ( ${DATABASE_TABLES.Benchmark128.COLUMN_NAME_KEY} INTEGER PRIMARY KEY, ${DATABASE_TABLES.Benchmark128.COLUMN_NAME_VALUE} BLOB )`;

const SQL_CREATE_SCHEMA_2048 = `CREATE TABLE IF NOT EXISTS ${DATABASE_TABLES.Benchmark2048.TABLE_NAME} ( ${DATABASE_TABLES.Benchmark2048.COLUMN_NAME_KEY} INTEGER PRIMARY KEY, ${DATABASE_TABLES.Benchmark2048.COLUMN_NAME_VALUE} BLOB )`;

export default class DatabaseScreen extends Component {
    static navigationOptions = {
        title: 'Database'
    }

    constructor(props) {
        super(props);

        this.openDatabase();
    }

    generateDatasets() {

        alert('This can take a while. Two alerts will be prompted when finished.')

        let count128 = 0;

        whilst(
            () => count128 < DATABASE_128_RUNS,
            (cb) => {
                generateSecureRandom(128).then((data) => {
                    count128++;
                    dataset128[count128] = data;
                    cb(null, count128);
                });
            },
            (err, n) => {
                if (err) console.log(err);
                alert('Generated 128 bytes times:' + n);
            }
        );

        let count2048 = 0;

        whilst(
            () => count2048 < DATABASE_2048_RUNS,
            (cb) => {
                generateSecureRandom(2048).then((data) => {
                    count2048++;
                    dataset2048[count2048] = data;
                    cb(null, count2048);
                });
            },
            (err, n) => {
                if (err) console.log(err);
                alert('Generated 2048 bytes times:' + n);
            }
        );
    }

    setupDatabaseTables() {
        Promise.all(
            [
                DATABASE_CONNECTION.executeSql(SQL_CREATE_SCHEMA_128, []),
                DATABASE_CONNECTION.executeSql(SQL_CREATE_SCHEMA_2048, [])
            ]
        ).then(res => console.log(res)).catch(err => console.error('err', err))
    }

    openDatabase() {
        console.log(SQLite);
        SQLite.openDatabase({ name: DATABASE_NAME, location: DATABASE_LOCATION })
            .then((db) => {
                DATABASE_CONNECTION = db;
                console.log(db);
            })
            .catch((error) => {
                console.error(error);
            });
    }

    resetDatabase() {
        Promise.all(
            [
                DATABASE_CONNECTION.executeSql(`DROP TABLE IF EXISTS ${DATABASE_TABLES.Benchmark128.TABLE_NAME}`, []),
                DATABASE_CONNECTION.executeSql(`DROP TABLE IF EXISTS ${DATABASE_TABLES.Benchmark2048.TABLE_NAME}`, [])
            ]
        ).then(() => {
            alert("Database reset");
        }).catch((error) => {
            alert(`Error: ${JSON.stringify(error)}`);
        });
    }

    readDataTask() {
        return Promise.all(
            [
                DATABASE_CONNECTION.executeSql(`SELECT * FROM ${DATABASE_TABLES.Benchmark128.TABLE_NAME}`, []),
                DATABASE_CONNECTION.executeSql(`SELECT * FROM ${DATABASE_TABLES.Benchmark2048.TABLE_NAME}`, [])
            ]
        );
    }

    writeDataTask() {
        return new Promise((resolve, reject) => {
            let promises = [];

            for (let i = 0; i < DATABASE_128_RUNS; i++) {
                promises.push(DATABASE_CONNECTION.executeSql(`INSERT INTO ${DATABASE_TABLES.Benchmark128.TABLE_NAME} (${DATABASE_TABLES.Benchmark128.COLUMN_NAME_KEY}, ${DATABASE_TABLES.Benchmark128.COLUMN_NAME_VALUE}) VALUES (?,?)`, [i, dataset128[i]]));
            }

            for (let i = 0; i < DATABASE_2048_RUNS; i++) {
                promises.push(DATABASE_CONNECTION.executeSql(`INSERT INTO ${DATABASE_TABLES.Benchmark2048.TABLE_NAME} (${DATABASE_TABLES.Benchmark2048.COLUMN_NAME_KEY}, ${DATABASE_TABLES.Benchmark2048.COLUMN_NAME_VALUE}) VALUES (?,?)`, [i, dataset2048[i]]));
            }

            Promise.all(promises)
                .then((result) => {
                    this.readDataTask().then(() => {
                        resolve();
                    }).catch((error) => {
                        console.error(error);
                        alert("error");
                        reject();
                    });
                })
                .catch((error) => {
                    console.log(error);
                    alert("ERROR");
                    reject();
                });
        });
    }

    async runTask(currentRun) {
        return new Promise((resolve, reject) => {
            currentRun.startTime = new Date();
            this.writeDataTask()
                .then(() => {
                    currentRun.completionTime = new Date();
                    currentRun.resultValue = `Run ${currentRun.runNumber} completed (data inserted).`;
                    resolve(currentRun);
                })
                .catch((error) => {
                    console.error('Error in runTask in Database');
                    console.error(error);
                    reject(error);
                });
        });
    }

    render() {
        return (
            <View style={styles.container}>
                <StatusBar backgroundColor='#31429D' barStyle='light-content' />
                <Button onPress={this.generateDatasets} title="(1) Generate Datasets" />
                <Button onPress={this.setupDatabaseTables} title="(2) Setup Database" />
                <Button onPress={this.openDatabase} title="(3) Open Database" />
                <Button onPress={this.readDataTask} title="(debug) Read data" />
                <Button color="red" onPress={this.resetDatabase} title="RESET DATABASE" />
                <Benchmarker description="IMPORTANT: Use only 1 repetition!! This benchmark will write 128 bytes 5000 times and 2048 bytes 1000 times, then read the data back from SQLite." feature="Database" task={this.runTask.bind(this)}></Benchmarker>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
        paddingTop: 20,
        paddingLeft: 20,
        paddingRight: 20
    },
    pageList: {
        marginTop: 20,
        alignSelf: 'stretch'
    }
});
