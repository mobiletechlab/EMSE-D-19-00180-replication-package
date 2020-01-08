"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var BenchmarkItem_1 = require("../../models/BenchmarkItem");
var Subject_1 = require("rxjs/Subject");
var moment = require("moment");
var BenchmarkerComponent = /** @class */ (function () {
    function BenchmarkerComponent() {
        this.repetitions = 30;
        this.onBenchmark = new core_1.EventEmitter();
        this.currentRunNumber = 0;
        this.outputLog = ["Waiting to start..."];
        this.benchmarkResults = [];
        this.arraySubject = new Subject_1.Subject();
        this.benchmarkRunning = false;
    }
    BenchmarkerComponent.prototype.ngOnInit = function () { };
    BenchmarkerComponent.prototype.onSliderChanged = function (event) {
        this.repetitions = event.value;
    };
    BenchmarkerComponent.prototype.pushLogOutput = function (msg) {
        this.outputLog.push(moment().format('HH:mm:ss:SSS') + " - " + msg);
    };
    BenchmarkerComponent.prototype.resetBenchmark = function () {
        this.benchmarkRunning = false;
        var numOfSuccessfulRuns = 0;
        var numOfFailedRuns = 0;
        var sumOfRunDuration = 0;
        for (var _i = 0, _a = this.benchmarkResults; _i < _a.length; _i++) {
            var item = _a[_i];
            if (!item.error) {
                numOfSuccessfulRuns++;
                sumOfRunDuration += item.completionTime.getTime() - item.startTime.getTime();
            }
            else {
                numOfFailedRuns++;
            }
        }
        this.pushLogOutput("Average duration of " + this.currentRunNumber + " successful runs: " + (sumOfRunDuration / numOfSuccessfulRuns) + " ms");
        if (numOfFailedRuns > 0) {
            this.pushLogOutput(numOfFailedRuns + " runs failed.");
        }
        this.currentRunNumber = 0;
        this.arraySubject.unsubscribe();
        this.arraySubject = new Subject_1.Subject();
    };
    BenchmarkerComponent.prototype.resetLogAndResults = function () {
        this.benchmarkResults = [];
        this.outputLog = [];
    };
    BenchmarkerComponent.prototype.initBenchmark = function () {
        this.benchmarkRunning = true;
        this.pushLogOutput(this.feature.toUpperCase() + " Benchmark started");
        this.currentRunNumber++;
    };
    BenchmarkerComponent.prototype.startBenchmarkRun = function (currentRun) {
        this.onBenchmark.emit({ observer: this.arraySubject, currentRun: currentRun });
        this.pushLogOutput("Run " + currentRun.runNumber + " started");
    };
    BenchmarkerComponent.prototype.onBenchmarkClicked = function () {
        var _this = this;
        if (!this.benchmarkRunning) {
            this.resetLogAndResults();
            this.initBenchmark();
            var currentRun_1 = new BenchmarkItem_1.default();
            currentRun_1.runNumber = this.currentRunNumber;
            currentRun_1.type = this.feature;
            this.startBenchmarkRun(currentRun_1); // Run first test
            this.arraySubject.subscribe(function (runResult) {
                _this.benchmarkResults.push(runResult);
                _this.pushLogOutput(runResult.resultValue);
                if (_this.currentRunNumber < _this.repetitions) {
                    _this.currentRunNumber++;
                    currentRun_1.runNumber = _this.currentRunNumber;
                    _this.startBenchmarkRun(currentRun_1); // Run test again if more than 1 repetition
                }
                else {
                    _this.resetBenchmark();
                }
            });
        }
        else {
            this.pushLogOutput("Benchmark cancelled.");
            this.resetBenchmark();
        }
    };
    __decorate([
        core_1.Input(),
        __metadata("design:type", String)
    ], BenchmarkerComponent.prototype, "feature", void 0);
    __decorate([
        core_1.Input(),
        __metadata("design:type", String)
    ], BenchmarkerComponent.prototype, "description", void 0);
    __decorate([
        core_1.Input(),
        __metadata("design:type", Number)
    ], BenchmarkerComponent.prototype, "repetitions", void 0);
    __decorate([
        core_1.Output(),
        __metadata("design:type", core_1.EventEmitter)
    ], BenchmarkerComponent.prototype, "onBenchmark", void 0);
    BenchmarkerComponent = __decorate([
        core_1.Component({
            selector: "ns-benchmarker",
            moduleId: module.id,
            templateUrl: "./benchmarker.component.html",
        }),
        __metadata("design:paramtypes", [])
    ], BenchmarkerComponent);
    return BenchmarkerComponent;
}());
exports.BenchmarkerComponent = BenchmarkerComponent;
