import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutentificarComponent } from './autentificar.component';

describe('AutentificarComponent', () => {
  let component: AutentificarComponent;
  let fixture: ComponentFixture<AutentificarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AutentificarComponent]
    });
    fixture = TestBed.createComponent(AutentificarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
