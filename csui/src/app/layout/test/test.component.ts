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
    public Cloudlets: any[] = [];
    public Brokers: any[] = [];
    public loading = false;
    public isNotFive = false;
    public isFive = false;
    
    public ExampleTitle: string ='';
    public ExampleDesc: string ='';
    public ExampleDescEng: string ='';
    public Logging: string ='';

    // lineChart
    public lineChartData: Array<any> = [
        { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' },
        { data: [28, 48, 40, 19, 86, 27, 90], label: 'Series B' },
        { data: [18, 48, 77, 9, 100, 27, 40], label: 'Series C' }
    ];
    public lineChartLabels: Array<any> = [
        'January',
        'February',
        'March',
        'April',
        'May',
        'June',
        'July'
    ];
    public lineChartOptions: any = {
        responsive: true
    };
    public lineChartColors: Array<any> = [
        {
            // grey
            backgroundColor: 'rgba(148,159,177,0.2)',
            borderColor: 'rgba(148,159,177,1)',
            pointBackgroundColor: 'rgba(148,159,177,1)',
            pointBorderColor: '#fff',
            pointHoverBackgroundColor: '#fff',
            pointHoverBorderColor: 'rgba(148,159,177,0.8)'
        },
        {
            // dark grey
            backgroundColor: 'rgba(77,83,96,0.2)',
            borderColor: 'rgba(77,83,96,1)',
            pointBackgroundColor: 'rgba(77,83,96,1)',
            pointBorderColor: '#fff',
            pointHoverBackgroundColor: '#fff',
            pointHoverBorderColor: 'rgba(77,83,96,1)'
        },
        {
            // grey
            backgroundColor: 'rgba(148,159,177,0.2)',
            borderColor: 'rgba(148,159,177,1)',
            pointBackgroundColor: 'rgba(148,159,177,1)',
            pointBorderColor: '#fff',
            pointHoverBackgroundColor: '#fff',
            pointHoverBorderColor: 'rgba(148,159,177,0.8)'
        }
    ];
    public lineChartLegend: boolean = true;
    public lineChartType: string = 'line';

    // events
    public chartClicked(e: any): void {
        // console.log(e);
    }

    public chartHovered(e: any): void {
        // console.log(e);
    }

    

    constructor(
        private pro: DashboardProvider,
    ) {}



    ngOnInit() {}

    public showExample(id:string) {
        //console.log(this.selected);
        this.loading = true;
        this.ExampleTitle ='Ví dụ '+ id;
        
        this.pro.showExample(id).subscribe((res: any) => {
            //console.log(res);
            if(id=="5") {
                this.isFive = true;
                this.isNotFive = false;
                this.Brokers = res.result; 
                console.log(this.Brokers);
            }
            else{
                this.isFive = false;
                this.isNotFive = true;
                this.Cloudlets = res.result;  
            }
            
            this.ExampleDesc = res.message;
            this.ExampleDescEng = res.messageEng;
            this.loading = false;  
            this.Logging = res.log;      
        }, err => {
            this.pro.handleError(err);
            this.loading = false;
        });
      }

      public showArima()
      {
        let info:any ={vmsQty:5,cloudletQty:550}; 
        this.pro.testpost(info).subscribe((res: any) => {
            console.log(res);            
        }, err => {
            this.pro.handleError(err);
            this.loading = false;
        });
      }

      public testpost()
      {    
        let info:any ={vmsQty:5,cloudletQty:550};    
        this.pro.testpost(info).subscribe((res: any) => {
            console.log(res);            
        }, err => {
            this.pro.handleError(err);
            this.loading = false;
        });
      }
}

