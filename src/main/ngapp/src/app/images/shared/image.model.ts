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


  constructor(id: number,
              name: string,
              promotion_name: string,
              promotion_id: number,
              user_name: string,
              user_id: number,
              screens: object[],
              height: number,
              width: number,
              url: string,
              time: number,
              active: boolean) {
    this.id = id;
    this.name = name;
    this.promotion = {};
    this.promotion["name"] = promotion_name;
    this.promotion["id"] = promotion_id;
    this.user = {};
    this.user["name"] = user_name;
    this.user["id"] = user_id;
    this.size = {};
    this.size["width"] = width;
    this.size["height"] = height;
    this.screens = screens;
    this.url = url;
    this.time = time;
    this.active = active;
  }

  public toTable() {
    return {'id': this.id, 'name': this.name, 'promotion_name': this.promotion['name'], 'promotion_id': this.promotion['id'], 'user_name': this.user['name'], 'user_id': this.user['id'], 'screens': this.screens, 'width': this.size['width'], 'height': this.size['height'], 'active': this.active}
  }
}
