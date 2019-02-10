import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateLocationWizardComponent } from './create-location-wizard.component';

describe('CreateLocationWizardComponent', () => {
  let component: CreateLocationWizardComponent;
  let fixture: ComponentFixture<CreateLocationWizardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateLocationWizardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateLocationWizardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
