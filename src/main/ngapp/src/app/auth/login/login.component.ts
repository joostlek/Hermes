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
  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private router: Router) {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    })
  }

  ngOnInit() {
  }

  login() {
    if (this.form.valid) {
      const val = this.form.value;
      if (val.email && val.password) {
        this.authService.login(val.email, val.password)
          .subscribe(
            _ => {
              this.getMe();
            }
          )
      }
    } else {
      console.error(this.form.errors);
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
