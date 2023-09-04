import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StockPageComponent } from './stock-page.component';

describe('PortfolioPageComponent', () => {
  let component: StockPageComponent;
  let fixture: ComponentFixture<StockPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StockPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StockPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
