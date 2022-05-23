import { Injectable } from '@angular/core';
import { RegisterData } from '../../Model/userRegisterData';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class RegistrationServiceService {

  constructor(private http: HttpClient, private router: Router) { }
  private apiServerUrl = environment.apiBaseUrl;

  

  Register(firstName: string , lastName: string,email: string , password: string, phoneNumber: number) {
     const registerData: RegisterData= {firstName: firstName, lastName: lastName, email: email, password: password, phoneNumber: phoneNumber};
      console.log(registerData)    
      this.http 
        .post<any>(`${this.apiServerUrl}/api/user/save`, registerData)
          .subscribe(response => {
            if(response){
              console.log("user successfully created");
              console.log(response);
              this.router.navigate(['/login']);
            }else {
              console.log("user already exists");
            }
            
          });
        };
}
