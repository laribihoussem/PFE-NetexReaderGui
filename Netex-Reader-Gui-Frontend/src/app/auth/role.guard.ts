import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationServiceService } from "./services/authenticationService/authentication-service.service";
import { JwtHelperService } from '@auth0/angular-jwt';



@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(public authService: AuthenticationServiceService, public router: Router,public jwtHelper: JwtHelperService) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    // this will be passed from the route config
    // on the data property
    const expectedRole = route.data.expectedRole;
    const token = localStorage.getItem('token');
    // decode the token to get its payload
    const tokenPayload = this.jwtHelper.decodeToken(token);

    if (
      !this.authService.isUserAuthenticated() || 
      !tokenPayload.roles.includes(expectedRole) 
    ) {
      console.log("error");
      this.router.navigate(['notFound']);
      return false;
    }
    return true;
  }
  
}
