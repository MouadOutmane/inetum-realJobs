import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Profile} from "../models/profile.model";

// @ts-ignore
@Injectable({
  providedIn: 'root'
})


export class ProfileService {

  constructor(private http: HttpClient) {
  }

  getUserProfile(): Observable<Profile> {
    return this.http.get<Profile>("http://localhost:8080/api/profile/",
      {responseType: 'json'});
  }
}
