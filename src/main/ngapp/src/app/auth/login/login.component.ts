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
            user => {
              console.log("User is logged in");
              localStorage.setItem('user', JSON.stringify(user));
              this.router.navigateByUrl('/');
            }
          );
      }
    } else {
      console.error(this.form.errors);
    }
  }
}
