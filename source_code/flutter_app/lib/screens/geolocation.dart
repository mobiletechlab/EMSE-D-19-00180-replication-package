import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:geolocation/geolocation.dart';
import 'package:simple_permissions/simple_permissions.dart';

import '../components/button.dart';
import '../components/benchmarker.dart';

import '../utils/logFormatter.dart';

class GeolocationScreen extends StatefulWidget {
  @override
  _GeolocationScreenState createState() => new _GeolocationScreenState();
}

class _GeolocationScreenState extends State<GeolocationScreen> {
  @override
  initState() {
    super.initState();
    SimplePermissions
        .requestPermission(Permission.AccessFineLocation)
        .then((res) => print(res.toString()))
        .catchError((error) => print(error.toString()));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Geolocation"),
        ),
        body: new BenchmarkerComponent(
            "Geolocation",
            "Fetch the current Geolocation coordinates from device.",
            _onExecuteBenchmarkClicked));
  }

  void _onExecuteBenchmarkClicked(int numberOfBenchmarkExecutions,
      List<int> _benchmarkResults, List<String> _logs) async {
    setState(() {
      _logs.add(logFormatter("Geolocation Benchmark started"));
    });

    final hackyAsyncPlaceholder = List.filled(numberOfBenchmarkExecutions, 0);

    int counter = 0;

    await Future.forEach(hackyAsyncPlaceholder, (el) async {
      _logs.add(logFormatter("Run $counter started"));
      Stopwatch stopwatch = new Stopwatch()..start();
      var val = await Geolocation
          .currentLocation(accuracy: LocationAccuracy.best)
          .first;
      int timeElapsedMs = stopwatch.elapsedMilliseconds;
      setState(() {
        _logs.add(logFormatter("Run $counter completed ($val)"));
        _benchmarkResults.add(timeElapsedMs);
      });
      counter += 1;
    }).whenComplete(() {
      setState(() {
        _logs.add(logFormatter(
            "GEOLOCATION Benchmark completed (avg time: ${_benchmarkResults.reduce((val, acc) => val+acc)/_benchmarkResults.length}ms)"));
      });
    });

    /* for (var i = 0; i < numberOfBenchmarkExecutions; i++) {
      _logs.add(logFormatter("Run $i started"));

      Stopwatch stopwatch = new Stopwatch()..start();
      await Geolocation
          .currentLocation(accuracy: LocationAccuracy.best)
          .first
          .then((GeolocationResult result) {
        int timeElapsedMs = stopwatch.elapsedMilliseconds;
        if (result.isSuccessful) {
          setState(() {
            _logs.add(logFormatter("Run $i completed (result: $result) "));
            _benchmarkResults.add(timeElapsedMs);
          });
        }
      });

    }

    print(_benchmarkResults);

    setState(() {
      _logs.add(logFormatter(
          "Geolocation Benchmark completed (avg time: ${_benchmarkResults.reduce((val, acc) => val+acc)/_benchmarkResults.length}ms)"));
    }); */
  }
}
