"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var nativescript_geolocation_1 = require("nativescript-geolocation");
var enums_1 = require("ui/enums");
var GeolocationComponent = /** @class */ (function () {
    //items: Item[];
    function GeolocationComponent() {
    }
    GeolocationComponent.prototype.ngOnInit = function () {
        nativescript_geolocation_1.isEnabled().then(function (isEnabled) {
            if (!isEnabled) {
                nativescript_geolocation_1.enableLocationRequest().then(function () {
                }, function (e) {
                    console.log("Error: " + (e.message || e));
                });
            }
        }, function (e) {
            console.log("Error: " + (e.message || e));
        });
    };
    GeolocationComponent.prototype.runTask = function (_a) {
        var observer = _a.observer, currentRun = _a.currentRun;
        currentRun.startTime = new Date();
        nativescript_geolocation_1.getCurrentLocation({
            timeout: 0,
            desiredAccuracy: enums_1.Accuracy.high
        })
            .then(function (location) {
            currentRun.completionTime = new Date();
            currentRun.resultValue = "Run " + currentRun.runNumber + " completed (" + location.latitude + " " + location.longitude + ")";
            observer.next(currentRun);
        }).catch(function (error) {
            currentRun.completionTime = new Date();
            console.log(JSON.stringify(error));
            currentRun.resultValue = "Run " + currentRun.runNumber + " errored";
            currentRun.error = true;
            observer.next(currentRun);
        });
    };
    GeolocationComponent = __decorate([
        core_1.Component({
            selector: "ns-geolocation",
            moduleId: module.id,
            templateUrl: "./geolocation.component.html",
        }),
        __metadata("design:paramtypes", [])
    ], GeolocationComponent);
    return GeolocationComponent;
}());
exports.GeolocationComponent = GeolocationComponent;
