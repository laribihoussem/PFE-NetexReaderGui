import { Component } from '@angular/core';
import { AuthenticationServiceService } from "./auth/services/authenticationService/authentication-service.service";
import { Subscription } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Netex-Reader-Gui-Frontend';

  opened = false;
  isAdmin= false;
  
  constructor( private authenticationService : AuthenticationServiceService, public jwtHelper: JwtHelperService) { }
  userIsAuthenticated : boolean;
  private authListenerSubs: Subscription;

  ngOnInit(): void {
    this.userIsAuthenticated = this.authenticationService.isUserAuthenticated();
    //console.log("user",this.userIsAuthenticated);
    this.authListenerSubs = this.authenticationService
    .getAuthStatusListener()
    .subscribe(isAuthentificated => {
      this.userIsAuthenticated = isAuthentificated;
    });
    this.getRoles();
  }

  getRoles() {
    const token = this.jwtHelper.decodeToken(localStorage.getItem('token'));
    //console.log(token1.roles);
    if( token.roles.includes("ROLE_ADMIN")){
      this.isAdmin=true;
    }else this.isAdmin=false;
  }

  toggleSidebar() {
    this.opened= !this.opened;
  }

  logout() {
    // this.authenticationService.logout();
    // this.userIsAuthenticated=false;
    Swal.fire({
      title: 'Do you really want to logout?',
    showCancelButton: true,
    confirmButtonText: 'Yes',
    
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        Swal.fire('loged out successfuly!', '', 'success')
        this.authenticationService.logout();
        this.userIsAuthenticated=false;}
    })
  }

}
