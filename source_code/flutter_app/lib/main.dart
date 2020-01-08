import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'screens/contacts.dart';
import 'screens/home.dart';
import 'screens/database.dart';
import 'screens/filesystem.dart';
import 'screens/accelerometer.dart';
import 'screens/geolocation.dart';

void main() {
  runApp(
    new MaterialApp(
      home: new HomeScreen(),
      routes: <String, WidgetBuilder> {
        '/home': (BuildContext context) => new HomeScreen(),
        /* '/camera' : (BuildContext context) => new CameraScreen(), */
        '/accelerometer' : (BuildContext context) => new AccelerometerScreen(),
        '/filesystem' : (BuildContext context) => new FilesystemScreen(),
        '/contacts' : (BuildContext context) => new ContactsScreen(),
        /* '/battery' : (BuildContext context) => new BatteryScreen(), */
        '/geolocation' : (BuildContext context) => new GeolocationScreen(),
        '/database' : (BuildContext context) => new DatabaseScreen(),
      },
    )
  );
}