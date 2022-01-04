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
import {UrlInterceptor} from "./interceptor/url.interceptor";
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {VacancyDetailsComponent} from "./components/vacancy-details/vacancy-details.component";
import {ErrorHandlerComponent} from "./components/error-handler/error-handler.component";
import {PrimengImportsModule} from "./primeng-imports";
import {ResumeCreateComponent} from "./components/resume/resume-create/resume-create.component";
import {LanguageFormComponent} from "./components/resume/forms/language-form/language-form.component";
import {SkillFormComponent} from "./components/resume/forms/skill-form/skill-form.component";
import {ExperienceFormComponent} from "./components/resume/forms/experience-form/experience-form.component";
import {EducationFormComponent} from './components/resume/forms/education-form/education-form.component';
import {ExperienceDetailsComponent} from './components/resume/details/experience-details/experience-details.component';
import {ExperienceListComponent} from './components/resume/details/experience-list/experience-list.component';
import {EducationDetailsComponent} from './components/resume/details/education-details/education-details.component';
import {EducationListComponent} from './components/resume/details/education-list/education-list.component';
import {SkillListComponent} from './components/resume/details/skill-list/skill-list.component';
import {LanguageListComponent} from './components/resume/details/language-list/language-list.component';
import {StatusFormComponent} from './components/resume/forms/status-form/status-form.component';
import {SummaryFormComponent} from './components/resume/forms/summary-form/summary-form.component';
import {
  ResumeAccountDetailsComponent
} from './components/resume/details/resume-account-details/resume-account-details.component';
import {ConfirmationService} from 'primeng/api';
import {ForgotPasswordComponent} from "./components/forgot-password/forgot-password.component";
import {ResetPasswordComponent} from "./components/reset-password/reset-password.component";
import { RecruiterOverviewComponent } from './components/recruiter-overview/recruiter-overview.component';
import {TableModule} from "primeng/table";

@NgModule({
  declarations: [
    AppComponent,
    FilterFormComponent,
    LoginComponent,
    UserDetailsComponent,
    RegisterComponent,
    NotFoundComponent,
    ForgotPasswordComponent,
    ResetPasswordComponent,
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
    ResumeAccountDetailsComponent,
    RecruiterOverviewComponent,
  ],
    imports: [
        BrowserModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        routing,
        PrimengImportsModule,
        TableModule,
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
