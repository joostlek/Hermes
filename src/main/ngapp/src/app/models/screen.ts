export class Screen {
  id: number;
  name: string;
  height: number;
  width: number;
  location: object;


  constructor(id: number, name: string, height: number, width: number, location: object) {
    this.id = id;
    this.name = name;
    this.height = height;
    this.width = width;
    this.location = location;
  }

  toTable() {
    return {id: this.id, name: this.name, height: this.height, width: this.width, locationId: this.location['id'],
      locationName: this.location['name']};
  }
}
