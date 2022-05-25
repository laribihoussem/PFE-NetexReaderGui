import { Component, OnInit } from '@angular/core';
import { MonitoringService } from 'src/app/service/monitoringService/monitoring.service';

@Component({
  selector: 'app-file-by-route',
  templateUrl: './file-by-route.component.html',
  styleUrls: ['./file-by-route.component.css']
})
export class FileByRouteComponent implements OnInit {

  constructor(private monitoringService: MonitoringService) { }
  files: any[];
  p: number = 1;
  filterTerm!: string;

  ngOnInit(): void {
    this.getroutefileInfo()
  }

  getroutefileInfo() {
    this.monitoringService.routesFileInfo().subscribe(
      (response) => {
        this.files= response;
        console.log(this.files);
        this.files=response.reverse();
        
      }
    )
  }

}
