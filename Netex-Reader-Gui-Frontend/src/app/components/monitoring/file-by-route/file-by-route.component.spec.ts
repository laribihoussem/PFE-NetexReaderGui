import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FileByRouteComponent } from './file-by-route.component';

describe('FileByRouteComponent', () => {
  let component: FileByRouteComponent;
  let fixture: ComponentFixture<FileByRouteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FileByRouteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FileByRouteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
