"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var BenchmarkItem = /** @class */ (function () {
    function BenchmarkItem(error, type, runNumber, startTime, completionTime, resultValue) {
        if (error === void 0) { error = false; }
        this.error = error;
        this.type = type;
        this.runNumber = runNumber;
        this.startTime = startTime;
        this.completionTime = completionTime;
        this.resultValue = resultValue;
    }
    return BenchmarkItem;
}());
exports.default = BenchmarkItem;
