import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Camera } from '@ionic-native/camera';
import BenchmarkItem from '../../models/BenchmarkItem';

/**
 * Generated class for the CameraPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-camera',
  templateUrl: 'camera.html',
})
export class CameraPage {

  constructor(public navCtrl: NavController, public navParams: NavParams, private camera: Camera) {
  }

  runTask(
    { observer, currentRun, }: { observer: any, currentRun: BenchmarkItem }
  ): void {
    this.camera.getPicture()
    /*     currentRun.startTime = new Date();
        this.deviceMotion.getCurrentAcceleration()
          .then((acceleration) => {
            currentRun.completionTime = new Date();
            currentRun.resultValue = `Run ${currentRun.runNumber} completed (${acceleration.x}, ${acceleration.y}, ${acceleration.z})`;
            observer.next(currentRun);
          }).catch((error) => {
            currentRun.completionTime = new Date();
            currentRun.resultValue = `Run ${currentRun.runNumber} errored`;
            currentRun.error = true;
            console.log(error);
            observer.next(currentRun);
          }); */
  }

}
