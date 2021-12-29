import { RouterModule, Routes } from "@angular/router";
import { LoginComponent as LoginComponent } from "./components/login/login.component";
import { UserDetailsComponent } from "./components/user-details/user-details.component";
import { FilterFormComponent } from "./components/filter-form/filter-form.component";
import { AuthGuardService } from "./guard/authGuard.service";
import { VacancyCreationComponent } from "./components/vacancy-creation/vacancy-creation.component";
import { SearchComponent } from "./components/search/search.component";

const appRoutes: Routes = [
    { path: 'users/:username', component: UserDetailsComponent, canActivate: [AuthGuardService]},
    { path: 'login', component: LoginComponent},
    { path: 'vacancy/search', component: FilterFormComponent},
    { path: 'vacancy/create', component: VacancyCreationComponent},
    { path: 'vacancy/new', component: SearchComponent}
];

export const routing = RouterModule.forRoot(appRoutes);