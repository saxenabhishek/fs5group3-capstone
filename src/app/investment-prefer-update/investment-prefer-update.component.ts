import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { PreferencesService } from '../services/preferences.service';
import { InvestmentPreferComponent } from '../investment-prefer/investment-prefer.component';
import { Preference } from '../models/preference';

@Component({
  selector: 'app-investment-prefer-update',
  templateUrl: './investment-prefer-update.component.html',
  styleUrls: ['./investment-prefer-update.component.css']
})
export class InvestmentPreferUpdateComponent implements OnInit{
  preference!:Preference
  updateForm: FormGroup = new FormGroup({});
  constructor(private fb: FormBuilder,
    private preferenceService: PreferencesService) { }

    riskTolerance: any = ['CONSERVATIVE','BELOW AVERAGE','AVERAGE','ABOVE AVERAGE','AGGRESSIVE'];
    incomeCategory:any=['0 - 20,000','20,001 - 40,000','40,001 - 60,000','60,001 - 80,000','80,001 - 100,000','100,001 - 150,000','150,000+']
    lengthOfInvestment:any=['0-5 years','5-7 years','7-10 years','10-15 years' ]
ngOnInit(): void {
  this.getPreferences();
  this.updateForm = this.fb.group({
    purpose:['',[Validators.required,Validators.minLength(5)]],
    risk: ['', [Validators.required]],
    income:['', [Validators.required]],
    length:['', [Validators.required]]
  
});
   
}
getPreferences() {

  this.preferenceService.getPreference()
  
  .subscribe(data => this.preference = data);
  
  }
  
updatePreference(){
    let userData:any={}

    userData.purpose = this.updateForm.value.purpose;

    userData.risk = this.updateForm.value.risk; 

    userData.income = this.updateForm.value.income; 

    userData.length = this.updateForm.value.length; 

    this.preferenceService.addPreference(userData).subscribe((res)=>{

      console.log(res);

    });

  }

}






