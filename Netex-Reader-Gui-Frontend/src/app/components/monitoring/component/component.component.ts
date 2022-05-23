import { Component, OnInit } from '@angular/core';
import { MonitoringService } from 'src/app/service/monitoringService/monitoring.service';

@Component({
  selector: 'app-component',
  templateUrl: './component.component.html',
  styleUrls: ['./component.component.css']
})
export class ComponentComponent implements OnInit {

  constructor(private monitoringService: MonitoringService) { }
  components : any[];

  ngOnInit(): void {
    this.getcomponentsInfo();
  }


  getcomponentsInfo() {
    this.monitoringService.componentsInfo().subscribe(
      (response) => {
        console.log(response);
        this.components=  Object.keys(response.value).map(function(componentNamedIndex){
          let person = response.value[componentNamedIndex];
          // do something with person
          return person;
      });
      }
    )
  }
}
