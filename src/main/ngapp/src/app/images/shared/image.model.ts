export class Image {
  imageId: number;
  name: string;
  promotion: string;
  promotionId: number;
  username: string;
  userId: number;
  screens: string[];
  height: number;
  width: number;
  url: string;
  active: boolean;


  constructor(imageId: number, name: string, promotion: string, promotionId: number, username: string, userId: number, screens: string[], height: number, width: number, url: string, active: boolean) {
    this.imageId = imageId;
    this.name = name;
    this.promotion = promotion;
    this.promotionId = promotionId;
    this.username = username;
    this.userId = userId;
    this.screens = screens;
    this.height = height;
    this.width = width;
    this.url = url;
    this.active = active;
  }
}
