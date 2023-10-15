import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { PreferencesService } from '../services/pref/preferences.service';
import { Preference } from '../models/preference';
import { ClientService } from '../services/client/client.service';
import { RiskTolerance } from '../models/RiskTolerance';
import { IncomeCategory } from '../models/IncomeCategory';
import { InvestmentLength } from '../models/InvestmentLength';

@Component({
  selector: 'app-investment-prefer-update',
  templateUrl: './investment-prefer-update.component.html',
  styleUrls: ['./investment-prefer-update.component.css']
})
export class InvestmentPreferUpdateComponent implements OnInit{
  updateForm: FormGroup = new FormGroup({
    purpose: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(10)]),
    risk: new FormControl('', [Validators.required]),
    income: new FormControl('', [Validators.required]),
    length: new FormControl('', [Validators.required])
  });

  riskTolerance: string[] = Object.values(RiskTolerance);
  incomeCategory: string[]= Object.values(IncomeCategory)
  lengthOfInvestment: string[] = Object.values(InvestmentLength);

  constructor(private fb: FormBuilder,
    private preferenceService: PreferencesService,
    private clientService: ClientService
  ) { }

  ngOnInit() {
  this.preferenceService.getPreferenceById(this.clientService.verifyClient.clientId)
    .subscribe(preference =>{
      if(preference != null){
      this.updateForm.get('purpose')?.patchValue(preference.investmentPurpose);        
      this.updateForm.get('risk')?.patchValue(this.mapToReversedEnumName(preference.riskTolerance));
      this.updateForm.get('income')?.patchValue(this.mapToReversedEnumName(preference.incomeCategory));
      this.updateForm.get('length')?.patchValue(this.mapToReversedEnumName(preference.lengthOfInvestment));}
    });
  }

  enumNameMapping: { [key: string]: string } = {
    '0 - 20,000': 'ZeroToTwentyK',
    '20,001 - 40,000': 'TwentyKToFortyK',
    '40,001 - 60,000': 'FortyKToSixtyK',
    '60,001 - 80,000': 'SixtyKToEigthyK',
    '80,001 - 100,000': 'EigthyKToOneL',
    '100,001 - 150,000': 'OneLToOneLFifty',
    '150,000+': 'Above',

    '0-5 years': 'ZeroToFiveYears', 
    '5-7 years': 'FiveToSevenYears', 
    '7-10 years': 'SevenToTenYears', 
    '10-15 years': 'TenToFifteenYears',

    'CONSERVATIVE': 'CONSERVATIVE', 
    'BELOW AVERAGE': 'BELOW_AVERAGE', 
    'AVERAGE': 'AVERAGE', 
    'ABOVE AVERAGE': 'ABOVE_AVERAGE',
    'AGGRESSIVE': 'AGGRESSIVE'
  };

  reversedEnumNameMapping: {[key: string]: string}= Object.keys(this.enumNameMapping).reduce((reversed, key) => {
    reversed[this.enumNameMapping[key]] = key;
    return reversed;
  }, {} as { [key: string]: string });
  
  mapToEnumName(selectedValue: string): string {
    return this.enumNameMapping[selectedValue];
  }

  mapToReversedEnumName(selectedValue: string): string {
    return this.reversedEnumNameMapping[selectedValue];
  }

  update() {
    if (this.updateForm.valid){      
      let preference= new Preference (
        this.clientService.verifyClient.clientId,
        this.updateForm.get('purpose')?.value || '',
        this.mapToEnumName(this.updateForm.get('risk')?.value) || '',
        this.mapToEnumName(this.updateForm.get('income')?.value) || '',
        this.mapToEnumName(this.updateForm.get('length')?.value) || '',
        'T'
      )
      console.log(preference)

      this.preferenceService.updatePreference(preference)
        .subscribe(result => {
          if (result.rowCount == 1){
            alert("Preference successfully updated")
            this.updateForm.reset( {} );
          }
          else{
            alert("Preference couldn't be updated")
          }
        }
      );
    }
  }  
}






