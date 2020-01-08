import { Component, OnInit } from "@angular/core";

import * as fs from "tns-core-modules/file-system";
import * as imageSourceModule from "tns-core-modules/image-source";

@Component({
    selector: "ns-filesystem",
    moduleId: module.id,
    templateUrl: "./filesystem.component.html",
})
export class FilesystemComponent implements OnInit {
    //items: Item[];

    constructor() { }

    ngOnInit(): void { }

    saveBenchmarkImageToDevice(e) {
        const documents = fs.knownFolders.documents();
        const path = fs.path.join(documents.path, "benchmarker.png");

        const saved = imageSourceModule.fromBase64(require('./file_benchmark_b64').default).saveToFile(path, "png");
        if (saved) {
            alert("Image saved successfully!");
        } else {
            alert("Image saved wrongly!");
        }
    }

    runTask({ observer, currentRun }): void {

        const documents = fs.knownFolders.documents();
        const path = fs.path.join(documents.path, "benchmarker.png");

        currentRun.startTime = new Date();
        imageSourceModule.fromFile(path);
        currentRun.completionTime = new Date();
        //console.log(imageFromLocalFile.toBase64String('png'));
        currentRun.resultValue = `Run ${currentRun.runNumber} completed.`;
        setTimeout(() => {
            observer.next(currentRun);
        }, 0);


        /* console.log("runTask");
        currentRun.startTime = new Date();
        console.log(JSON.stringify(currentRun))
        getCurrentLocation({ desiredAccuracy: 3, updateDistance: 10, maximumAge: 20000, timeout: 20000 })
            .then((location) => {
                console.log(JSON.stringify(location))
                currentRun.completionTime = new Date();
                currentRun.resultValue = `Run ${currentRun.runNumber} completed (${location.latitude} ${location.longitude})`;
                observer.next(currentRun);
            }).catch((error) => {
                currentRun.completionTime = new Date();
                console.log(JSON.stringify(currentRun))
                console.log(JSON.stringify(error))
                currentRun.resultValue = `Run ${currentRun.runNumber} errored`;
                currentRun.error = true;
                observer.next(currentRun);
            }); */
        /*         const documents = fs.knownFolders.documents();
                const path = fs.path.join(documents.path, "benchmarker.png");
                const filePath = fs.File.fromPath(path);
        
                console.log(JSON.stringify(currentRun));
        
                currentRun.startTime = new Date();
                const binarySource = filePath.readSync((err) => {
                    console.log(err);
                });
                console.log(binarySource);
        
                currentRun.completionTime = new Date();
                currentRun.resultValue = `Run ${currentRun.runNumber} completed.`;
        
                observer.next(currentRun); */


        /* currentRun.completionTime = new Date();
        currentRun.resultValue = `Not implemented`;
        setTimeout(() => {
            observer.next(currentRun);
        }, 300);*/
    }
}