import { PromotionsModule } from './promotions.module';

describe('PromotionsModule', () => {
  let promotionsModule: PromotionsModule;

  beforeEach(() => {
    promotionsModule = new PromotionsModule();
  });

  it('should create an instance', () => {
    expect(promotionsModule).toBeTruthy();
  });
});
