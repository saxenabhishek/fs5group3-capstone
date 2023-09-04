import { Instruments } from './instruments';

describe('Instruments', () => {
  it('should create an instance', () => {
    expect(new Instruments("", "", "", "", "", 0, 0)).toBeTruthy();
  });
});
