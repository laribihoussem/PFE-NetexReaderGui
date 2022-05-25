import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DestinationService } from 'src/app/service/destinationService/destination.service';

@Component({
  selector: 'app-add-destination',
  templateUrl: './add-destination.component.html',
  styleUrls: ['./add-destination.component.css']
})
export class AddDestinationComponent implements OnInit {

  constructor(private destinationService : DestinationService) { }
  destinations : any;

  ngOnInit(): void {
    this.getDestination();
  }

  addNewDestination(destName: string) {
    console.log(destName);
    this.destinationService.addDestination(destName).subscribe(
      (response: any) => {
        console.log(response);
    },
      (error: HttpErrorResponse ) => {
        alert(error.message);
      }
    )
  }

  getDestination() {
    this.destinationService.getDestinations().subscribe(
      (response: any) => {
         console.log(response)
         this.destinations= response;
      },
      (error: HttpErrorResponse ) => {
        alert(error.message);
      }
    )
  }

}
