"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var ItemsComponent = /** @class */ (function () {
    function ItemsComponent() {
        //items: Item[];
        this.navigationPages = [
            'CameraPage',
            'AccelerometerPage',
            'GeolocationPage',
            'FileSystemPage',
            'DatabasePage'
        ];
    }
    ItemsComponent.prototype.ngOnInit = function () {
    };
    ItemsComponent = __decorate([
        core_1.Component({
            selector: "ns-items",
            moduleId: module.id,
            templateUrl: "./items.component.html",
        }),
        __metadata("design:paramtypes", [])
    ], ItemsComponent);
    return ItemsComponent;
}());
exports.ItemsComponent = ItemsComponent;
