import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { UserData } from 'src/app/models/userData';
import { Observable } from 'rxjs';
import { User } from "src/app/models/user";
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  private apiServerUrl = environment.apiBaseUrl;

  getAll() {
    return this.http.get<User[]>(`${this.apiServerUrl}/api/users`);
  }
  getAllUsers(page:number,sizePage:number,name:String ) {
    let params = new HttpParams();
    params = params.append('name', name.toString());
    params = params.append('page', page.toString());
    params = params.append('size', sizePage.toString());
    return this.http.get<any>(`${this.apiServerUrl}/api/getAll`, { params: params });
  }
  getUserByEmail(email: string){
    return this.http.get<UserData>(`${this.apiServerUrl}/api/user/${email}`)
  }
  public updateUser(user: UserData): Observable<UserData> {
    return this.http.put<UserData> (`${this.apiServerUrl}/api/user/update`, user);
  }


  public enable(userId: number): Observable<any> {
    return this.http.put<any> (`${this.apiServerUrl}/api/user/enable/${userId}`, {});
  }

  public desable(userId: number): Observable<any> {
    return this.http.put<any> (`${this.apiServerUrl}/api/user/desable/${userId}`, {});
  }

  public deleteUser(userId: number): Observable<void> {
    return this.http.delete<void> (`${this.apiServerUrl}/api/user/delete/${userId}`);
  }

  public addRoleToUser(email: string, roleName: String) {
    const userRole= {email: email, roleName: roleName};
    console.log(userRole);
    return this.http.post<any> (`${this.apiServerUrl}/api/role/addtouser`, userRole);
  }
  
}
