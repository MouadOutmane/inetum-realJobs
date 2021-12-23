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
import {PasswordModule} from "primeng/password";
import {PanelModule} from "primeng/panel";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {DropdownModule} from "primeng/dropdown";
import {CalendarModule} from "primeng/calendar";
import {FileUploadModule} from "primeng/fileupload";
import {RadioButtonModule} from "primeng/radiobutton";
import {MessagesModule} from "primeng/messages";
import {MessageModule} from "primeng/message";
import {ToastModule} from "primeng/toast";
import {InputMaskModule} from "primeng/inputmask";
import {UrlInterceptor} from "./interceptor/url.interceptor";
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {ResumeCreateComponent} from "./components/resume/resume-create/resume-create.component";
import {InputTextModule} from "primeng/inputtext";
import {LanguageFormComponent} from "./components/resume/forms/language-form/language-form.component";
import {SkillFormComponent} from "./components/resume/forms/skill-form/skill-form.component";
import {ExperienceFormComponent} from "./components/resume/forms/experience-form/experience-form.component";
import {CheckboxModule} from "primeng/checkbox";
import {EducationFormComponent} from './components/resume/forms/education-form/education-form.component';

@NgModule({
  declarations: [
    AppComponent,
    FilterFormComponent,
    LoginComponent,
    UserDetailsComponent,
    RegisterComponent,
    NotFoundComponent,
    ResumeCreateComponent,
    ExperienceFormComponent,
    SkillFormComponent,
    LanguageFormComponent,
    EducationFormComponent,
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
    InputTextModule,
    CheckboxModule,
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: UrlInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: UniversalAppInterceptor, multi: true},
    AuthGuardService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}
