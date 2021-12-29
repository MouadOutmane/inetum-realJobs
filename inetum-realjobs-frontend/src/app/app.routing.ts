import {RouterModule, Routes} from "@angular/router";
import {LoginComponent as LoginComponent} from "./components/login/login.component";
import {UserDetailsComponent} from "./components/user-details/user-details.component";
import {FilterFormComponent} from "./components/filter-form/filter-form.component";
import {AuthGuardService} from "./guard/authGuard.service";
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {RegisterComponent} from "./components/register/register.component";
import {ResumeCreateComponent} from "./components/resume/resume-create/resume-create.component";
import {VacancyDetailsComponent} from "./components/vacancy-details/vacancy-details.component";
import {JobseekerGuard} from "./guard/jobseeker.guard";

const appRoutes: Routes = [
  {path: "", redirectTo: "/vacancy/search", pathMatch: "full"},
  {path: "users/:username", component: UserDetailsComponent, canActivate: [AuthGuardService]},
  {path: "login", component: LoginComponent},
  {path: "register", component: RegisterComponent},
  {path: "vacancy/search", component: FilterFormComponent},
  {path: "vacancy/:id", component: VacancyDetailsComponent},
  {path: "resume/create", component: ResumeCreateComponent, canActivate: [JobseekerGuard]},
  {path: "not-found", component: NotFoundComponent},
  {path: "**", component: NotFoundComponent},
];

export const routing = RouterModule.forRoot(appRoutes);
