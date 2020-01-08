import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import '../components/button.dart';

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      // 1
      appBar: new AppBar(
        title: new Text("Feature Performance Benchmarking"),
      ),
      body: new Center(
        child: new Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
           /*  new HomePageNavigationButton(
                routeName: '/camera', routeTitle: 'CAMERA'), */
            new HomePageNavigationButton(
                routeName: '/accelerometer', routeTitle: 'ACCELEROMETER'),
            new HomePageNavigationButton(
                routeName: '/filesystem', routeTitle: 'FILESYSTEM'),
            /* new HomePageNavigationButton(
                routeName: '/gyroscope', routeTitle: 'GYROSCOPE'), */
            /* new HomePageNavigationButton(
                routeName: '/battery', routeTitle: 'BATTERY'), */
            new HomePageNavigationButton(
                routeName: '/geolocation', routeTitle: 'GEOLOCATION'),
            new HomePageNavigationButton(
                routeName: '/contacts', routeTitle: 'CONTACTS'),
            new HomePageNavigationButton(
                routeName: '/database', routeTitle: 'DATABASE'),
          ],
        ),
      ),
    );
  }
}
