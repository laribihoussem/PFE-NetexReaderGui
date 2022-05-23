import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HistoryService {

  constructor(private http: HttpClient) { }
  private apiServerUrl = environment.apiBaseUrl;

  getHistory() { return this.http.get<any>(`${this.apiServerUrl}/api/history`)}
}
