import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PromotionStepperComponent } from './promotion-stepper.component';

describe('PromotionStepperComponent', () => {
  let component: PromotionStepperComponent;
  let fixture: ComponentFixture<PromotionStepperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PromotionStepperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PromotionStepperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
