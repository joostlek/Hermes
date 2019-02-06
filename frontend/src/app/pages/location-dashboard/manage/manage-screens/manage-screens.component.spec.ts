import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ManageScreensComponent} from './manage-screens.component';

describe('ManageScreensComponent', () => {
    let component: ManageScreensComponent;
    let fixture: ComponentFixture<ManageScreensComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ManageScreensComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ManageScreensComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
