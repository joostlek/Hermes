export class User {
  id: number;
  first_name: string;
  middle_name: string;
  last_name: string;
  email: string;
  role: string;
  locations: object[];
  promotions: object[];
  images: object[];
  phone_number: string;
  street: string;
  house_number: string;
  zip_code: string;
  city: string;
  country: string;


  constructor(id: number, first_name: string, middle_name: string, last_name: string, email: string, role: string, locations: object[], promotions: object[], images: object[], phone_number: string, street: string, house_number: string, zip_code: string, city: string, country: string) {
    this.id = id;
    this.first_name = first_name;
    this.middle_name = middle_name;
    this.last_name = last_name;
    this.email = email;
    this.role = role;
    this.locations = locations;
    this.promotions = promotions;
    this.images = images;
    this.phone_number = phone_number;
    this.street = street;
    this.house_number = house_number;
    this.zip_code = zip_code;
    this.city = city;
    this.country = country;
  }
}
