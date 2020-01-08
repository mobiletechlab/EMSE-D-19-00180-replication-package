import { Component, OnInit } from "@angular/core";
import BenchmarkItem from "../../models/BenchmarkItem";
const contacts = require("nativescript-contacts");
const permissions = require("nativescript-permissions");

declare var android: any;

@Component({
    selector: "ns-contacts",
    moduleId: module.id,
    templateUrl: "./contacts.component.html",
})
export class ContactsComponent implements OnInit {

    constructor() {
        permissions.requestPermissions([android.Manifest.permission.GET_ACCOUNTS, android.Manifest.permission.READ_CONTACTS, android.Manifest.permission.WRITE_CONTACTS], "I need these permissions because I'm cool")
            .then(() => {
                console.log("Permission granted!");
            }).catch(() => {
                console.log("Permission is not granted (sadface)");
            });
    }

    ngOnInit(): void {
    }

    createContact(currentRun) {
        return new Promise((resolve, reject) => {
            var contact = new contacts.Contact();
            contact.name.given = currentRun.runNumber.toString();
            contact.name.family = "NATIVESCRIPT_FAKE";
            contact.phoneNumbers.push({ label: contacts.KnownLabel.MAIN, value: "123456789" });
            contact.save();
            resolve();
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
            })
            .catch((error) => {
                console.error(error);
                currentRun.completionTime = new Date();
                currentRun.resultValue = `Run ${currentRun.runNumber} errored`;
                currentRun.error = true;
                observer.next(currentRun);
            });
    }
}