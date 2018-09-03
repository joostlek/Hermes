import { TypesModule } from './types.module';

describe('TypesModule', () => {
  let typesModule: TypesModule;

  beforeEach(() => {
    typesModule = new TypesModule();
  });

  it('should create an instance', () => {
    expect(typesModule).toBeTruthy();
  });
});
