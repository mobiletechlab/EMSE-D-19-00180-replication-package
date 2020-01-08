import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { CameraPage } from '../camera/camera';
import { AccelerometerPage } from '../accelerometer/accelerometer';
import { GeolocationPage } from '../geolocation/geolocation';
import { FileSystemPage } from '../file-system/file-system';
import { DatabasePage } from '../database/database';
import { AndroidPermissions } from '@ionic-native/android-permissions';
import { ContactsPage } from '../contacts/contacts';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  navigationPages: any = {
    /* CameraPage, */
    AccelerometerPage,
    GeolocationPage,
    FileSystemPage,
    DatabasePage,
    ContactsPage
  };

  pages: any[] = [];

  constructor(public navCtrl: NavController, private androidPermissions: AndroidPermissions) {
    this.pages = Object.keys(this.navigationPages);
    this.androidPermissions.requestPermissions(
      [
        this.androidPermissions.PERMISSION.CAMERA,
        this.androidPermissions.PERMISSION.ACCESS_FINE_LOCATION,
        this.androidPermissions.PERMISSION.ACCESS_COARSE_LOCATION,
        this.androidPermissions.PERMISSION.READ_EXTERNAL_STORAGE,
        this.androidPermissions.PERMISSION.WRITE_EXTERNAL_STORAGE,
        this.androidPermissions.PERMISSION.READ_CONTACTS,
        this.androidPermissions.PERMISSION.WRITE_CONTACTS
      ]
    );

  }

  goToPage(page: string) {
    //this.navCtrl.push(page);
    this.navCtrl.push(this.navigationPages[page]);
  }

}
