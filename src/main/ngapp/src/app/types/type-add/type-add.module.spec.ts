import { TypeAddModule } from './type-add.module';

describe('TypeAddModule', () => {
  let typeAddModule: TypeAddModule;

  beforeEach(() => {
    typeAddModule = new TypeAddModule();
  });

  it('should create an instance', () => {
    expect(typeAddModule).toBeTruthy();
  });
});
