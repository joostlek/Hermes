import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';

import {ReactiveFormsModule} from '@angular/forms';
import {ClarityModule, ClrFormsModule} from '@clr/angular';
import {ChosenLocationService} from './chosen-location.service';
import { EditLocationModalComponent } from './edit-location-modal/edit-location-modal.component';
import {LocationDashboardRoutingModule} from './location-dashboard-routing.module';
import {LocationDashboardComponent} from './location-dashboard.component';
import {LocationDetailComponent} from './location-detail/location-detail.component';

@NgModule({
    declarations: [LocationDashboardComponent, LocationDetailComponent, EditLocationModalComponent],
    imports: [
        CommonModule,
        ClarityModule,
        ClrFormsModule,
        ReactiveFormsModule,
        LocationDashboardRoutingModule,
    ],
    providers: [
        ChosenLocationService,
    ],
})
export class LocationDashboardModule {
}
