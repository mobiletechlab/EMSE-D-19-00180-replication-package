import { Component, OnInit } from "@angular/core";
import { isEnabled, enableLocationRequest, getCurrentLocation, watchLocation, distance, clearWatch } from "nativescript-geolocation";
import { Accuracy } from "ui/enums";

import BenchmarkItem from "../../models/BenchmarkItem";

@Component({
    selector: "ns-geolocation",
    moduleId: module.id,
    templateUrl: "./geolocation.component.html",
})
export class GeolocationComponent implements OnInit {
    //items: Item[];

    constructor() { }

    ngOnInit(): void {
        isEnabled().then(function (isEnabled) {
            if (!isEnabled) {
                enableLocationRequest().then(function () {
                }, function (e) {
                    console.log("Error: " + (e.message || e));
                });
            }
        }, function (e) {
            console.log("Error: " + (e.message || e));
        });
    }

    runTask({ observer, currentRun }): void {
        currentRun.startTime = new Date();
        getCurrentLocation({
            timeout: 0,
            desiredAccuracy: Accuracy.high
        })
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
            });
    }
}