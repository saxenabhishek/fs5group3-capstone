import { Direction } from "./direction";
import { Order } from "./order";

export class Trade {
    constructor (
        public order: Order,
        public cashValue: number,
        public quantity: number,
        public direction: Direction,
        public executionPrice: number,
        public instrumentId: string,
        public clientId: string,
        public tradeId: string,
    ) { }
}
