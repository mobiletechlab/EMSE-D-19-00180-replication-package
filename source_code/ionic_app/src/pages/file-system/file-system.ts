import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { File } from '@ionic-native/file';
import BenchmarkItem from '../../models/BenchmarkItem';

import { b64 } from './file_benchmark_b64';

@Component({
  selector: 'page-file-system',
  templateUrl: 'file-system.html',
})
export class FileSystemPage {

  private image: any = "/assets/imgs/logo.png";

  constructor(public navCtrl: NavController, public navParams: NavParams, private file: File) {

  }

  saveBenchmarkImage() {
    var blob = this.b64toBlob(b64(), 'image/png');
    this.file.writeFile(this.file.externalDataDirectory, 'file_system_benchmark_2.png', blob)
      .then((success) => {
        alert("success");
        alert(JSON.stringify(success));
      })
      .catch((error) => {
        console.log(error);
        alert(JSON.stringify(error));
      });
  }


  // From here: https://forum.ionicframework.com/t/save-base64-jpg-to-file/53648
  b64toBlob(b64Data, contentType) {
    contentType = contentType || '';
    var sliceSize = 1024;
    var byteCharacters = atob(b64Data);
    var bytesLength = byteCharacters.length;
    var slicesCount = Math.ceil(bytesLength / sliceSize);
    var byteArrays = new Array(slicesCount);

    for (var sliceIndex = 0; sliceIndex < slicesCount; ++sliceIndex) {
      var begin = sliceIndex * sliceSize;
      var end = Math.min(begin + sliceSize, bytesLength);

      var bytes = new Array(end - begin);
      for (var offset = begin, i = 0; offset < end; ++i, ++offset) {
        bytes[i] = byteCharacters[offset].charCodeAt(0);
      }
      byteArrays[sliceIndex] = new Uint8Array(bytes);
    }
    return new Blob(byteArrays, { type: contentType });
  }


  runTask(
    { observer, currentRun, }: { observer: any, currentRun: BenchmarkItem }
  ): void {
    currentRun.startTime = new Date();
    this.file.readAsDataURL(this.file.externalDataDirectory, 'file_system_benchmark_2.png')
      .then((file) => {
        currentRun.completionTime = new Date();
        currentRun.resultValue = `Run ${currentRun.runNumber} completed (Image loaded)`;
        observer.next(currentRun);
      }).catch((error) => {
        console.error(error)
        currentRun.completionTime = new Date();
        currentRun.resultValue = `Run ${currentRun.runNumber} errored`;
        currentRun.error = true;
        observer.next(currentRun);
      });
  }

}
