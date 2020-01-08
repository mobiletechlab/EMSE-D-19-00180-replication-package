import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Subject, SubjectSubscriber } from 'rxjs/Subject'
import BenchmarkItem from '../../models/BenchmarkItem';
import * as moment from 'moment';

@Component({
  selector: 'benchmarker',
  templateUrl: 'benchmarker.html'
})
export class BenchmarkerComponent {

  // Attributes for the <benchmarker></benchmarker> custom component
  @Input() feature: string;
  @Input() description: string;
  @Input() repetitions: number = 30;
  @Output() onBenchmark: EventEmitter<any> = new EventEmitter();


  public currentRunNumber = 0;
  public outputLog: string[] = [`Waiting to start...`];
  public benchmarkResults: BenchmarkItem[] = [];
  public arraySubject = new Subject();
  public benchmarkRunning = false;

  //repetitions = 30;

  constructor() { }

  onSliderChanged(event) {
    this.repetitions = event.value;
  }

  pushLogOutput(msg): void {
    this.outputLog.push(`${moment().format('HH:mm:ss:SSS')} - ${msg}`);
  }

  resetBenchmark() {
    this.benchmarkRunning = false;
    this.arraySubject.unsubscribe();
    let numOfSuccessfulRuns: number = 0;
    let numOfFailedRuns: number = 0;
    let sumOfRunDuration: number = 0;
    for (let item of this.benchmarkResults) {
      if (!item.error) {
        numOfSuccessfulRuns++;
        sumOfRunDuration += item.completionTime.getTime() - item.startTime.getTime();
      } else {
        numOfFailedRuns++;
      }
    }
    this.pushLogOutput(`Average duration of ${this.currentRunNumber} successful runs: ${(sumOfRunDuration / numOfSuccessfulRuns)} ms`);
    if (numOfFailedRuns > 0) {
      this.pushLogOutput(`${numOfFailedRuns} runs failed.`);
    }
    this.currentRunNumber = 0;
  }

  resetLogAndResults() {
    this.benchmarkResults = [];
    this.outputLog = [];
  }

  initBenchmark() {
    this.benchmarkRunning = true;
    this.pushLogOutput(`${this.feature.toUpperCase()} Benchmark started`);
    this.currentRunNumber++;
  }

  startBenchmarkRun(currentRun: BenchmarkItem) {
    this.onBenchmark.emit({ observer: this.arraySubject, currentRun, benchmarkResults: this.benchmarkResults });
    this.pushLogOutput(`Run ${currentRun.runNumber} started`);
  }

  onBenchmarkClicked(): void {
    if (!this.benchmarkRunning) {
      this.resetLogAndResults();
      this.initBenchmark();

      let currentRun = new BenchmarkItem();
      currentRun.runNumber = this.currentRunNumber;
      currentRun.type = this.feature;
      this.startBenchmarkRun(currentRun); // Run first test

      this.arraySubject.subscribe((runResult: BenchmarkItem) => {
        this.benchmarkResults.push(runResult);
        this.pushLogOutput(runResult.resultValue);

        if (this.currentRunNumber < this.repetitions) {
          this.currentRunNumber++;
          currentRun.runNumber = this.currentRunNumber;
          this.startBenchmarkRun(currentRun); // Run test again if more than 1 repetition
        } else {
          this.resetBenchmark();
        }
      });
    } else {
      this.pushLogOutput(`Benchmark cancelled.`);
      this.resetBenchmark();
    }
  }

}
