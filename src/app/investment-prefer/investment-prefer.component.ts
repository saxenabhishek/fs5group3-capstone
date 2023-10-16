import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder,Validators,FormGroup } from '@angular/forms';
import { Preference } from '../models/preference';
import { PreferencesService } from '../services/pref/preferences.service';
import { ClientService } from '../services/client/client.service';
import { InvestmentLength } from '../models/InvestmentLength';
import { IncomeCategory } from '../models/IncomeCategory';
import { RiskTolerance } from '../models/RiskTolerance';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-investment-prefer',
  templateUrl: './investment-prefer.component.html',
  styleUrls: ['./investment-prefer.component.css']
})
export class InvestmentPreferComponent implements OnInit {
  investmentForm: FormGroup = new FormGroup({});
  riskTolerance: string[] = Object.values(RiskTolerance);
  incomeCategory: string[]= Object.values(IncomeCategory)
  lengthOfInvestment: string[] = Object.values(InvestmentLength);
  
  constructor(private fb: FormBuilder,
              private router: Router,
              private preferenceService: PreferencesService,
              private clientService: ClientService,
              private toastr: ToastrService
    ) { }

  ngOnInit(): void {
    this.investmentForm = this.fb.group({
      purpose:['',[Validators.required, Validators.minLength(5), Validators.maxLength(10)]],
      risk: ['', [Validators.required]],
      income:['', [Validators.required]],
      length:['', [Validators.required]],
      cb: [false, Validators.requiredTrue]});    
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
  
  // Function to convert the selected value to the enum name
  mapToEnumName(selectedValue: string): string {
    return this.enumNameMapping[selectedValue];
  }
  
  addPreference() {
    if (this.investmentForm.valid){      
      let preference= new Preference (
          this.clientService.verifyClient.clientId,
          this.investmentForm.get('purpose')?.value || '',
          this.mapToEnumName(this.investmentForm.get('risk')?.value) || '',
          this.mapToEnumName(this.investmentForm.get('income')?.value) || '',
          this.mapToEnumName(this.investmentForm.get('length')?.value) || '',
          this.investmentForm.get('cb')?.value == true ? 'T' : 'F' || ''
      )

      this.preferenceService.addPreference(preference)
        .subscribe(result => {
          if (result.rowCount == 1){
            this.toastr.success("Your preferences were successfully added", 'Success');
            this.preferenceService.newUser= false;
            this.router.navigate(['/']); 
            this.investmentForm.reset( {} );
          }
          else
            this.toastr.error("Your preferences couldn't be added", 'Error');          
        } 
      );
    }
    else
      this.toastr.error("Please fill out your preferences correctly", 'Error');
  }  
}
 



  




