"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var home_component_1 = require("./pages/home/home.component");
var geolocation_component_1 = require("./pages/geolocation/geolocation.component");
var database_component_1 = require("./pages/database/database.component");
var filesystem_component_1 = require("./pages/filesystem/filesystem.component");
var accelerometer_component_1 = require("./pages/accelerometer/accelerometer.component");
var contacts_component_1 = require("./pages/contacts/contacts.component");
exports.default = [
    { path: "", redirectTo: "/home", pathMatch: "full" },
    { path: "home", component: home_component_1.HomeComponent },
    { path: "contacts", component: contacts_component_1.ContactsComponent },
    { path: "geolocation", component: geolocation_component_1.GeolocationComponent },
    { path: "accelerometer", component: accelerometer_component_1.AccelerometerComponent },
    { path: "database", component: database_component_1.DatabaseComponent },
    { path: "filesystem", component: filesystem_component_1.FilesystemComponent },
];
