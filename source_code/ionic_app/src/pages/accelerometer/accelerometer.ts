import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { DeviceMotion, DeviceMotionAccelerationData } from '@ionic-native/device-motion';
import BenchmarkItem from '../../models/BenchmarkItem';

@Component({
  selector: 'page-accelerometer',
  templateUrl: 'accelerometer.html',
})
export class AccelerometerPage {

  constructor(public navCtrl: NavController, public navParams: NavParams, public deviceMotion: DeviceMotion) {
  }

  task() {
    console.log("run task", performance.now())
  }


  runTask(
    { observer, currentRun, }: { observer: any, currentRun: BenchmarkItem }
  ): void {
    currentRun.startTime = new Date();
    this.deviceMotion.getCurrentAcceleration()
      .then((acceleration) => {
        currentRun.completionTime = new Date();
        currentRun.resultValue = `Run ${currentRun.runNumber} completed (${acceleration.x}, ${acceleration.y}, ${acceleration.z})`;
        observer.next(currentRun);
      }).catch((error) => {
        currentRun.completionTime = new Date();
        currentRun.resultValue = `Run ${currentRun.runNumber} failed`;
        currentRun.error = true;
        console.log(error);
        observer.next(currentRun);
      });
  }
}
