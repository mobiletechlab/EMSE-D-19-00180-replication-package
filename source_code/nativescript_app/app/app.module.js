"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var nativescript_module_1 = require("nativescript-angular/nativescript.module");
var app_routing_1 = require("./app.routing");
var app_component_1 = require("./app.component");
var home_component_1 = require("./pages/home/home.component");
var geolocation_component_1 = require("./pages/geolocation/geolocation.component");
var database_component_1 = require("./pages/database/database.component");
var filesystem_component_1 = require("./pages/filesystem/filesystem.component");
var accelerometer_component_1 = require("./pages/accelerometer/accelerometer.component");
var benchmarker_component_1 = require("./components/benchmarker/benchmarker.component");
var contacts_component_1 = require("./pages/contacts/contacts.component");
// Uncomment and add to NgModule imports if you need to use two-way binding
// import { NativeScriptFormsModule } from "nativescript-angular/forms";
// Uncomment and add to NgModule imports  if you need to use the HTTP wrapper
// import { NativeScriptHttpModule } from "nativescript-angular/http";
var AppModule = /** @class */ (function () {
    /*
    Pass your application module to the bootstrapModule function located in main.ts to start your app
    */
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            bootstrap: [
                app_component_1.AppComponent
            ],
            imports: [
                nativescript_module_1.NativeScriptModule,
                app_routing_1.AppRoutingModule
            ],
            declarations: [
                app_component_1.AppComponent,
                home_component_1.HomeComponent,
                contacts_component_1.ContactsComponent,
                geolocation_component_1.GeolocationComponent,
                accelerometer_component_1.AccelerometerComponent,
                database_component_1.DatabaseComponent,
                filesystem_component_1.FilesystemComponent,
                benchmarker_component_1.BenchmarkerComponent
            ],
            providers: [],
            schemas: [
                core_1.NO_ERRORS_SCHEMA
            ]
        })
        /*
        Pass your application module to the bootstrapModule function located in main.ts to start your app
        */
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;
