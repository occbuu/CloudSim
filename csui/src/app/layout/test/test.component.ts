import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { DashboardProvider } from '../../providers/dashboard/dashboard';

@Component({
    selector: 'app-test',
    templateUrl: './test.component.html',
    styleUrls: ['./test.component.scss'],
    animations: [routerTransition()]
})
export class TestComponent implements OnInit {
    public orders: any[] = [];
    constructor(
        private pro: DashboardProvider,
    ) {}

    ngOnInit() {}

    public getOrders() {
        //console.log(this.selected);
        this.pro.getOrders("ReqTest").subscribe((res: any) => {
          this.orders = res.result.data;
          console.log(res.result);
        }, err => this.pro.handleError(err));
      }
}
