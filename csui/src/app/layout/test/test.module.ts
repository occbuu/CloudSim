import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TestRoutingModule } from './test-routing.module';
import { TestComponent } from './test.component';
import { PageHeaderModule } from './../../shared';

@NgModule({
    imports: [CommonModule, TestRoutingModule, PageHeaderModule],
    declarations: [TestComponent]
})
export class TestModule {}
