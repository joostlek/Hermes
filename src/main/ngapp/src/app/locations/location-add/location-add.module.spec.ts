import { LocationAddModule } from './location-add.module';

describe('LocationAddModule', () => {
  let locationAddModule: LocationAddModule;

  beforeEach(() => {
    locationAddModule = new LocationAddModule();
  });

  it('should create an instance', () => {
    expect(locationAddModule).toBeTruthy();
  });
});
