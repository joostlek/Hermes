export class Type {
  id: number;
  name: string;
  time: number;
  price: number;
  active: boolean;
  exclusive: boolean;
  imageCount: number;
  location: object;


  constructor(id: number, name: string, time: number, price: number, active: boolean, exclusive: boolean,
              imageCount: number, location: object) {
    this.id = id;
    this.name = name;
    this.time = time;
    this.price = price;
    this.active = active;
    this.exclusive = exclusive;
    this.imageCount = imageCount;
    this.location = location;
  }

  toTable() {
    return {id: this.id, name: this.name, time: this.time, price: this.price, active: this.active,
      exclusive: this.exclusive, imageCount: this.imageCount, locationId: this.location['id'],
      locationName: this.location['name']};
  }
}
