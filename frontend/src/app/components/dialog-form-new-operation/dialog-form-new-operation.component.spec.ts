import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogFormNewOperationComponent } from './dialog-form-new-operation.component';

describe('DialogFormNewOperationComponent', () => {
  let component: DialogFormNewOperationComponent;
  let fixture: ComponentFixture<DialogFormNewOperationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogFormNewOperationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogFormNewOperationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
