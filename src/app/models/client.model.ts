import { ClientIdentification } from "./client-identification.model";
import  {Person} from "./person.model"
export class Client{
    public constructor(
        public person:Person,
        public identification:ClientIdentification
    ){};
}