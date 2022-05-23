import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MonitoringService {

  constructor(private http: HttpClient) { }
  private apiServerUrl = environment.apiBaseUrl;

  routesInfo(){ return this.http.get<any>(`${this.apiServerUrl}/actuator/jolokia/read/org.apache.camel:context=*,type=routes,name=*`) }
  routesFileInfo(){ return this.http.get<any>(`${this.apiServerUrl}/api/files/routes`) }
  endpointsInfo(){ return this.http.get<any>(`${this.apiServerUrl}/actuator/jolokia/read/org.apache.camel:context=*,type=endpoints,name=*`) }
  componentsInfo(){ return this.http.get<any>(`${this.apiServerUrl}/actuator/jolokia/read/org.apache.camel:context=*,type=components,name=*`) }
  

}
