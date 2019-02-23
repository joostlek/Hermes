import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ScreenDetailComponent} from './screen-detail.component';

describe('ScreenDetailComponent', () => {
    let component: ScreenDetailComponent;
    let fixture: ComponentFixture<ScreenDetailComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ScreenDetailComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ScreenDetailComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
