import 'dart:async';
import 'dart:core';

import 'package:simple_permissions/simple_permissions.dart';
import 'package:contacts_service/contacts_service.dart';
import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import '../components/benchmarker.dart';
import '../utils/logFormatter.dart';

class ContactsScreen extends StatefulWidget {
  @override
  _ContactsScreenState createState() => new _ContactsScreenState();
}

class _ContactsScreenState extends State<ContactsScreen> {
  @override
  initState() {
    super.initState();
    SimplePermissions
        .requestPermission(Permission.ReadContacts)
        .then((res) => SimplePermissions
            .requestPermission(Permission.WriteContacts)
            .then((res) => print(res.toString()))
            .catchError((error) => print(error.toString())))
        .catchError((error) => print(error.toString()));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Contacts"),
        ),
        body: Column(children: <Widget>[
          Padding(padding: const EdgeInsets.all(5.0)),
          BenchmarkerComponent(
              "Contacts",
              "Write contacts to the device's contact list.",
              _onExecuteBenchmarkClicked),
        ]));
  }

  Future _createContact(int i) async {
    Contact contact = Contact();
    contact.familyName = "FLUTTER_FAKE";
    contact.givenName = i.toString();
    contact.phones = [Item(label: "mobile", value: "123456789")];
    print("Before AddContact");
    return ContactsService.addContact(contact);
  }

  void _onExecuteBenchmarkClicked(int numberOfBenchmarkExecutions,
      List<int> _benchmarkResults, List<String> _logs) async {
    setState(() {
      _logs.add(logFormatter("CONTACTS Benchmark started"));
    });

    final hackyAsyncPlaceholder = List.filled(numberOfBenchmarkExecutions, 0);

    int counter = 0;

    await Future.forEach(hackyAsyncPlaceholder, (el) async {
      _logs.add(logFormatter("Run $counter started"));
      Stopwatch stopwatch = new Stopwatch()..start();
      await this._createContact(counter);
      int timeElapsedMs = stopwatch.elapsedMilliseconds;
      setState(() {
        _logs.add(logFormatter("Run $counter completed"));
        _benchmarkResults.add(timeElapsedMs);
      });
      counter += 1;
    }).whenComplete(() {
      setState(() {
        _logs.add(logFormatter(
            "CONTACTS Benchmark completed (avg time: ${_benchmarkResults.reduce((val, acc) => val+acc)/_benchmarkResults.length}ms)"));
      });
    });
  }
}
