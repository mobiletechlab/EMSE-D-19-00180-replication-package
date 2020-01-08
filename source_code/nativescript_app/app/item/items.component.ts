import { Component, OnInit } from "@angular/core";

import { Item } from "./item";

@Component({
    selector: "ns-items",
    moduleId: module.id,
    templateUrl: "./items.component.html",
})
export class ItemsComponent implements OnInit {
    //items: Item[];
    navigationPages: any = [
        'CameraPage',
        'AccelerometerPage',
        'GeolocationPage',
        'FileSystemPage',
        'DatabasePage'
    ];

    constructor() { }

    ngOnInit(): void {
        
    }
}