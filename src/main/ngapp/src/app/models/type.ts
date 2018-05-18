export class Type {
  id: number;
  name: string;
  time: number;
  price: number;
  active: boolean;
  exclusive: boolean;
  numberOfImages: number;
  locationId: number;


  constructor(id: number, name: string, time: number, price: number, active: boolean, exclusive: boolean, numberOfImages: number, locationId: number) {
    this.id = id;
    this.name = name;
    this.time = time;
    this.price = price;
    this.active = active;
    this.exclusive = exclusive;
    this.numberOfImages = numberOfImages;
    this.locationId = locationId;
  }

  toTable() {
    return {'id': this.id, 'name': this.name, 'time': this.time, 'price': this.price, 'active': this.active, 'exclusive': this.exclusive, 'numberOfImages': this.numberOfImages, 'locationId': this.locationId};
  }
}
