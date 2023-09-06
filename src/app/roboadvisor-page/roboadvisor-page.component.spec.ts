import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoboadvisorPageComponent } from './roboadvisor-page.component';

describe('RoboadvisorPageComponent', () => {
  let component: RoboadvisorPageComponent;
  let fixture: ComponentFixture<RoboadvisorPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RoboadvisorPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RoboadvisorPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
