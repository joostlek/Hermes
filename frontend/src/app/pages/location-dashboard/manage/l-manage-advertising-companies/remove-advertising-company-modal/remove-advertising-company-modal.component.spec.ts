import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RemoveAdvertisingCompanyModalComponent } from './remove-advertising-company-modal.component';

describe('RemoveAdvertisingCompanyModalComponent', () => {
  let component: RemoveAdvertisingCompanyModalComponent;
  let fixture: ComponentFixture<RemoveAdvertisingCompanyModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RemoveAdvertisingCompanyModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RemoveAdvertisingCompanyModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
