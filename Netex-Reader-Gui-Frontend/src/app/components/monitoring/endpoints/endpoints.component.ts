import { Component, OnInit } from '@angular/core';
import { MonitoringService } from 'src/app/service/monitoringService/monitoring.service';

@Component({
  selector: 'app-endpoints',
  templateUrl: './endpoints.component.html',
  styleUrls: ['./endpoints.component.css']
})
export class EndpointsComponent implements OnInit {

  constructor(private monitoringService: MonitoringService) { }
  endpoints : any[];

  ngOnInit(): void {
    this.getendpointsInfo();
  }

  getendpointsInfo() {
    this.monitoringService.endpointsInfo().subscribe(
      (response) => {
        this.endpoints=  Object.keys(response.value).map(function(personNamedIndex){
          let person = response.value[personNamedIndex];
          // do something with person
          return person;
      });
      }
    )
  }

}
