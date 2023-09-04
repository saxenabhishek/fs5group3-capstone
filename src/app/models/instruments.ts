export class Instruments {
  public constructor(
    public instrumentId: String,
    public externalIdType: String,
    public externalId: String,
    public categoryId: String,
    public instrumentDescription: String,
    public maxQuantity: Number,
    public minQuantity: Number
  ) {}
}
