import { Component } from '@angular/core';
import { AuthenticationServiceService } from "./auth/services/authenticationService/authentication-service.service";
import { Subscription } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import Swal from 'sweetalert2';
import { ProgressWebsocketService } from './service/websocketService/progress.websocket.service';
import { UserService } from './service/userservice/user.service';
import { UserData } from './models/userData';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Netex-Reader-Gui-Frontend';

  opened = false;
  isAdmin:  boolean;
  isUser: boolean;
  progress: any = {};
  message : any[];
  actualEmail: any;
  actualid: any;
  actualUser:any;
  id: any;
  link: any;
  name: any;
  

  constructor( private authenticationService : AuthenticationServiceService,
     public jwtHelper: JwtHelperService,
      private userService: UserService,
     private progressWebsocketService: ProgressWebsocketService) { }
  userIsAuthenticated : boolean;
  private authListenerSubs: Subscription;
  private adminListenerSubs: Subscription;
  private userListenerSubs: Subscription;

  ngOnInit(): void {
    const tokenDecoded = this.jwtHelper.decodeToken(localStorage.getItem('token'));
    this.actualEmail=tokenDecoded.sub;
    this.getActualUser(this.actualEmail); 
    this.userIsAuthenticated = this.authenticationService.isUserAuthenticated();
    this.isAdmin = this.authenticationService.getIsAdmin();
    
    this.isUser = this.authenticationService.getIsUser();
    //console.log("user",this.userIsAuthenticated);
    this.authListenerSubs = this.authenticationService
    .getAuthStatusListener()
    .subscribe(isAuthentificated => {
      this.userIsAuthenticated = isAuthentificated;
    });
    
    this.adminListenerSubs = this.authenticationService.getAdminStatusListener().subscribe(isAdmin => {
      this.isAdmin = isAdmin;
    });
    

    this.userListenerSubs = this.authenticationService.getUserStatusListener().subscribe(isUser => {
      this.isUser = isUser;
    })
    
    this.initProgressWebSocket();
  }

  // getRoles() {
  //   const token = this.jwtHelper.decodeToken(localStorage.getItem('token'));
  //   //console.log(token1.roles);
  //   if( token.roles.includes("ROLE_ADMIN")){
  //     this.isAdmin=true;
  //   }else this.isAdmin=false;
  //   if( token.roles.includes("ROLE_USER")){
  //     this.isUser=true;
  //   }else this.isUser=false;

    
  // }

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

  private initProgressWebSocket = () => {
    const obs = this.progressWebsocketService.getObservable();

    obs.subscribe({
      next: this.onNewProgressMsg,
      error: err => {
        console.log(err);
      }
    });
  }

  /**
   * Apply result of the java server notification to the view.
   */
  private onNewProgressMsg = receivedMsg => {
    if (receivedMsg.type === 'SUCCESS') {
    //    this.message = Object.keys(receivedMsg.message).map(function(personNamedIndex){
    //      let person = receivedMsg.message[personNamedIndex];
    //      // do something with person
    //      return person;
    //  });
     //console.log(this.message);
      this.progress = receivedMsg.message;
      console.log(JSON.parse(JSON.stringify(receivedMsg.message)));
      this.id = this.progress[Object.keys(this.progress)[2]]
      this.link = this.progress[Object.keys(this.progress)[1]]
      this.name =this.progress[Object.keys(this.progress)[0]]
      if(this.id== this.actualid) {
        Swal.fire({
          title: '<strong>Message Sent</strong>',
          icon: 'info',
          html:
            'You can use <b>Preview</b> the file by clicking on  , ' +
            '<a href='+this.link+'>link</a> ',
          showCloseButton: true,
          showCancelButton: true,
          focusConfirm: false,
          confirmButtonText:
            '<i class="fa fa-thumbs-up"></i> Great!',
          confirmButtonAriaLabel: 'Thumbs up, great!',
          cancelButtonText:
            '<i class="fa fa-thumbs-down"></i>',
          cancelButtonAriaLabel: 'Thumbs down'
        })
        // Swal.fire(
        //   'Successfully sent! ' + this.name,
        //   'preview on : ' + this.link
        // )
      }
      
    }
  }

  getActualUser(email) {
    this.userService.getUserByEmail(email).subscribe(
      (response : UserData ) => {
        this.actualUser = response;
        this.actualid= this.actualUser.id;
      },
        (error: HttpErrorResponse ) => {
             alert(error.message);
        }
    );
  }


}
