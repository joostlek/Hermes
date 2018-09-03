import { ImageAddModule } from './image-add.module';

describe('ImageAddModule', () => {
  let imageAddModule: ImageAddModule;

  beforeEach(() => {
    imageAddModule = new ImageAddModule();
  });

  it('should create an instance', () => {
    expect(imageAddModule).toBeTruthy();
  });
});
