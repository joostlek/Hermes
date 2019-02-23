import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {LManageScreensComponent} from './l-manage-screens.component';

describe('LManageScreensComponent', () => {
    let component: LManageScreensComponent;
    let fixture: ComponentFixture<LManageScreensComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [LManageScreensComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(LManageScreensComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
