import { Routes } from "@angular/router";
import { HomeComponent } from "./pages/home/home.component";
import { GeolocationComponent } from "./pages/geolocation/geolocation.component";
import { DatabaseComponent } from "./pages/database/database.component";
import { FilesystemComponent } from "./pages/filesystem/filesystem.component";
import { AccelerometerComponent } from "./pages/accelerometer/accelerometer.component";
import { ContactsComponent } from "./pages/contacts/contacts.component";

export default [
    { path: "", redirectTo: "/home", pathMatch: "full" },
    { path: "home", component: HomeComponent },
    { path: "contacts", component: ContactsComponent },
    { path: "geolocation", component: GeolocationComponent },
    { path: "accelerometer", component: AccelerometerComponent },
    { path: "database", component: DatabaseComponent },
    { path: "filesystem", component: FilesystemComponent },
] as Routes;