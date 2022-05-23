import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginData } from "../../Model/user-login-data";
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { JwtHelperService } from '@auth0/angular-jwt';
import decode from 'jwt-decode';
import { of, Subject, Subscription } from 'rxjs';
import { delay } from 'rxjs/operators';



@Injectable({
  providedIn: 'root'
})
export class AuthenticationServiceService {
  private token: string | undefined | null;
  private isAuthenticated = false;
  private apiServerUrl = environment.apiBaseUrl;
  private tokenTimer: any;
  private roles: any;
  private authStatusListener = new Subject<boolean>();
  tokenSubscription = new Subscription();

  constructor(private http: HttpClient, private router: Router, public jwtHelper: JwtHelperService) { }


  getToken() {
    return this.token;
  }
  getIsAuth() {
    return this.isAuthenticated;
  }
  getAuthStatusListener() {
    return this.authStatusListener.asObservable();
  }

  public isUserAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    // Check whether the token is expired and return
    // true or false
    return !this.jwtHelper.isTokenExpired(token);
  }

 

  
  login(email: string , password: string) {
    let body = new URLSearchParams();
    body.set('email', email);
    body.set('password', password);
    let options = {
    headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
    };
    this.http
        .post<any>(`${this.apiServerUrl}/api/login`, body.toString(), options)
        .subscribe(response => {
          const token = response.access_token;
          this.token = token;
          if (token) {
            this.isAuthenticated = true;
            }
            console.log(this.isAuthenticated);
            this.saveAuthData(token);
            this.authStatusListener.next(true);
            const timeout = this.jwtHelper.getTokenExpirationDate(token).valueOf() - new Date().valueOf();
            console.log(timeout);
            this.expirationCounter(timeout);
      
            // decode the token to get its payload
            // let tokenPayload = decode(token);
            // console.log(tokenPayload);
            const token1 = this.jwtHelper.decodeToken(localStorage.getItem('token'));
            console.log(token1.roles);
            this.router.navigate(['home']);
        });
  }
  expirationCounter(timeout) {
    this.tokenSubscription.unsubscribe();
    this.tokenSubscription = of(null).pipe(delay(timeout)).subscribe((expired) => {
      console.log('EXPIRED!!');

      this.logout();
      this.router.navigate(["/login"]);
    });
  }


  logout() {
    this.token = null;
    this.tokenSubscription.unsubscribe();
    this.isAuthenticated = false;
    this.authStatusListener.next(false);
    clearTimeout(this.tokenTimer);
    this.clearAuthData();
    this.router.navigate(["/"]);
    
  }
  //  private setAuthTimer (duration: number) {
  //    console.log("setting timer:" + duration);
  //    this.tokenTimer = setTimeout(() => {
  //      this.logout();
  //    } , duration * 1000);
  //  }
  private saveAuthData(token: string){
    localStorage.setItem('token', token);
  }
  private clearAuthData() {
    localStorage.removeItem("token");
  }
  // private getAuthData() {
  //   const token = localStorage.getItem("token");
  //   if (!token) {
  //     return "";
  //   }
  //   return {
  //     token: token,
  //   }
  // }
}
