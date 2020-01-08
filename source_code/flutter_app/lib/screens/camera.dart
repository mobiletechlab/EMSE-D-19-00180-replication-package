import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import '../components/button.dart';

class CameraScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text("Camera"),
      ),
      body: new Center(
        child: new Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            
          ],
        ),
      ),
    );
  }
}
