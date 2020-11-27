import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { RegisterPayload } from './register-payload';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  registerPayload : RegisterPayload;

  constructor(private formBuilder: FormBuilder) {
    
    this.registerForm = this.formBuilder.group({
      name: '',
      surname: '',
      username: '',
      email: '',
      password: '',
      confirmPassword: ''});

    this.registerPayload = {
      name: '',
      surname: '',
      username: '',
      email: '',
      password: '',
      confirmPassword: ''
    } 
  }
 
  ngOnInit(): void {
  }

  onSubmit() {
    this.registerPayload.name =  this.registerForm.get('name')?.value;
    this.registerPayload.surname = this.registerForm.get('surname')?.value;
    this.registerPayload.username = this.registerForm.get('username')?.value;
    this.registerPayload.email = this.registerForm.get('email')?.value; 
    this.registerPayload.password = this.registerForm.get('password')?.value;
    this.registerPayload.confirmPassword = this.registerForm.get('confirmPassword')?.value;
  }

}
