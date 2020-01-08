import { NgModule, NO_ERRORS_SCHEMA } from "@angular/core";
import { NativeScriptModule } from "nativescript-angular/nativescript.module";
import { AppRoutingModule } from "./app.routing";
import { AppComponent } from "./app.component";

import { HomeComponent } from "./pages/home/home.component";
import { GeolocationComponent } from "./pages/geolocation/geolocation.component";
import { DatabaseComponent } from "./pages/database/database.component";
import { FilesystemComponent } from "./pages/filesystem/filesystem.component";
import { AccelerometerComponent } from "./pages/accelerometer/accelerometer.component";
import { BenchmarkerComponent } from "./components/benchmarker/benchmarker.component";
import { ContactsComponent } from "./pages/contacts/contacts.component";

// Uncomment and add to NgModule imports if you need to use two-way binding
// import { NativeScriptFormsModule } from "nativescript-angular/forms";

// Uncomment and add to NgModule imports  if you need to use the HTTP wrapper
// import { NativeScriptHttpModule } from "nativescript-angular/http";

@NgModule({
    bootstrap: [
        AppComponent
    ],
    imports: [
        NativeScriptModule,
        AppRoutingModule
    ],
    declarations: [
        AppComponent,
        HomeComponent,
        ContactsComponent,
        GeolocationComponent,
        AccelerometerComponent,
        DatabaseComponent,
        FilesystemComponent,
        BenchmarkerComponent
    ],
    providers: [ ],
    schemas: [
        NO_ERRORS_SCHEMA
    ]
})
/*
Pass your application module to the bootstrapModule function located in main.ts to start your app
*/
export class AppModule { }
