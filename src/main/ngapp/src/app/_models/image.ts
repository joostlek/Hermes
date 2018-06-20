export class Image {
  id: number;
  name: string;
  promotion: object;
  owner: object;
  screens: object[];
  size: object;
  url: string;
  time: number;
  active: boolean;


  constructor(id: number, name: string, promotion: object, owner: object, screens: object[], size: object, url: string,
              time: number, active: boolean) {
    this.id = id;
    this.name = name;
    this.promotion = promotion;
    this.owner = owner;
    this.screens = screens;
    this.size = size;
    this.url = url;
    this.time = time;
    this.active = active;
  }
}
