import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CManageUsersComponent} from './c-manage-users.component';

const routes: Routes = [
    {
        path: '',
        component: CManageUsersComponent,
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class CManageUsersRoutingModule {
}
