export class Screen {
  id: number;
  name: string;
  height: number;
  width: number;
  location: object;
  thirdParty: boolean;


  constructor(id: number, name: string, height: number, width: number, location: object, thirdParty: boolean) {
    this.id = id;
    this.name = name;
    this.height = height;
    this.width = width;
    this.location = location;
    this.thirdParty = thirdParty;
  }

  toTable() {
    return {id: this.id, name: this.name, height: this.height, width: this.width, locationId: this.location['id'],
      locationName: this.location['name'], thirdParty: this.thirdParty};
  }
}
