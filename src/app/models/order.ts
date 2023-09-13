import { Direction } from "./direction";

export class Order {
    constructor(
        public instrumentId: string,
        public quantity: number,
        public targetPrice: number,
        public direction: Direction,
        public clientId: string,
        public orderId: string
    ) { }
}
