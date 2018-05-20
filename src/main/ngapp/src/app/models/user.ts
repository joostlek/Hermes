export class User {
  id: number;
  firstName: string;
  middleName: string;
  lastName: string;
  email: string;
  roles: string[];
  locations: object[];
  promotions: object[];
  images: object[];
  phoneNumber: string;
  street: string;
  houseNumber: string;
  zipCode: string;
  city: string;
  country: string;


  constructor(id: number, firstName: string, middleName: string, lastName: string, email: string, role: string[],
              locations: object[], promotions: object[], images: object[], phoneNumber: string, street: string,
              houseNumber: string, zipCode: string, city: string, country: string) {
    this.id = id;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.email = email;
    this.roles = role;
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

  printRoles(): string {
    let res = '';
    this.roles.map(role => res += role + ", ");
    return res.slice(0, res.length - 2);
  }

  toTable() {
    return {id: this.id, name: this.getFullName(), role: this.printRoles()};
  }
}
