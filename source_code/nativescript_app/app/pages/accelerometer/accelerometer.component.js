"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var accelerometer = require("nativescript-accelerometer");
var AccelerometerComponent = /** @class */ (function () {
    //items: Item[];
    function AccelerometerComponent() {
    }
    AccelerometerComponent.prototype.ngOnInit = function () { };
    AccelerometerComponent.prototype.runTask = function (_a) {
        var observer = _a.observer, currentRun = _a.currentRun;
        currentRun.startTime = new Date();
        accelerometer.startAccelerometerUpdates(function (data) {
            currentRun.completionTime = new Date();
            currentRun.resultValue = "Run " + currentRun.runNumber + " completed (x: " + data.x + ", y: " + data.y + " z: " + data.z + ")";
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
    };
    AccelerometerComponent = __decorate([
        core_1.Component({
            selector: "ns-accelerometer",
            moduleId: module.id,
            templateUrl: "./accelerometer.component.html",
        }),
        __metadata("design:paramtypes", [])
    ], AccelerometerComponent);
    return AccelerometerComponent;
}());
exports.AccelerometerComponent = AccelerometerComponent;
