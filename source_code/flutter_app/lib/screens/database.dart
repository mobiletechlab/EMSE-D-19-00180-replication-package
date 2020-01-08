import 'dart:async';
import 'dart:math';
import 'dart:typed_data';

import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

import '../components/button.dart';
import '../components/benchmarker.dart';

import '../utils/logFormatter.dart';

class DatabaseScreen extends StatefulWidget {
  @override
  _DatabaseScreenState createState() => new _DatabaseScreenState();
}

class _DatabaseScreenState extends State<DatabaseScreen> {
  String _alertMessage = "";
  final String DATABASE_NAME = "flutter_sqlite.db";

  static final String Benchmark128_TABLE_NAME = "Benchmark128";
  static final String Benchmark2048_TABLE_NAME = "Benchmark2048";
  static final int DATABASE_128_RUNS = 1000;
  static final int DATABASE_2048_RUNS = 5000;
  static final String COLUMN_NAME_KEY = 'key';
  static final String COLUMN_NAME_VALUE = 'value';
  static Database DATABASE_CONNECTION;
  List<List<int>> dataset128 = [];
  List<List<int>> dataset2048 = [];

  final String SQL_CREATE_SCHEMA_128 =
      'CREATE TABLE IF NOT EXISTS $Benchmark128_TABLE_NAME ( $COLUMN_NAME_KEY INTEGER PRIMARY KEY, $COLUMN_NAME_VALUE BLOB )';

  final String SQL_CREATE_SCHEMA_2048 =
      'CREATE TABLE IF NOT EXISTS $Benchmark2048_TABLE_NAME ( $COLUMN_NAME_KEY INTEGER PRIMARY KEY, $COLUMN_NAME_VALUE BLOB )';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Database"),
        ),
        body: Column(
          children: <Widget>[
            Padding(padding: const EdgeInsets.all(5.0)),
            MaterialButton(
              color: Color(0xD0D2D1),
              elevation: 1.0,
              minWidth: 300.0,
              child: const Text("(1) GENERATE DATASET"),
              onPressed: () => this._generateDataset(context),
            ),
            MaterialButton(
              color: Color(0xD0D2D1),
              elevation: 1.0,
              minWidth: 300.0,
              child: const Text("(2) OPEN DATABASE"),
              onPressed: () => this._openDatabase(),
            ),
            MaterialButton(
              color: Colors.red,
              elevation: 1.0,
              minWidth: 300.0,
              child: const Text(
                "(3) RESET DATABASE \n (before each new benchmark)",
                style: TextStyle(color: Colors.white),
              ),
              onPressed: () => this._resetDatabase(),
            ),
            MaterialButton(
              color: Color(0xD0D2D1),
              elevation: 1.0,
              minWidth: 300.0,
              child: const Text("(4) SETUP DATABASE"),
              onPressed: () => this._setupDatabaseTables(),
            ),
            Padding(padding: const EdgeInsets.all(5.0)),
            Text(
              _alertMessage,
              style: TextStyle(color: Colors.red),
            ),
            BenchmarkerComponent(
                "Database",
                "IMPORTANT: Use only 1 repetition!! This benchmark will write 128 bytes 5000 times and 2048 bytes 1000 times, then read the data back from SQLite.",
                _onExecuteBenchmarkClicked),
          ],
        ));
  }

  List<int> randomNumberGenereator(int length) {
    var buffer = new Uint8List(length);
    var rng = new Random.secure();
    for (var i = 0; i < length; i++) {
      buffer[i] = rng.nextInt(256);
    }

    return buffer;
  }

  void _generateDataset(BuildContext context) {
    setState(() {
      _alertMessage = "Started generating data -- wait…";
    });

    print("Start of generate");

    new Timer(new Duration(seconds: 1), () {
      for (int i = 0; i < DATABASE_128_RUNS; i++) {
        this.dataset128.add(this.randomNumberGenereator(128));
      }

      for (int i = 0; i < DATABASE_2048_RUNS; i++) {
        this.dataset2048.add(this.randomNumberGenereator(2048));
      }

      print("end of generate");
      setState(() {
        _alertMessage = "Done generating data";
      });
    });
  }

  void _setupDatabaseTables() async {
    try {
      Future
          .wait([
            DATABASE_CONNECTION.execute(SQL_CREATE_SCHEMA_128),
            DATABASE_CONNECTION.execute(SQL_CREATE_SCHEMA_2048)
          ])
          .then((List responses) => print(responses))
          .catchError((e) => print(e.toString()));
    } catch (e) {
      print(e.toString());
    }
  }

  void _resetDatabase() async {
    print(DATABASE_CONNECTION);
    try {
      Future
          .wait([
            DATABASE_CONNECTION
                .execute('DROP TABLE IF EXISTS $Benchmark128_TABLE_NAME'),
            DATABASE_CONNECTION
                .execute('DROP TABLE IF EXISTS $Benchmark2048_TABLE_NAME')
          ])
          .then((List responses) => print(responses))
          .catchError((e) => print(e.toString()));
    } catch (e) {
      print(e.toString());
    }
  }

  dynamic _writeDataTask() async {
    List<Future> promises = [];

    for (int i = 0; i < DATABASE_128_RUNS; i++) {
      promises.add(DATABASE_CONNECTION.rawInsert(
          "INSERT INTO $Benchmark128_TABLE_NAME($COLUMN_NAME_KEY, $COLUMN_NAME_VALUE) VALUES(?,?)",
          [i, this.dataset128[i]]));
    }

    for (int i = 0; i < DATABASE_2048_RUNS; i++) {
      promises.add(DATABASE_CONNECTION.rawInsert(
          "INSERT INTO $Benchmark2048_TABLE_NAME($COLUMN_NAME_KEY, $COLUMN_NAME_VALUE) VALUES(?,?)",
          [i, this.dataset2048[i]]));
    }

    return Future.wait(promises);
  }

  dynamic _readDataTask() async {
    List<Future> list = [];
    list.add(
        DATABASE_CONNECTION.rawQuery("SELECT * FROM $Benchmark128_TABLE_NAME"));
    list.add(DATABASE_CONNECTION
        .rawQuery("SELECT * FROM $Benchmark2048_TABLE_NAME"));

    return Future.wait(list);
  }

  void _openDatabase() async {
    var databasesPath = await getDatabasesPath();
    String path = join(databasesPath, DATABASE_NAME);
    DATABASE_CONNECTION = await openDatabase(path);
  }

  void _onExecuteBenchmarkClicked(int numberOfBenchmarkExecutions,
      List<int> _benchmarkResults, List<String> _logs) async {
    setState(() {
      _logs.add(logFormatter("Database Benchmark started"));
      _alertMessage = "Benchmark started -- could take a while.";
    });

    // Hardcoded to only accept one repetition, avoiding primary key issues
    final hackyAsyncPlaceholder = List.filled(1, 0);

    int counter = 0;

    await Future.forEach(hackyAsyncPlaceholder, (el) async {
      _logs.add(logFormatter("Run $counter started"));
      Stopwatch stopwatch = new Stopwatch()..start();
      await this._writeDataTask();
      await this._readDataTask();
      int timeElapsedMs = stopwatch.elapsedMilliseconds;
      setState(() {
        _logs.add(logFormatter("Run $counter completed"));
        _benchmarkResults.add(timeElapsedMs);
      });
      counter += 1;
    }).whenComplete(() {
      setState(() {
        _logs.add(logFormatter(
            "DATABASE Benchmark completed (avg time: ${_benchmarkResults.reduce((val, acc) => val+acc)/_benchmarkResults.length}ms)"));
      });
    });

    /* new Timer(const Duration(seconds: 1), () async {
      //for (int i = 0; i < numberOfBenchmarkExecutions; i++) {
        _logs.add(logFormatter("Run 1 started"));

        Stopwatch stopwatch = new Stopwatch()..start();

        await this._writeDataTask().then((a) async {
          await this._readDataTask().then((b) {
            int timeElapsedMs = stopwatch.elapsedMilliseconds;
            setState(() {
              _logs.add(logFormatter("Run 1 completed"));
              _benchmarkResults.add(timeElapsedMs);
            });
          }).catchError((err) {
            print("Error in catcherror onexec");
            print(err.toString());
          });
        });
      //}

      setState(() {
        _logs.add(logFormatter(
            "Database Benchmark completed (avg time: ${_benchmarkResults.reduce((val, acc) => val+acc)/_benchmarkResults.length}ms)"));
      });
    }); */
  }
}
