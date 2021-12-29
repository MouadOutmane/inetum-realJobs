import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{
  title='Inetum-RealJobs'

  constructor(private authService: AuthenticationService) {}

  isLoggedIn(): boolean{
    return this.authService.isLoggedIn();
  }
  
  logout(){
    this.authService.logout();
  }
}
