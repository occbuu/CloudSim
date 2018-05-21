import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ErrortypeRoutingModule } from './errortype-routing.module';
import { ErrortypeComponent } from './errortype.component';
import { PageHeaderModule } from './../../shared';

@NgModule({
    imports: [CommonModule, ErrortypeRoutingModule, PageHeaderModule],
    declarations: [ErrortypeComponent]
})
export class ErrortypeModule {}
