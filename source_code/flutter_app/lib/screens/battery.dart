import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:battery/battery.dart';
import '../components/benchmarker.dart';
import '../utils/logFormatter.dart';

class BatteryScreen extends StatefulWidget {
  @override
  _BatteryScreenState createState() => new _BatteryScreenState();
}

class _BatteryScreenState extends State<BatteryScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Battery"),
        ),
        body: new BenchmarkerComponent(
            "Battery",
            "Fetch the current battery levels from device.",
            _onExecuteBenchmarkClicked));
  }

  void _onExecuteBenchmarkClicked(int numberOfBenchmarkExecutions,
      List<int> _benchmarkResults, List<String> _logs) async {
    setState(() {
      _logs.add(logFormatter("BATTERYLEVEL Benchmark started"));
    });

    for (var i = 0; i < numberOfBenchmarkExecutions; i++) {
      _logs.add(logFormatter("Run $i started"));

      Stopwatch stopwatch = new Stopwatch()..start();
      var batteryLevel = await new Battery().batteryLevel;
      int timeElapsedMs = stopwatch.elapsedMilliseconds;

      setState(() {
        _logs.add(logFormatter("Run $i completed (result: $batteryLevel%) "));
        _benchmarkResults.add(timeElapsedMs);
      });
    }

    print(_benchmarkResults);

    setState(() {
      _logs.add(logFormatter(
          "BATTERYLEVEL Benchmark completed (avg time: ${_benchmarkResults.reduce((val, acc) => val+acc)/_benchmarkResults.length}ms)"));
    });
  }
}
