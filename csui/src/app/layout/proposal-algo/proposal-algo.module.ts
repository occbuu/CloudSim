import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChartsModule as Ng2Charts } from 'ng2-charts';

import { ProposalAlgoRoutingModule } from './proposal-algo-routing.module';
import { ProposalAlgoComponent } from './proposal-algo.component';
import { PageHeaderModule } from './../../shared';
import { LoadingModule } from 'ngx-loading';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
    imports: [
        CommonModule, 
        Ng2Charts, 
        ProposalAlgoRoutingModule, 
        FormsModule,
        NgbModule.forRoot(), 
        PageHeaderModule, 
        LoadingModule],
    declarations: [ProposalAlgoComponent]
})
export class ProposalAlgoModule {}
