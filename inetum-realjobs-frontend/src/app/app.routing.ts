import {RouterModule, Routes} from "@angular/router";
import {LoginComponent as LoginComponent} from "./components/login/login.component";
import {UserDetailsComponent} from "./components/user-details/user-details.component";
import {FilterFormComponent} from "./components/filter-form/filter-form.component";
import {AuthGuardService} from "./guard/authGuard.service";
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {RegisterComponent} from "./components/register/register.component";
import {VacancyDetailsComponent} from "./components/vacancy-details/vacancy-details.component";
import {JobseekerGuard} from "./guard/jobseeker.guard";
import {ForgotPasswordComponent} from "./components/forgot-password/forgot-password.component";
import {ResetPasswordComponent} from "./components/reset-password/reset-password.component";
import {
  VacancyApplicationsComponent,
} from "./components/applications/vacancy-applications/vacancy-applications.component";
import {ResumeComponent} from "./components/resume/resume/resume.component";
import {RecruiterOverviewComponent} from "./components/recruiter-overview/recruiter-overview.component";
import {RecruiterGuard} from "./guard/recruiter.guard";

const appRoutes: Routes = [
  {path: "", redirectTo: "/vacancy/search", pathMatch: "full"},
  {path: "users/:username", component: UserDetailsComponent, canActivate: [AuthGuardService]},
  {path: "login", component: LoginComponent},
  {path: "register", component: RegisterComponent},
  {path: "vacancy/search", component: FilterFormComponent},
  {path: "vacancy/:id", component: VacancyDetailsComponent},
  {path: "resume", component: ResumeComponent, canActivate: [JobseekerGuard]},
  {path: "vacancy/:id/applications", component: VacancyApplicationsComponent},
  {path: "not-found", component: NotFoundComponent},
  {path: "forgot-password", component: ForgotPasswordComponent},
  {path: "reset-password", component: ResetPasswordComponent},
  {path: 'recruiter/overview', component: RecruiterOverviewComponent, canActivate: [RecruiterGuard]},
  {path: "**", component: NotFoundComponent},
];

export const routing = RouterModule.forRoot(appRoutes);
