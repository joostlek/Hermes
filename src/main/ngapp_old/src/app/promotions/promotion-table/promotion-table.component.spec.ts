
import { fakeAsync, ComponentFixture, TestBed } from '@angular/core/testing';

import { PromotionTableComponent } from './promotion-table.component';

describe('PromotionTableComponent', () => {
  let component: PromotionTableComponent;
  let fixture: ComponentFixture<PromotionTableComponent>;

  beforeEach(fakeAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ PromotionTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PromotionTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should compile', () => {
    expect(component).toBeTruthy();
  });
});
