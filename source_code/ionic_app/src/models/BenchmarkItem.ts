export default class BenchmarkItem {
    constructor(
        public error: boolean = false,
        public type?: string,
        public runNumber?: number,
        public startTime?: Date,
        public completionTime?: Date,
        public resultValue?: string) { }

    /* getText() {
        return ``;
    } */
}