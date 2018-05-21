import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProposalAlgoComponent } from './proposal-algo.component';

const routes: Routes = [
    {
        path: '', component: ProposalAlgoComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ProposalAlgoRoutingModule {
}
