import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';

@Component({
    selector: 'app-errortype',
    templateUrl: './errortype.component.html',
    styleUrls: ['./errortype.component.scss'],
    animations: [routerTransition()]
})
export class ErrortypeComponent implements OnInit {
    constructor() {}

    ngOnInit() {}
}
