import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { DashboardProvider } from '../../providers/dashboard/dashboard';


@Component({
    selector: 'app-proposal-algo',
    templateUrl: './proposal-algo.component.html',
    styleUrls: ['./proposal-algo.component.scss'],
    animations: [routerTransition()]
})
export class ProposalAlgoComponent implements OnInit {
    isCollapsed:boolean = false;
    public Cloudlets: any[] = [];
    public Vms: any[] = [];
    public loading = false;
    public isNotFive = false;
    public isFive = false;
    public BrokerPredictedRT:number=0;
    
    public ExampleTitle: string ='';
    public ExampleDesc: string ='';
    public ExampleDescEng: string ='';
    public Logging: string ='';

    criteria:any ={vmsQty:3, cloudletQty:100 };

    // bar chart
    public barChartOptions: any = {
        scaleShowVerticalLines: false,
        responsive: true
    };
    public barChartLabels: string[] = [
        'This time'
    ];
    public barChartType: string = 'bar';
    public barChartLegend: boolean = true;

    public barChartData: any[] = [
        { data: [65], label: 'VM 0' },
        { data: [28], label: 'VM 1' },
        { data: [90], label: 'VM 2' },
        { data: [18], label: 'VM 3' },
        { data: [38], label: 'VM 4' },
        { data: [87], label: 'Threshold' }
    ];

    // lineChart
    public lineChartData: Array<any> = [
        { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' },
        { data: [28, 48, 40, 19, 86, 27, 90], label: 'Series B' },
        { data: [18, 48, 77, 9, 100, 27, 40], label: 'Series C' },
        { data: [98, 25, 15, 28, 35, 65, 64], label: 'Series D' },
        { data: [44, 23, 76, 90, 34, 19, 54], label: 'Series E' }
    ];
    public lineChartLabels: Array<any> = [
        '1', '2','3','4',
        '5','6', '7','8',
        '9','10','11','12',
        '13','14','15','16',
        '17','18','19','20',
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
        },
        {
            // grey
            backgroundColor: 'rgba(50,85,55,0.2)',
            borderColor: 'rgba(148,98,25,1)',
            pointBackgroundColor: 'rgba(53,159,150,1)',
            pointBorderColor: '#fff',
            pointHoverBackgroundColor: '#fff',
            pointHoverBorderColor: 'rgba(90,98,177,0.8)'
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

    

    public showArima()
    {
        //let info:any ={vmsQty:5,cloudletQty:550}; 
        //console.log(this.criteria);
        this.loading = true;
        this.pro.showArima(this.criteria).subscribe((res: any) => {               
            let broker = res.result;
            //console.log(broker); 
            this.Cloudlets = broker.cloudlets;  
            this.Vms = broker.vms;
            let chartData:any = [];
            let chartHori:any = [];
            let BarChartData:any=[];
            for(let item of broker.vms){
                let chart_item = {
                    data: item.lastRT,
                    label: 'VM ' + item.vmID
                };
                chartData.push(chart_item);
                let bar_chart_item = {
                    data: [item.predictedRT],
                    label: 'VM ' + item.vmID
                };
                BarChartData.push(bar_chart_item);
            }
            let bar_chart_item_t = {
                data: [broker.predictedRT],
                label: 'Threshold'
            };
            BarChartData.push(bar_chart_item_t);
            this.barChartData = BarChartData;
            for(var i=1; i<=broker.vms[0].lastRT.length; i++)
            {
                chartHori.push(i.toString());
            }
            this.lineChartData = chartData;
            this.lineChartLabels = chartHori;
            //console.log(chartHori);
            //console.log(broker.vms[0].lastRT.length);
            this.BrokerPredictedRT = broker.predictedRT;            
            this.loading = false;  
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

    public showBarChart(): void {
        // Only Change 3 values
        const data = [
            Math.round(Math.random() * 100),
            59,
            80,
            Math.random() * 100,
            56,
            Math.random() * 100,
            40
        ];
        const clone = JSON.parse(JSON.stringify(this.barChartData));
        clone[0].data = data;
        this.barChartData = clone;
        /**
         * (My guess), for Angular to recognize the change in the dataset
         * it has to change the dataset variable directly,
         * so one way around it, is to clone the data, change it and then
         * assign it;
         */
    }
}

