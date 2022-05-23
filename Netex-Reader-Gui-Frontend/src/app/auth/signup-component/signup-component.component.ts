import { Component, OnInit } from '@angular/core';
import { RegistrationServiceService } from '../services/registrationService/registration-service.service';
import { FormBuilder, FormGroup, Validators,FormControl } from  '@angular/forms';
import { ConfirmedValidator } from './confirmedValidator';

@Component({
  selector: 'app-signup-component',
  templateUrl: './signup-component.component.html',
  styleUrls: ['./signup-component.component.css']
})
export class SignupComponentComponent implements OnInit {

  registerForm: FormGroup;

  constructor(public registrationServica: RegistrationServiceService, public FormBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.registerValidation()
  }

  registerValidation(){
    this.registerForm = this.FormBuilder.group({
          firstName: ['', Validators.required],
          lastName: ['', Validators.required],
          email: ['', [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")]],
          phoneNumber: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(8)]],
          password: ['', [Validators.required, Validators.minLength(6)]],
          confirmPassword: ['', Validators.required],
      }, {
    validator: ConfirmedValidator('password', 'confirmPassword')
  })
 }

 get getElements() { return this.registerForm.controls ; }

  onRegister(firstName: string,lastName:string ,email:string ,pwd:string, phoneNumber: string) {
    var phone= Number(phoneNumber)
    this.registrationServica.Register(firstName,lastName,email, pwd, phone);
  }

}
