import { Component, OnInit } from "@angular/core";

@Component({
    selector: "ns-home",
    moduleId: module.id,
    templateUrl: "./home.component.html",
})
export class HomeComponent implements OnInit {
    //items: Item[];
    navigationPages: any[] = [
        'accelerometer',
        'geolocation',
        'contacts',
        'filesystem',
        'database'
    ];

    constructor() { }

    ngOnInit(): void {
        
    }
}