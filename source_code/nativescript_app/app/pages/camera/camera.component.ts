import { Component, OnInit } from "@angular/core";

@Component({
    selector: "ns-camera",
    moduleId: module.id,
    templateUrl: "./camera.component.html",
})
export class CameraComponent implements OnInit {
    //items: Item[];
    
    constructor() { }

    ngOnInit(): void { }
    
    runTask({ observer, currentRun }): void {
        currentRun.startTime = new Date();
        currentRun.resultValue = `Not implemented`;
        setTimeout(() => {
            currentRun.completionTime = new Date();
            observer.next(currentRun);
        }, 300);
    }
}