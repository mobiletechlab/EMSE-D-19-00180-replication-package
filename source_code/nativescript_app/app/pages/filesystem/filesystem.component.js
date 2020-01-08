"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var fs = require("tns-core-modules/file-system");
var imageSourceModule = require("tns-core-modules/image-source");
var FilesystemComponent = /** @class */ (function () {
    //items: Item[];
    function FilesystemComponent() {
    }
    FilesystemComponent.prototype.ngOnInit = function () { };
    FilesystemComponent.prototype.saveBenchmarkImageToDevice = function (e) {
        var documents = fs.knownFolders.documents();
        var path = fs.path.join(documents.path, "benchmarker.png");
        var saved = imageSourceModule.fromBase64(require('./file_benchmark_b64').default).saveToFile(path, "png");
        if (saved) {
            alert("Image saved successfully!");
        }
        else {
            alert("Image saved wrongly!");
        }
    };
    FilesystemComponent.prototype.runTask = function (_a) {
        var observer = _a.observer, currentRun = _a.currentRun;
        var documents = fs.knownFolders.documents();
        var path = fs.path.join(documents.path, "benchmarker.png");
        currentRun.startTime = new Date();
        imageSourceModule.fromFile(path);
        currentRun.completionTime = new Date();
        //console.log(imageFromLocalFile.toBase64String('png'));
        currentRun.resultValue = "Run " + currentRun.runNumber + " completed.";
        setTimeout(function () {
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
    };
    FilesystemComponent = __decorate([
        core_1.Component({
            selector: "ns-filesystem",
            moduleId: module.id,
            templateUrl: "./filesystem.component.html",
        }),
        __metadata("design:paramtypes", [])
    ], FilesystemComponent);
    return FilesystemComponent;
}());
exports.FilesystemComponent = FilesystemComponent;
