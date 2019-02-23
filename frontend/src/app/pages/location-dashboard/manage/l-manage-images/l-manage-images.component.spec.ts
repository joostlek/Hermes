import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {LManageImagesComponent} from './l-manage-images.component';

describe('LManageImagesComponent', () => {
    let component: LManageImagesComponent;
    let fixture: ComponentFixture<LManageImagesComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [LManageImagesComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(LManageImagesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
