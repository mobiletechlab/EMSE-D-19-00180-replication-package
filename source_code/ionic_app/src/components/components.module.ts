import { NgModule } from '@angular/core';
import { IonicModule } from 'ionic-angular';
import { BenchmarkerComponent } from './benchmarker/benchmarker';
@NgModule({
	declarations: [BenchmarkerComponent],
	imports: [IonicModule],
	exports: [BenchmarkerComponent]
})
export class ComponentsModule {}
