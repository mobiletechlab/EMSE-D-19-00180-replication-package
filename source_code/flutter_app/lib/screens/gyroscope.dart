import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:sensors/sensors.dart';

import '../components/button.dart';
import '../components/benchmarker.dart';

import '../utils/logFormatter.dart';

class GyroscopeScreen extends StatefulWidget {
  @override
  _GyroscopeScreenState createState() => new _GyroscopeScreenState();
}

class _GyroscopeScreenState extends State<GyroscopeScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Gyroscope"),
        ),
        body: new BenchmarkerComponent(
            "Gyroscope",
            "Fetch the current Gyroscope levels from device.",
            _onExecuteBenchmarkClicked));
  }

  void _onExecuteBenchmarkClicked(int numberOfBenchmarkExecutions,
      List<int> _benchmarkResults, List<String> _logs) async {
    setState(() {
      _logs.add(logFormatter("GYROSCOPE Benchmark started"));
    });

    for (var i = 0; i < numberOfBenchmarkExecutions; i++) {
      _logs.add(logFormatter("Run $i started"));

      Stopwatch stopwatch = new Stopwatch()..start();
      await gyroscopeEvents.first.then((GyroscopeEvent event) {
        int timeElapsedMs = stopwatch.elapsedMilliseconds;

        setState(() {
          _logs.add(logFormatter("Run $i completed (result: $event) "));
          _benchmarkResults.add(timeElapsedMs);
        });
      });
    }

    print(_benchmarkResults);

    setState(() {
      _logs.add(logFormatter(
          "GYROSCOPE Benchmark completed (avg time: ${_benchmarkResults.reduce((val, acc) => val+acc)/_benchmarkResults.length}ms)"));
    });
  }
}
