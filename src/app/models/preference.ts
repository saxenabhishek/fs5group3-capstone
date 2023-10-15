export class Preference {
    constructor(
        public id: string,
        public investmentPurpose : string, 
        public riskTolerance: string, 
        public incomeCategory:  string,
        public lengthOfInvestment: string,
        public isChecked: string
    ) { }
}
