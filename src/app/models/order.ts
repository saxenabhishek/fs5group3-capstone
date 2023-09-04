import { Direction } from "./direction";

export class Order {
    constructor(
        public instrument: string,
        public quantity: number,
        public targetPrice: number,
        public direction: Direction,
        public clientId: string,
        public orderId: string
    ) { }
}
