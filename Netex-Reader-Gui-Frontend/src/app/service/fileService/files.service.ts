import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FilesService {

  constructor(private http: HttpClient) { }
  private apiServerUrl = environment.apiBaseUrl;

  visualise(file):Observable<any> {
    const formData = new FormData();    
    // Store form name as "file" with file data
    formData.append("file", file);  
    // Make http post request over api
    // with formData as req
    return this.http.post(`${this.apiServerUrl}/api/files`, formData, {responseType: 'text'})
  }

  upload(file, userId, inputFormat, outputFormat, destination):Observable<any> {
    // Create form data
    const formData = new FormData();    
    // Store form name as "file" with file data
    formData.append("file", file);  
    // Make http post request over api
    // with formData as req
    return this.http.post(`${this.apiServerUrl}/api/files/${userId}/${inputFormat}/${outputFormat}/${destination}`, formData, {responseType: 'text'})
  }

  getFiles() { return this.http.get<any>(`${this.apiServerUrl}/api/files/list`)}

  public getFileByid(id : number): any {
    const httpOptions: Object = {
      responseType: 'blob',
      observe: 'response'
    };
    return this.http.get<any>(`${this.apiServerUrl}/api/files/${id}`, httpOptions) }

  getFilesByUser(id: number){ return this.http.get<any>(`${this.apiServerUrl}/api/files/user/${id}`) }
}


