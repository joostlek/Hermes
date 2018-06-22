import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "@app/_services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  hide = true;
  validLogin: boolean = true;
  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private router: Router) {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    })
  }

  get email() { return this.form.get('email'); }

  get password() { return this.form.get('password'); }

  ngOnInit() {
  }

  getErrorMessage(field) {
    return field.hasError('required') ? 'You must enter a value' :
      field.hasError('email') ? 'You must enter a valid email' :
        field.hasError('pattern') ? 'You must enter a valid value' :
          '';
  }

  login() {
    if (this.form.valid) {
      const val = this.form.value;
      if (val.email && val.password) {
        this.authService.login(val.email, val.password)
          .subscribe(
            _ => {
              if (_ == null) {
                console.error('nothing found');
                this.validLogin = false;
              } else {
                this.getMe();

              }
            }
          )
      }
    }
  }

  getMe() {
    this.authService.getMe()
      .subscribe(user => {
        localStorage.setItem('user', JSON.stringify(user));
        this.router.navigateByUrl('/');
      })
  }
}
