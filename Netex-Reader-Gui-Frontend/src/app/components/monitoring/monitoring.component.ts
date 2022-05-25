import { Component, OnInit } from '@angular/core';
import { MonitoringService } from 'src/app/service/monitoringService/monitoring.service';
import { ChartType, ChartOptions } from 'chart.js';
import { SingleDataSet, Label, monkeyPatchChartJsLegend, monkeyPatchChartJsTooltip } from 'ng2-charts';

@Component({
  selector: 'app-monitoring',
  templateUrl: './monitoring.component.html',
  styleUrls: ['./monitoring.component.css']
})
export class MonitoringComponent implements OnInit {
  obj1: any;
  obj: any;
  obj2 : any;
  value : any;
  routes: any;
  route : any[];
 
  p: number = 1;
  filterTerm!: string;

  ngOnInit(): void {
    this.getRoutesInfo();  
    
  }

  public pieChartOptions: ChartOptions = {
    responsive: true,
    title: {
      display: true,
      text: 'Exchanges in route 1',
      position: 'top'
    }
  };
  public pieChartOptions1: ChartOptions = {
    responsive: true,
    title: {
      display: true,
      text: 'Exchanges in route 2',
      position: 'top'
    }
  };
  public pieChartLabels: Label[] = [['Completed Exchanges'], ['Failed Exchanges']];
  public pieChartData: SingleDataSet = [];
  public pieChartType: ChartType = 'pie';
  public pieChartLegend = true;
  public pieChartPlugins = [];
  public pieChartColors = [
    {
      backgroundColor: ['rgba(0,255,0,0.3)', 'rgba(255,0,0,0.3)']
    }
  ]
  public pieChartLabels1: Label[] = [['Completed Exchanges'], ['Failed Exchanges']];
  public pieChartData1: SingleDataSet = [];
  public pieChartType1: ChartType = 'pie';
  public pieChartLegend1 = true;
  public pieChartPlugins1 = [];
  constructor(private monitoringService: MonitoringService) {
    monkeyPatchChartJsTooltip();
    monkeyPatchChartJsLegend();
   }
  



  

  getRoutesInfo() {
    this.monitoringService.routesInfo().subscribe(
      (response) => {
        console.log(response);
        this.route = Object.keys(response.value).map(function(personNamedIndex){
          let person = response.value[personNamedIndex];
          // do something with person
          return person;
      });
      console.log(this.route);
        this.value = response.value;
        this.obj1 =this.value[Object.keys(this.value)[0]];
        console.log(this.obj1);  
        this.obj2 =this.value[Object.keys(this.value)[1]];
        console.log(this.obj2);
        this.pieChartData.push(this.obj1.ExchangesCompleted);
        this.pieChartData.push(this.obj1.ExchangesFailed);
        this.pieChartData1.push(this.obj2.ExchangesCompleted);
        this.pieChartData1.push(this.obj2.ExchangesFailed);
      }
    )
  }
  

}
