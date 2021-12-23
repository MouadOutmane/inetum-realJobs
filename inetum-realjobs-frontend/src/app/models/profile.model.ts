import {Gender} from "./gender.enum";
import {Address} from "./address";

export interface Profile {
  "email": string,
  "gender": Gender,
  "firstName": string,
  "lastName": string,
  "dateOfBirth": string,
  address: Address
  "mobilePhone": number,
  "profilePicture": string
}
