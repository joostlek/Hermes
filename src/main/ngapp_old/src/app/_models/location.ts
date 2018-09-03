export class Location {
  id: number;
  name: string;
  owner: object;
  screens: object[];
  street: string;
  houseNumber: string;
  zipCode: string;
  city: string;
  country: string;

  constructor(id: number, name: string, owner: object, screens: object[], street: string, houseNumber: string,
              zipCode: string, city: string, country: string) {
    this.id = id;
    this.name = name;
    this.owner = owner;
    this.screens = screens;
    this.street = street;
    this.houseNumber = houseNumber;
    this.zipCode = zipCode;
    this.city = city;
    this.country = country;
  }

  toTable() {
    return {
      id: this.id, name: this.name, ownerId: this.owner['id'], ownerName: this.owner['name'],
      screens: this.screens.length};
  }
}
