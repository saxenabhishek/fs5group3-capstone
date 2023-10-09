import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { PreferencesService } from '../services/pref/preferences.service';
import { InvestmentPreferComponent } from '../investment-prefer/investment-prefer.component';
import { Preference } from '../models/preference';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-investment-prefer-update',
  templateUrl: './investment-prefer-update.component.html',
  styleUrls: ['./investment-prefer-update.component.css']
})
export class InvestmentPreferUpdateComponent implements OnInit{
  updateForm: FormGroup = new FormGroup({});
  constructor(private fb: FormBuilder,
    private preferenceService: PreferencesService, private router: ActivatedRoute) { }

    riskTolerance: any = ['CONSERVATIVE','BELOW AVERAGE','AVERAGE','ABOVE AVERAGE','AGGRESSIVE'];
    incomeCategory:any=['0 - 20,000','20,001 - 40,000','40,001 - 60,000','60,001 - 80,000','80,001 - 100,000','100,001 - 150,000','150,000+']
    lengthOfInvestment:any=['0-5 years','5-7 years','7-10 years','10-15 years' ]
ngOnInit(): void {
  //console.log( this.router.snapshot.params.id );
  this.preferenceService.getPreferenceById( this.router.snapshot.params['id'] ).subscribe( ( result: any ) => {
    //console.log( result );
    this.updateForm = this.fb.group({
      purpose:['',[Validators.required,Validators.minLength(5)]],
      risk: ['', [Validators.required]],
      income:['', [Validators.required]],
      length:['', [Validators.required]]
    
  });
  } );
}
update() {

  this.preferenceService.updatePreference( this.router.snapshot.params['id'], this.updateForm.value ).subscribe( ( result ) => {
    //console.log( result );
  
  } )
}

}






