"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var HomeComponent = /** @class */ (function () {
    function HomeComponent() {
        //items: Item[];
        this.navigationPages = [
            'accelerometer',
            'geolocation',
            'contacts',
            'filesystem',
            'database'
        ];
    }
    HomeComponent.prototype.ngOnInit = function () {
    };
    HomeComponent = __decorate([
        core_1.Component({
            selector: "ns-home",
            moduleId: module.id,
            templateUrl: "./home.component.html",
        }),
        __metadata("design:paramtypes", [])
    ], HomeComponent);
    return HomeComponent;
}());
exports.HomeComponent = HomeComponent;
