import {RouterModule, Routes} from "@angular/router";
import {LoginComponent as LoginComponent} from "./components/login/login.component";
import {UserDetailsComponent} from "./components/user-details/user-details.component";
import {FilterFormComponent} from "./components/filter-form/filter-form.component";
import {AuthGuardService} from "./guard/authGuard.service";
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {RegisterComponent} from "./components/register/register.component";
import {VacancyDetailsComponent} from "./components/vacancy-details/vacancy-details.component";

const appRoutes: Routes = [
  {path: "", redirectTo: "/vacancy/search", pathMatch: "full"},
  {path: "profile", component: UserDetailsComponent, canActivate: [AuthGuardService]},
  {path: "login", component: LoginComponent},
  {path: "register", component: RegisterComponent},
  {path: "vacancy/search", component: FilterFormComponent},
  {path: "vacancy/:id", component: VacancyDetailsComponent},
  {path: "**", component: NotFoundComponent},
];

export const routing = RouterModule.forRoot(appRoutes);
