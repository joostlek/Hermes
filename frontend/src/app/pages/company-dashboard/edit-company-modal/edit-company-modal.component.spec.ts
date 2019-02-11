import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {EditCompanyModalComponent} from './edit-company-modal.component';

describe('EditCompanyModalComponent', () => {
    let component: EditCompanyModalComponent;
    let fixture: ComponentFixture<EditCompanyModalComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [EditCompanyModalComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(EditCompanyModalComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
