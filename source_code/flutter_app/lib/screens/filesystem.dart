import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_app/utils/file_benchmark_b64.dart';
import 'package:sensors/sensors.dart';
import 'dart:io';
import 'package:path_provider/path_provider.dart';
import 'package:simple_permissions/simple_permissions.dart';
import '../components/benchmarker.dart';
import '../utils/logFormatter.dart';

class FilesystemScreen extends StatefulWidget {
  @override
  _FilesystemScreenState createState() => new _FilesystemScreenState();
}

class _FilesystemScreenState extends State<FilesystemScreen> {
  @override
  initState() {
    super.initState();
    SimplePermissions
        .requestPermission(Permission.WriteExternalStorage)
        .then((res) => print(res.toString()))
        .catchError((error) => print(error.toString()));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Filesystem"),
        ),
        body: Column(children: <Widget>[
          Padding(padding: const EdgeInsets.all(5.0)),
          MaterialButton(
            color: Color(0xD0D2D1),
            elevation: 1.0,
            minWidth: 300.0,
            child: const Text("(1) SAVE TEST FILE TO DEVICE"),
            onPressed: () => this._saveTestFileToDevice(),
          ),
          BenchmarkerComponent(
              "Filesystem",
              "Fetch a file from the device's file system.",
              _onExecuteBenchmarkClicked),
        ]));
  }

  void _saveTestFileToDevice() async {
    final directory = await getApplicationDocumentsDirectory();
    final path = directory.path;
    FileBenchmark fileBenchmark = new FileBenchmark();

    File file = File('$path/flutter_test_file.png');
    file.writeAsBytes(base64.decode(fileBenchmark.b64)).whenComplete(() {
      print("Saved");
      print(file.path);
    });
  }

  Future _readTestFileFromDevice() async {
    final directory = await getApplicationDocumentsDirectory();
    final path = directory.path;

    File file = File('$path/flutter_test_file.png');
    print("inside read");
    return file.readAsBytes();
  }

  void _onExecuteBenchmarkClicked(int numberOfBenchmarkExecutions,
      List<int> _benchmarkResults, List<String> _logs) async {
    setState(() {
      _logs.add(logFormatter("FILESYSTEM Benchmark started"));
    });

    final hackyAsyncPlaceholder = List.filled(numberOfBenchmarkExecutions, 0);

    int counter = 0;

    await Future.forEach(hackyAsyncPlaceholder, (el) async {
      _logs.add(logFormatter("Run $counter started"));
      Stopwatch stopwatch = new Stopwatch()..start();
      print("before read");
      var a = await this._readTestFileFromDevice();
      print(a.toString().substring(0, 10));
      print("after read");
      int timeElapsedMs = stopwatch.elapsedMilliseconds;
      setState(() {
        _logs.add(logFormatter("Run $counter completed"));
        _benchmarkResults.add(timeElapsedMs);
      });
      counter += 1;
    }).whenComplete(() {
      setState(() {
        _logs.add(logFormatter(
            "FILESYSTEM Benchmark completed (avg time: ${_benchmarkResults.reduce((val, acc) => val+acc)/_benchmarkResults.length}ms)"));
      });
    });
  }
}
