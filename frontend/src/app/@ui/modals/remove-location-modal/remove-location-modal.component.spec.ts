import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {RemoveLocationModalComponent} from './remove-location-modal.component';

describe('RemoveLocationModalComponent', () => {
  let component: RemoveLocationModalComponent;
  let fixture: ComponentFixture<RemoveLocationModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RemoveLocationModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RemoveLocationModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
