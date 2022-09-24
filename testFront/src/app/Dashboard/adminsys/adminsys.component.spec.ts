import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminsysComponent } from './adminsys.component';

describe('AdminsysComponent', () => {
  let component: AdminsysComponent;
  let fixture: ComponentFixture<AdminsysComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminsysComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminsysComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
