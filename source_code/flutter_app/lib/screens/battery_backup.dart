import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'package:battery/battery.dart';

import '../components/button.dart';

class BatteryScreen extends StatefulWidget {
  @override
  _BatteryScreenState createState() => new _BatteryScreenState();
}

class _BatteryScreenState extends State<BatteryScreen> {
  Battery _battery = new Battery();
  int _sliderValue = 1;
  List<String> _logs = [];
  List<int> _benchmarkedTimes = [];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Battery"),
      ),
      body: Container(
        padding: const EdgeInsets.fromLTRB(25.0, 25.0, 25.0, 5.0),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: <Widget>[
            Column(
              children: <Widget>[
                Text("Fetch the current battery levels. Please specify test parameters.", overflow: TextOverflow.clip,),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Padding(padding: const EdgeInsets.all(5.0)),
                    Text("Repetitions"),
                    Slider(
                      label: "Iterations: $_sliderValue",
                      value: _sliderValue.toDouble(),
                      max: 50.0,
                      divisions: 50,
                      min: 1.0,
                      onChanged: _onIterationSliderChanged,
                    ),
                    Text(_sliderValue.toString())
                  ],
                ),
                MaterialButton(
                  child: Text("START BENCHMARK"),
                  color: Color(0xD0D2D1),
                  elevation: 1.0,
                  minWidth: 300.0,
                  onPressed: _onExecuteBenchmarkClicked,
                ),
              ],
            ),
            _logs.length > 0
                ? Flexible(
                    child: ListView(
                        shrinkWrap: true,
                        controller: new ScrollController(),
                        padding: const EdgeInsets.all(20.0),
                        children: _logs.map((log) => Text(log)).toList()))
                : Container(
                    padding: const EdgeInsets.all(20.0),
                    child: Text("Waiting to start..."),
                  ),
          ],
        ),
      ),
    );
  }

  void _onIterationSliderChanged(sliderValue) {
    setState(() {
      _sliderValue = sliderValue.floor();
    });
  }

  void _onExecuteBenchmarkClicked() async {
    setState(() {
      _logs.add(_logFormatter("BATTERYLEVEL Benchmark started"));
    });

    for (var i = 0; i < _sliderValue; i++) {
      _logs.add(_logFormatter("Run $i started"));

      Stopwatch stopwatch = new Stopwatch()..start();
      var batteryLevel = await _battery.batteryLevel;
      int timeElapsedMs = stopwatch.elapsedMilliseconds;

      setState(() {
        _logs.add(
            _logFormatter("Run $i completed (batterylevel: $batteryLevel) "));
        _benchmarkedTimes.add(timeElapsedMs);
      });
    }

    print(_benchmarkedTimes);

    setState(() {
      _logs.add(_logFormatter(
          "BATTERYLEVEL Benchmark completed (avg time: ${_benchmarkedTimes.reduce((val, acc) => val+acc)/_benchmarkedTimes.length}ms)"));
    });
  }

  String _logFormatter(text) {
    var dateTime = DateTime.now();
    return "${dateTime.hour}:${dateTime.minute}:${dateTime.second}:${dateTime.millisecond} - $text";
  }
}
