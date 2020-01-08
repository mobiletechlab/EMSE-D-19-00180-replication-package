import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:sensors/sensors.dart';

import '../components/button.dart';
import '../components/benchmarker.dart';

import '../utils/logFormatter.dart';

class AccelerometerScreen extends StatefulWidget {
  @override
  _AccelerometerScreenState createState() => new _AccelerometerScreenState();
}

class _AccelerometerScreenState extends State<AccelerometerScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Accelerometer"),
        ),
        body: new BenchmarkerComponent(
            "Accelerometer",
            "Fetch the current accelerometer levels from device.",
            _onExecuteBenchmarkClicked));
  }

  void _onExecuteBenchmarkClicked(int numberOfBenchmarkExecutions,
      List<int> _benchmarkResults, List<String> _logs) async {
    setState(() {
      _logs.add(logFormatter("ACCELEROMETER Benchmark started"));
    });

    /* for (var i = 0; i < numberOfBenchmarkExecutions; i++) {
      _logs.add(logFormatter("Run $i started"));

      Stopwatch stopwatch = new Stopwatch()..start();
      await accelerometerEvents.first.then((AccelerometerEvent event) {
        int timeElapsedMs = stopwatch.elapsedMilliseconds;

        setState(() {
          _logs.add(logFormatter("Run $i completed (result: $event) "));
          _benchmarkResults.add(timeElapsedMs);
        });
      });
    } */

    final hackyAsyncPlaceholder = List.filled(numberOfBenchmarkExecutions, 0);

    int counter = 0;

    await Future.forEach(hackyAsyncPlaceholder, (el) async {
      _logs.add(logFormatter("Run $counter started"));
      Stopwatch stopwatch = new Stopwatch()..start();
      var val = await accelerometerEvents.first;
      int timeElapsedMs = stopwatch.elapsedMilliseconds;
      setState(() {
        _logs.add(logFormatter("Run $counter completed ($val)"));
        _benchmarkResults.add(timeElapsedMs);
      });
      counter += 1;
    }).whenComplete(() {
      setState(() {
        _logs.add(logFormatter(
            "ACCELEROMETER Benchmark completed (avg time: ${_benchmarkResults.reduce((val, acc) => val+acc)/_benchmarkResults.length}ms)"));
      });
    });
  }
}
