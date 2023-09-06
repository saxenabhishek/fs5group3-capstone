import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvestmentPreferComponent } from './investment-prefer.component';

describe('InvestmentPreferComponent', () => {
  let component: InvestmentPreferComponent;
  let fixture: ComponentFixture<InvestmentPreferComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InvestmentPreferComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InvestmentPreferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
