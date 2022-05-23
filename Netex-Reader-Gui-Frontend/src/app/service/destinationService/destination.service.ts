import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DestinationService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  getDestinations() { return this.http.get<any>(`${this.apiServerUrl}/api/destination/all`)}

  addDestination(name) {
    const body= {name: name};
    return this.http.post(`${this.apiServerUrl}/api/destination`, body,  {responseType: 'text'})

  }

}
