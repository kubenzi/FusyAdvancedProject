import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogFormCategoryComponent } from './dialog-form-category.component';

describe('DialogFormCategoryComponent', () => {
  let component: DialogFormCategoryComponent;
  let fixture: ComponentFixture<DialogFormCategoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogFormCategoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogFormCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
