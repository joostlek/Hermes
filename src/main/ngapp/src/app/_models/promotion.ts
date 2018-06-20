

export class Promotion {
  id: number;
  name: string;
  type: object;
  owner: object;
  images: object[];
  startDate: string;


  constructor(promotion: object) {
    this.id = promotion['id'];
    this.name = promotion['name'];
    this.type = promotion['type'];
    this.owner = promotion['owner'];
    this.images = promotion['images'];
    this.startDate = promotion['startDate'];
  }
}
