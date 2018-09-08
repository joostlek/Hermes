import {ExtraOptions, RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {
  NbAuthComponent, NbAuthJWTToken,
  NbAuthModule,
  NbLoginComponent,
  NbLogoutComponent,
  NbPasswordAuthStrategy,
  NbRegisterComponent,
  NbRequestPasswordComponent,
  NbResetPasswordComponent,
} from '@nebular/auth';

const routes: Routes = [
  {path: 'pages', loadChildren: 'app/pages/pages.module#PagesModule'},
  {
    path: 'auth',
    component: NbAuthComponent,
    children: [
      {
        path: '',
        component: NbLoginComponent,
      },
      {
        path: 'login',
        component: NbLoginComponent,
      },
      {
        path: 'register',
        component: NbRegisterComponent,
      },
      {
        path: 'logout',
        component: NbLogoutComponent,
      },
      {
        path: 'request-password',
        component: NbRequestPasswordComponent,
      },
      {
        path: 'reset-password',
        component: NbResetPasswordComponent,
      },
    ],
  },
  {path: '', redirectTo: 'pages', pathMatch: 'full'},
  {path: '**', redirectTo: 'pages'},
];

const config: ExtraOptions = {
  useHash: true,
};


@NgModule({
  imports: [
    RouterModule.forRoot(routes, config),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {
}

// export const defaultSettings: any = {
//   forms: {
//     login: {
//     redirectDelay: 500, // delay before redirect after a successful login, while success message is shown to the user
//       strategy: 'email',  // strategy id key.
//       rememberMe: true,   // whether to show or not the `rememberMe` checkbox
//       showMessages: {     // show/not show success/error messages
//         success: true,
//         error: true,
//       },
//     },
//     register: {
//       redirectDelay: 500,
//       strategy: 'email',
//       showMessages: {
//         success: true,
//         error: true,
//       },
//       terms: true,
//     },
//     requestPassword: {
//       redirectDelay: 500,
//       strategy: 'email',
//       showMessages: {
//         success: true,
//         error: true,
//       },
//     },
//     resetPassword: {
//       redirectDelay: 500,
//       strategy: 'email',
//       showMessages: {
//         success: true,
//         error: true,
//       },
//     },
//     logout: {
//       redirectDelay: 500,
//       strategy: 'email',
//     },
//     validation: {
//       password: {
//         required: true,
//         minLength: 4,
//         maxLength: 50,
//       },
//       email: {
//         required: true,
//       },
//       fullName: {
//         required: false,
//         minLength: 4,
//         maxLength: 50,
//       },
//     },
//   },
// };
