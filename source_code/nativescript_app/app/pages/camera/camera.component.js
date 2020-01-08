"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var CameraComponent = /** @class */ (function () {
    //items: Item[];
    function CameraComponent() {
    }
    CameraComponent.prototype.ngOnInit = function () { };
    CameraComponent.prototype.runTask = function (_a) {
        var observer = _a.observer, currentRun = _a.currentRun;
        currentRun.startTime = new Date();
        currentRun.resultValue = "Not implemented";
        setTimeout(function () {
            currentRun.completionTime = new Date();
            observer.next(currentRun);
        }, 300);
    };
    CameraComponent = __decorate([
        core_1.Component({
            selector: "ns-camera",
            moduleId: module.id,
            templateUrl: "./camera.component.html",
        }),
        __metadata("design:paramtypes", [])
    ], CameraComponent);
    return CameraComponent;
}());
exports.CameraComponent = CameraComponent;
