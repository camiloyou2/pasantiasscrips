import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerSolicitudesComponent } from './ver-solicitudes.component';

describe('VerSolicitudesComponent', () => {
  let component: VerSolicitudesComponent;
  let fixture: ComponentFixture<VerSolicitudesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VerSolicitudesComponent]
    });
    fixture = TestBed.createComponent(VerSolicitudesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
