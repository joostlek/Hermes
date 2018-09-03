import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllowimagesComponent } from './allowimages.component';

describe('AllowimagesComponent', () => {
  let component: AllowimagesComponent;
  let fixture: ComponentFixture<AllowimagesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllowimagesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllowimagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
