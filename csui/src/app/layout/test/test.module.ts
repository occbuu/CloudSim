import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChartsModule as Ng2Charts } from 'ng2-charts';

import { TestRoutingModule } from './test-routing.module';
import { TestComponent } from './test.component';
import { PageHeaderModule } from './../../shared';
import { LoadingModule } from 'ngx-loading';

@NgModule({
    imports: [
        CommonModule, 
        Ng2Charts, 
        TestRoutingModule, 
        PageHeaderModule, 
        LoadingModule],
    declarations: [TestComponent]
})
export class TestModule {}
