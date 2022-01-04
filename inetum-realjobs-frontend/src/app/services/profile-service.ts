import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Profile} from "../models/profile.model";

// @ts-ignore
@Injectable({
  providedIn: 'root'
})


export class ProfileService {

  constructor(private http: HttpClient) {
  }

  getUserProfile(): Observable<Profile> {
    return this.http.get<Profile>("profile/",
      {responseType: 'json'});
  }
}
