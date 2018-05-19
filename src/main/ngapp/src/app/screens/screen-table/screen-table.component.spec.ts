
import { fakeAsync, ComponentFixture, TestBed } from '@angular/core/testing';

import { ScreenTableComponent } from './screen-table.component';

describe('ScreenTableComponent', () => {
  let component: ScreenTableComponent;
  let fixture: ComponentFixture<ScreenTableComponent>;

  beforeEach(fakeAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ScreenTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScreenTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should compile', () => {
    expect(component).toBeTruthy();
  });
});
