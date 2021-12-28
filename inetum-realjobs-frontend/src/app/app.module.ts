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
import {VacancyDetailsComponent} from "./components/vacancy-details/vacancy-details.component";
import {ErrorHandlerComponent} from "./components/error-handler/error-handler.component";
import {PrimengImportsModule} from "./primeng-imports";
import {ResumeCreateComponent} from "./components/resume/resume-create/resume-create.component";
import {InputTextModule} from "primeng/inputtext";
import {LanguageFormComponent} from "./components/resume/forms/language-form/language-form.component";
import {SkillFormComponent} from "./components/resume/forms/skill-form/skill-form.component";
import {ExperienceFormComponent} from "./components/resume/forms/experience-form/experience-form.component";
import {CheckboxModule} from "primeng/checkbox";
import {EducationFormComponent} from './components/resume/forms/education-form/education-form.component';
import {ExperienceDetailsComponent} from './components/resume/details/experience-details/experience-details.component';
import {CardModule} from "primeng/card";
import {RippleModule} from "primeng/ripple";
import {StyleClassModule} from "primeng/styleclass";
import {ExperienceListComponent} from './components/resume/details/experience-list/experience-list.component';
import {EducationDetailsComponent} from './components/resume/details/education-details/education-details.component';
import {EducationListComponent} from './components/resume/details/education-list/education-list.component';
import {SkillListComponent} from './components/resume/details/skill-list/skill-list.component';
import {ChipModule} from "primeng/chip";
import {LanguageListComponent} from './components/resume/details/language-list/language-list.component';
import {StatusFormComponent} from './components/resume/forms/status-form/status-form.component';
import {SummaryFormComponent} from './components/resume/forms/summary-form/summary-form.component';
import {InputTextareaModule} from "primeng/inputtextarea";
import {ConfirmPopupModule} from "primeng/confirmpopup";
import {ConfirmationService} from 'primeng/api';
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {PasswordModule} from "primeng/password";
import {PanelModule} from "primeng/panel";

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
    ResumeCreateComponent,
    ExperienceFormComponent,
    SkillFormComponent,
    LanguageFormComponent,
    EducationFormComponent,
    ExperienceDetailsComponent,
    ExperienceListComponent,
    EducationDetailsComponent,
    EducationListComponent,
    SkillListComponent,
    LanguageListComponent,
    StatusFormComponent,
    SummaryFormComponent,
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
    CardModule,
    RippleModule,
    StyleClassModule,
    ChipModule,
    InputTextareaModule,
    ConfirmPopupModule,
    ConfirmDialogModule,
    routing,
    PrimengImportsModule,
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: UrlInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: UniversalAppInterceptor, multi: true},
    AuthGuardService,
    ConfirmationService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}
