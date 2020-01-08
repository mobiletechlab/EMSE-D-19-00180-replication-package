import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';

class HomePageNavigationButton extends StatelessWidget {
  final String routeName;
  final String routeTitle;

  HomePageNavigationButton({
    this.routeName,
    this.routeTitle,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(5.0),
      child: MaterialButton(
        color: Color(0xD0D2D1),
        minWidth: 300.0,
        elevation: 1.0,
        onPressed: () {
          Navigator.of(context).pushNamed(routeName);
        },
        child: Text(routeTitle),
      ),
    );
  }
}
