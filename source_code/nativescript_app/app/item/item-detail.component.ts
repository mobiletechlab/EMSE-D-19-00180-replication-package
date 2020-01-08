import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";

import { Item } from "./item";

@Component({
    selector: "ns-details",
    moduleId: module.id,
    templateUrl: "./item-detail.component.html",
})
export class ItemDetailComponent implements OnInit {
    item: Item;

    constructor(
        private route: ActivatedRoute
    ) { }

    ngOnInit(): void {
        const id = +this.route.snapshot.params["id"];
    }
}
