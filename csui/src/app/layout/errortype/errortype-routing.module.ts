import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ErrortypeComponent } from './errortype.component';

const routes: Routes = [
    {
        path: '', component: ErrortypeComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ErrortypeRoutingModule {
}
