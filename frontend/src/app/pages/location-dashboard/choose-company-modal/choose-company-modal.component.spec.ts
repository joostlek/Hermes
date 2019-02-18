import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ChooseCompanyModalComponent} from './choose-company-modal.component';

describe('ChooseCompanyModalComponent', () => {
    let component: ChooseCompanyModalComponent;
    let fixture: ComponentFixture<ChooseCompanyModalComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ChooseCompanyModalComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ChooseCompanyModalComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
