import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import { Subscription } from 'rxjs';
import { AuthenticationServiceService } from 'src/app/auth/services/authenticationService/authentication-service.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor( private authenticationService : AuthenticationServiceService) { }
  userIsAuthenticated : boolean;
  private authListenerSubs: Subscription;

  ngOnInit(): void {
    this.userIsAuthenticated = this.authenticationService.isUserAuthenticated();
    console.log("user",this.userIsAuthenticated);
    this.authListenerSubs = this.authenticationService
    .getAuthStatusListener()
    .subscribe(isAuthentificated => {
      this.userIsAuthenticated = isAuthentificated;
    });
  }

  logout() {
    this.authenticationService.logout();
    this.userIsAuthenticated=false;
  }
  
  
  

}
