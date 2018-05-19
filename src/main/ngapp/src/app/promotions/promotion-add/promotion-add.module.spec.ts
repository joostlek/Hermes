import { PromotionAddModule } from './promotion-add.module';

describe('PromotionAddModule', () => {
  let promotionAddModule: PromotionAddModule;

  beforeEach(() => {
    promotionAddModule = new PromotionAddModule();
  });

  it('should create an instance', () => {
    expect(promotionAddModule).toBeTruthy();
  });
});
