import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CManageUsersComponent} from './c-manage-users.component';

describe('CManageUsersComponent', () => {
    let component: CManageUsersComponent;
    let fixture: ComponentFixture<CManageUsersComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [CManageUsersComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(CManageUsersComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
