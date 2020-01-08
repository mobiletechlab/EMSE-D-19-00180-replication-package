"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var router_1 = require("@angular/router");
var ItemDetailComponent = /** @class */ (function () {
    function ItemDetailComponent(route) {
        this.route = route;
    }
    ItemDetailComponent.prototype.ngOnInit = function () {
        var id = +this.route.snapshot.params["id"];
    };
    ItemDetailComponent = __decorate([
        core_1.Component({
            selector: "ns-details",
            moduleId: module.id,
            templateUrl: "./item-detail.component.html",
        }),
        __metadata("design:paramtypes", [router_1.ActivatedRoute])
    ], ItemDetailComponent);
    return ItemDetailComponent;
}());
exports.ItemDetailComponent = ItemDetailComponent;
