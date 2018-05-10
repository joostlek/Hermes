
import { fakeAsync, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageTableComponent } from './image-table.component';

describe('ImageTableComponent', () => {
  let component: ImageTableComponent;
  let fixture: ComponentFixture<ImageTableComponent>;

  beforeEach(fakeAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ImageTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ImageTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should compile', () => {
    expect(component).toBeTruthy();
  });
});
