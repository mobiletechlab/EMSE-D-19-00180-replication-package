import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import BenchmarkItem from '../../models/BenchmarkItem';
import { Contacts, Contact, ContactField, ContactName, ContactFieldType, ContactFindOptions } from '@ionic-native/contacts';

@Component({
  selector: 'page-contacts',
  templateUrl: 'contacts.html',
})
export class ContactsPage {

  constructor(public navCtrl: NavController, public navParams: NavParams, private contacts: Contacts) { }

  createContact(currentRun: BenchmarkItem) {
    return new Promise((resolve, reject) => {
      let contact: Contact = this.contacts.create();
      contact.name = new ContactName(null, "IONIC_FAKE", currentRun.runNumber.toString());
      contact.phoneNumbers = [new ContactField('mobile', '123456789')];
      contact.save().then(
        () => resolve(),
        (error) => reject(error)
      );
    });
  }

  runTask(
    { observer, currentRun, }: { observer: any, currentRun: BenchmarkItem }
  ): void {
    currentRun.startTime = new Date();
    this.createContact(currentRun)
      .then(() => {
        currentRun.completionTime = new Date();
        currentRun.resultValue = `Run ${currentRun.runNumber} completed (contact created)`;
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