import {UserTableItem} from "@app/users/user-table/user-table-datasource";

export class User {
  id: number;
  firstName: string;
  middleName: string;
  lastName: string;
  email: string;
  role: string;
  locations: object[];
  promotions: object[];
  images: object[];
  phoneNumber: string;
  street: string;
  houseNumber: string;
  zipCode: string;
  city: string;
  country: string;


  constructor(id: number, firstName: string, middleName: string, lastName: string, email: string, role: string, locations: object[], promotions: object[], images: object[], phoneNumber: string, street: string, houseNumber: string, zipCode: string, city: string, country: string) {
    this.id = id;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.email = email;
    this.role = role;
    this.locations = locations;
    this.promotions = promotions;
    this.images = images;
    this.phoneNumber = phoneNumber;
    this.street = street;
    this.houseNumber = houseNumber;
    this.zipCode = zipCode;
    this.city = city;
    this.country = country;
  }

  getLastName(): string {
    if (this.middleName) {
      return this.middleName + ' ' + this.lastName;
    }
    return this.lastName;
  }

  getFullName(): string {
    return this.firstName + ' ' + this.getLastName();
  }

  toTable(): UserTableItem {
    return {id: this.id, name: this.getFullName(), role: this.role};
  }
}
