import { NgModule } from "@angular/core";
import { NativeScriptRouterModule } from "nativescript-angular/router";

import { ItemsComponent } from "./item/items.component";
import { ItemDetailComponent } from "./item/item-detail.component";
import routes from './routes';

@NgModule({
    imports: [NativeScriptRouterModule.forRoot(routes)],
    exports: [NativeScriptRouterModule]
})
export class AppRoutingModule { }