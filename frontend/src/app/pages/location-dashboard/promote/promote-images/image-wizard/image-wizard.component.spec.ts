import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ImageWizardComponent} from './image-wizard.component';

describe('ImageWizardComponent', () => {
    let component: ImageWizardComponent;
    let fixture: ComponentFixture<ImageWizardComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ImageWizardComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ImageWizardComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
