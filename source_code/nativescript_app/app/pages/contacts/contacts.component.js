"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var contacts = require("nativescript-contacts");
var permissions = require("nativescript-permissions");
var ContactsComponent = /** @class */ (function () {
    function ContactsComponent() {
        permissions.requestPermissions([android.Manifest.permission.GET_ACCOUNTS, android.Manifest.permission.READ_CONTACTS, android.Manifest.permission.WRITE_CONTACTS], "I need these permissions because I'm cool")
            .then(function () {
            console.log("Permission granted!");
        }).catch(function () {
            console.log("Permission is not granted (sadface)");
        });
    }
    ContactsComponent.prototype.ngOnInit = function () {
    };
    ContactsComponent.prototype.createContact = function (currentRun) {
        return new Promise(function (resolve, reject) {
            var contact = new contacts.Contact();
            contact.name.given = currentRun.runNumber.toString();
            contact.name.family = "NATIVESCRIPT_FAKE";
            contact.phoneNumbers.push({ label: contacts.KnownLabel.MAIN, value: "123456789" });
            contact.save();
            resolve();
        });
    };
    ContactsComponent.prototype.runTask = function (_a) {
        var observer = _a.observer, currentRun = _a.currentRun;
        currentRun.startTime = new Date();
        this.createContact(currentRun)
            .then(function () {
            currentRun.completionTime = new Date();
            currentRun.resultValue = "Run " + currentRun.runNumber + " completed (contact created)";
            observer.next(currentRun);
        })
            .catch(function (error) {
            console.error(error);
            currentRun.completionTime = new Date();
            currentRun.resultValue = "Run " + currentRun.runNumber + " errored";
            currentRun.error = true;
            observer.next(currentRun);
        });
    };
    ContactsComponent = __decorate([
        core_1.Component({
            selector: "ns-contacts",
            moduleId: module.id,
            templateUrl: "./contacts.component.html",
        }),
        __metadata("design:paramtypes", [])
    ], ContactsComponent);
    return ContactsComponent;
}());
exports.ContactsComponent = ContactsComponent;
