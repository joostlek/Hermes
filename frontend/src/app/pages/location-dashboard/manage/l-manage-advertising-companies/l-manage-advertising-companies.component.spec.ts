import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {LManageAdvertisingCompaniesComponent} from './l-manage-advertising-companies.component';

describe('LManageAdvertisingCompaniesComponent', () => {
    let component: LManageAdvertisingCompaniesComponent;
    let fixture: ComponentFixture<LManageAdvertisingCompaniesComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [LManageAdvertisingCompaniesComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(LManageAdvertisingCompaniesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
