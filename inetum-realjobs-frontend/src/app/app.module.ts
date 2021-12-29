import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { AppComponent } from './app.component';
import { FilterFormComponent } from './components/filter-form/filter-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { routing } from './app.routing';
import { LoginComponent } from './components/login/login.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { UniversalAppInterceptor } from 'src/app/interceptor/universalAppInterceptor';
import { AuthGuardService } from './guard/authGuard.service';
import{AccordionModule} from 'primeng/accordion';
import {TableModule} from 'primeng/table';
import {KeyFilterModule} from 'primeng/keyfilter';
import {DropdownModule} from 'primeng/dropdown';
import {ButtonModule} from 'primeng/button';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {SliderModule} from 'primeng/slider';
import { PanelModule } from "primeng/panel";
import{BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MessagesModule} from "primeng/messages";
import {MessageModule} from 'primeng/message';
import { VacancyCreationComponent } from './components/vacancy-creation/vacancy-creation.component';
import { InputTextModule } from 'primeng/inputtext';
import {ScrollPanelModule} from 'primeng/scrollpanel';
import { SearchComponent } from './components/search/search.component';



@NgModule({
  declarations: [
    AppComponent,
    FilterFormComponent,
    LoginComponent,
    UserDetailsComponent,
    VacancyCreationComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AccordionModule,
    TableModule,
    KeyFilterModule,
    DropdownModule,
    ButtonModule,
    InputTextareaModule,
    SliderModule,
    InputTextModule,
    PanelModule,
    BrowserAnimationsModule,
    MessagesModule,
    MessageModule,
    ScrollPanelModule,
    routing
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: UniversalAppInterceptor, multi: true },
    AuthGuardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
