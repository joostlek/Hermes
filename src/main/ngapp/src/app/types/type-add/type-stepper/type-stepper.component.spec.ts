import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TypeStepperComponent } from './type-stepper.component';

describe('TypeStepperComponent', () => {
  let component: TypeStepperComponent;
  let fixture: ComponentFixture<TypeStepperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TypeStepperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TypeStepperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
