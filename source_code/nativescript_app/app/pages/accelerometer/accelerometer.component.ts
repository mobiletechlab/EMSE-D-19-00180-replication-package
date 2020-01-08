import { Component, OnInit } from "@angular/core";
const accelerometer = require("nativescript-accelerometer");

@Component({
    selector: "ns-accelerometer",
    moduleId: module.id,
    templateUrl: "./accelerometer.component.html",
})
export class AccelerometerComponent implements OnInit {
    //items: Item[];

    constructor() { }

    ngOnInit(): void { }

    runTask({ observer, currentRun }): void { 
        currentRun.startTime = new Date();
        accelerometer.startAccelerometerUpdates((data) => {
            currentRun.completionTime = new Date();
            currentRun.resultValue = `Run ${currentRun.runNumber} completed (x: ${data.x}, y: ${data.y} z: ${data.z})`;
            accelerometer.stopAccelerometerUpdates();
            observer.next(currentRun);
        }, { sensorDelay: "fastest" });
        /* getCurrentLocation({ desiredAccuracy: 3, updateDistance: 10, maximumAge: 20000, timeout: 20000 })
            .then((location) => {
                currentRun.completionTime = new Date();
                currentRun.resultValue = `Run ${currentRun.runNumber} completed (${location.latitude} ${location.longitude})`;
                observer.next(currentRun);
            }).catch((error) => {
                currentRun.completionTime = new Date();
                console.log(JSON.stringify(error))
                currentRun.resultValue = `Run ${currentRun.runNumber} errored`;
                currentRun.error = true;
                observer.next(currentRun);
            }); */
    }
}