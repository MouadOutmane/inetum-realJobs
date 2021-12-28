import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AppComponent} from "./app.component";
import {FilterFormComponent} from "./components/filter-form/filter-form.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {routing} from "./app.routing";
import {LoginComponent} from "./components/login/login.component";
import {UserDetailsComponent} from "./components/user-details/user-details.component";
import {UniversalAppInterceptor} from "src/app/interceptor/universalAppInterceptor";
import {AuthGuardService} from "./guard/authGuard.service";
import {RegisterComponent} from "./components/register/register.component";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {VacancyDetailsComponent} from "./components/vacancy-details/vacancy-details.component";
import {ErrorHandlerComponent} from "./components/error-handler/error-handler.component";
import {PrimengImportsModule} from "./primeng-imports";
import {FieldsetModule} from 'primeng/fieldset';
import {InputNumberModule} from 'primeng/inputnumber';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {ConfirmationService} from 'primeng/api';
import {ProgressSpinnerModule} from 'primeng/progressspinner';

@NgModule({
  declarations: [
    AppComponent,
    FilterFormComponent,
    LoginComponent,
    UserDetailsComponent,
    RegisterComponent,
    NotFoundComponent,
    VacancyDetailsComponent,
    ErrorHandlerComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    PasswordModule,
    PanelModule,
    DropdownModule,
    CalendarModule,
    RadioButtonModule,
    MessagesModule,
    MessageModule,
    BrowserAnimationsModule,
    FileUploadModule,
    ToastModule,
    InputMaskModule,
    routing,
    FieldsetModule,
    InputNumberModule,
    ConfirmDialogModule,
    ProgressSpinnerModule,
    PrimengImportsModule,
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: UniversalAppInterceptor, multi: true},
    AuthGuardService,
    ConfirmationService
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}
