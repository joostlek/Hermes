import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ScreenStepperComponent } from './screen-stepper.component';

describe('ScreenStepperComponent', () => {
  let component: ScreenStepperComponent;
  let fixture: ComponentFixture<ScreenStepperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ScreenStepperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ScreenStepperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
