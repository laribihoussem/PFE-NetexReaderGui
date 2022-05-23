import { Component, OnInit } from '@angular/core';
import { AuthenticationServiceService } from "../services/authenticationService/authentication-service.service";
import { FormBuilder, FormGroup, Validators,FormControl } from  '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor( public authService: AuthenticationServiceService,
     private formBuilder: FormBuilder,

    ) { }

  ngOnInit(): void {
   
  }

  get getEmail(){
    return this.loginForm.get('email')
    }
  
    get getPassword(){
    return this.loginForm.get('password')
    }
  
    loginForm = new FormGroup({
    email: new FormControl('',[
      Validators.required,
      Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]),
    password: new FormControl('',Validators.required)
    });
    onLogin(email: string,pwd: string) {
      this.authService.login(email,pwd);
    }

}
