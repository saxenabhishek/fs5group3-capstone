export class Instruments {
  public constructor(
    public instrumentId: String,
    public externalIdType: String,
    public externalId: String,
    public categoryId: string,
    public instrumentDescription: string,
    public maxQuantity: Number,
    public minQuantity: Number
  ) {}
}
