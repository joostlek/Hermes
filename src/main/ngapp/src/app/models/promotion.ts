export class Promotion {
  id: number;
  name: string;
  typeId: number;
  userId: number;
  images: object[];
  startDate: string;


  constructor(id: number, name: string, typeId: number, userId: number, images: object[], startDate: string) {
    this.id = id;
    this.name = name;
    this.typeId = typeId;
    this.userId = userId;
    this.images = images;
    this.startDate = startDate;
  }

  toTable() {
    return {'id': this.id, 'name': this.name, 'typeId': this.typeId, 'userId': this.userId, 'images': this.images.length, 'startDate': this.startDate};
  }
}
