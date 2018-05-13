import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageStepperComponent } from './image-stepper.component';

describe('ImageStepperComponent', () => {
  let component: ImageStepperComponent;
  let fixture: ComponentFixture<ImageStepperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImageStepperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImageStepperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
