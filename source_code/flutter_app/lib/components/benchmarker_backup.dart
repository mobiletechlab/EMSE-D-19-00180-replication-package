import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';

class BenchmarkerComponent extends StatefulWidget {
  final Function _onExecuteBenchmarkClicked;
  final String _featureName;
  final String _featureText;

  // Constructor
  BenchmarkerComponent(
      this._featureName, this._featureText, this._onExecuteBenchmarkClicked,
      {Key key})
      : super(key: key);

  @override
  _BenchmarkerComponentState createState() => new _BenchmarkerComponentState(
      _featureName, _featureText, _onExecuteBenchmarkClicked);
}

class _BenchmarkerComponentState extends State<BenchmarkerComponent> {
  int _sliderValue = 1;
  List<int> _benchmarkResults = [];
  List<String> _logs = [];

  final Function _onExecuteBenchmarkClicked;
  final String _featureName;
  final String _featureText;

  // Constructor
  _BenchmarkerComponentState(this._featureName, this._featureText,
      this._onExecuteBenchmarkClicked);

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.fromLTRB(25.0, 25.0, 25.0, 5.0),
      child: Column(
        mainAxisSize: MainAxisSize.min,
        children: <Widget>[
          Column(
            children: <Widget>[
              Text(
                "$_featureText. Please specify test parameters.",
                overflow: TextOverflow.clip,
              ),
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
                onPressed: () => _onExecuteBenchmarkClicked(_sliderValue, _benchmarkResults, _logs),
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
    );
  }

  void _onIterationSliderChanged(sliderValue) {
    setState(() {
      _sliderValue = sliderValue.floor();
    });
  }
}
