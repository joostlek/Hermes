import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LocationStepperComponent } from './location-stepper.component';

describe('LocationStepperComponent', () => {
  let component: LocationStepperComponent;
  let fixture: ComponentFixture<LocationStepperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LocationStepperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LocationStepperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
