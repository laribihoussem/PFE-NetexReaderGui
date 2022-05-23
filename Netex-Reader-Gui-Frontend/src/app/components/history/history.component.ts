import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { HistoryService } from 'src/app/service/historyService/history.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  constructor( private historyService: HistoryService) { }

  ngOnInit(): void {
    this.getHistory();
  }
  history: any;
  p: number = 1;
  filterTerm!: string;

  public getHistory(): void {
    this.historyService.getHistory().subscribe(
      (response : any ) => {
        this.history = response;
        console.log(response);

      },
      (error: HttpErrorResponse ) => {
        alert(error.message);
      }
    )
  }
}
