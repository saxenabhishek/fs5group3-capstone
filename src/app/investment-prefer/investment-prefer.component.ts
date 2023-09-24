import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder,Validators,FormGroup } from '@angular/forms';
import { Preference } from '../models/preference';
import { PreferencesService } from '../services/pref/preferences.service';

@Component({
  selector: 'app-investment-prefer',
  templateUrl: './investment-prefer.component.html',
  styleUrls: ['./investment-prefer.component.css']
})
export class InvestmentPreferComponent implements OnInit {
  investmentForm: FormGroup = new FormGroup({});

  riskTolerance: any = ['CONSERVATIVE','BELOW AVERAGE','AVERAGE','ABOVE AVERAGE','AGGRESSIVE'];
  incomeCategory:any=['0 - 20,000','20,001 - 40,000','40,001 - 60,000','60,001 - 80,000','80,001 - 100,000','100,001 - 150,000','150,000+']
  lengthOfInvestment:any=['0-5 years','5-7 years','7-10 years','10-15 years' ]
  constructor(private fb: FormBuilder,
              private preferenceService: PreferencesService
    ) { }
  ngOnInit(): void {
    this.investmentForm = this.fb.group({
      purpose:['',[Validators.required,Validators.minLength(5)]],
      risk: ['', [Validators.required]],
      income:['', [Validators.required]],
      length:['', [Validators.required]],
      cb: [false, Validators.requiredTrue]});
    
  }
  
  add(preference:Preference) {
    console.log(preference);

    this.preferenceService.addPreference(preference) .subscribe();

    
    
  
  }
 
}


  




