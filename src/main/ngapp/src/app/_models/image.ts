export class Image {
  id: number;
  name: string;
  promotion: object;
  user: object;
  screens: object[];
  size: object;
  url: string;
  time: number;
  active: boolean;


  constructor(id: number, name: string, promotion: object, user: object, screens: object[], size: object, url: string,
              time: number, active: boolean) {
    this.id = id;
    this.name = name;
    this.promotion = promotion;
    this.user = user;
    this.screens = screens;
    this.size = size;
    this.url = url;
    this.time = time;
    this.active = active;
  }

  public toTable() {
    return {id: this.id, name: this.name, promotionName: this.promotion['name'], promotionId: this.promotion['id'],
      userId: this.user['id'], userName: this.user['name'], width: this.size['width'], height: this.size['height'],
      time: this.time, active: this.active};
  }
}
