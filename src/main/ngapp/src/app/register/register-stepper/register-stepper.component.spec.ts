import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterStepperComponent } from './register-stepper.component';

describe('RegisterStepperComponent', () => {
  let component: RegisterStepperComponent;
  let fixture: ComponentFixture<RegisterStepperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterStepperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterStepperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
