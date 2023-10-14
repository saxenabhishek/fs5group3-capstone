export class Preference {
    constructor(
        public clientId: string,
        public purpose : string, 
        public risk: string, 
        public income:  string,
        public length: string
        ) { }
}
