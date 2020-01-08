import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';

import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';
import { Camera } from '@ionic-native/camera';
import { BatteryStatus } from '@ionic-native/battery-status';
import { DeviceMotion, DeviceMotionAccelerationData } from '@ionic-native/device-motion';
import { AndroidPermissions } from '@ionic-native/android-permissions';
import { Geolocation } from '@ionic-native/geolocation';
import { File } from '@ionic-native/file';
import { SQLite } from '@ionic-native/sqlite';
import { Contacts } from '@ionic-native/contacts';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { CameraPage } from '../pages/camera/camera';
import { AccelerometerPage } from '../pages/accelerometer/accelerometer';
import { GeolocationPage } from '../pages/geolocation/geolocation';
import { DatabasePage } from '../pages/database/database';
import { FileSystemPage } from '../pages/file-system/file-system';

import { ComponentsModule } from '../components/components.module';

import { ResultsProvider } from '../providers/results/results';
import { ContactsPage } from '../pages/contacts/contacts';

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    CameraPage,
    AccelerometerPage,
    GeolocationPage,
    DatabasePage,
    FileSystemPage,
    ContactsPage
  ],
  imports: [
    BrowserModule,
    ComponentsModule,
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    CameraPage,
    AccelerometerPage,
    GeolocationPage,
    DatabasePage,
    FileSystemPage,
    ContactsPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    BatteryStatus,
    DeviceMotion,
    Geolocation,
    ResultsProvider,
    Camera,
    AndroidPermissions,
    File,
    SQLite,
    Contacts
  ]
})
export class AppModule {}
