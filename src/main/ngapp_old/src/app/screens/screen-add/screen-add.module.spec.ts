import { ScreenAddModule } from './screen-add.module';

describe('ScreenAddModule', () => {
  let screenAddModule: ScreenAddModule;

  beforeEach(() => {
    screenAddModule = new ScreenAddModule();
  });

  it('should create an instance', () => {
    expect(screenAddModule).toBeTruthy();
  });
});
