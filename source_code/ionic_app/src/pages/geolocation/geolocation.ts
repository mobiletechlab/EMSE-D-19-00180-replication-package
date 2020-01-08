import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Geolocation } from '@ionic-native/geolocation';
import BenchmarkItem from '../../models/BenchmarkItem';

@Component({
  selector: 'page-geolocation',
  templateUrl: 'geolocation.html',
})
export class GeolocationPage {

  constructor(public navCtrl: NavController, public navParams: NavParams, private geolocation: Geolocation) {
    
  }

  runTask(
    { observer, currentRun, }: { observer: any, currentRun: BenchmarkItem }
  ): void {
    currentRun.startTime = new Date();
    this.geolocation.getCurrentPosition({timeout: 30000})
      .then((location) => {
        currentRun.completionTime = new Date();
        currentRun.resultValue = `Run ${currentRun.runNumber} completed (${location.coords.latitude} ${location.coords.longitude})`;
        observer.next(currentRun);
      }).catch((error) => {
        currentRun.completionTime = new Date();
        currentRun.resultValue = `Run ${currentRun.runNumber} errored`;
        currentRun.error = true;
        observer.next(currentRun);
      });
  }

}
