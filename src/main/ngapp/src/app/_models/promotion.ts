export class Promotion {
  id: number;
  name: string;
  type: object;
  user: object;
  images: object[];
  location: object;
  startDate: string;


  constructor(id: number, name: string, type: object, user: object, images: object[], location: object, startDate: string) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.user = user;
    this.images = images;
    this.location = location;
    this.startDate = startDate;
  }

  toTable() {
    return {id: this.id, name: this.name, typeId: this.type['id'], typeName: this.type['name'], userId: this.user['id'],
      userName: this.user['name'], images: this.images.length, startDate: this.startDate,
      locationId: this.location['id'], locationName: this.location['name']};
  }
}