import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PromoteImagesComponent} from './promote-images.component';

describe('PromoteImagesComponent', () => {
    let component: PromoteImagesComponent;
    let fixture: ComponentFixture<PromoteImagesComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [PromoteImagesComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(PromoteImagesComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
