import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvestmentPreferUpdateComponent } from './investment-prefer-update.component';

describe('InvestmentPreferUpdateComponent', () => {
  let component: InvestmentPreferUpdateComponent;
  let fixture: ComponentFixture<InvestmentPreferUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InvestmentPreferUpdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InvestmentPreferUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
