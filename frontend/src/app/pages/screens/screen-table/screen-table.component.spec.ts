import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ScreenTableComponent} from './screen-table.component';

describe('ScreenTableComponent', () => {
    let component: ScreenTableComponent;
    let fixture: ComponentFixture<ScreenTableComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ScreenTableComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ScreenTableComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
