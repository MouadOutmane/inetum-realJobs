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
import {ConfirmationService} from 'primeng/api';
import {ForgotPasswordComponent} from "./components/forgot-password/forgot-password.component";
import {ResetPasswordComponent} from "./components/reset-password/reset-password.component";
import {ResumeComponent} from './components/resume/resume/resume.component';
import {
  EducationControllerComponent
} from './components/resume/controllers/education-controller/education-controller.component';
import {EducationViewComponent} from './components/resume/details/education-view/education-view.component';
import {ExperienceViewComponent} from './components/resume/details/experience-view/experience-view.component';
import {
  ExperienceControllerComponent
} from './components/resume/controllers/experience-controller/experience-controller.component';
import {SkillViewComponent} from './components/resume/details/skill-view/skill-view.component';
import {SkillControllerComponent} from './components/resume/controllers/skill-controller/skill-controller.component';
import {
  LanguageControllerComponent
} from './components/resume/controllers/language-controller/language-controller.component';
import {LanguageViewComponent} from './components/resume/details/language-view/language-view.component';
import {
  SummaryControllerComponent
} from './components/resume/controllers/summary-controller/summary-controller.component';
import {SummaryViewComponent} from './components/resume/details/summary-view/summary-view.component';

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
    ResumeComponent,
    EducationControllerComponent,
    EducationViewComponent,
    ExperienceViewComponent,
    ExperienceControllerComponent,
    SkillViewComponent,
    SkillControllerComponent,
    LanguageControllerComponent,
    LanguageViewComponent,
    SummaryControllerComponent,
    SummaryViewComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
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
