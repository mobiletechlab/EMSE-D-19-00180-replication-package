String logFormatter(text) {
  var dateTime = DateTime.now();
  return "${dateTime.hour}:${dateTime.minute}:${dateTime.second}:${dateTime.millisecond} - $text";
}
