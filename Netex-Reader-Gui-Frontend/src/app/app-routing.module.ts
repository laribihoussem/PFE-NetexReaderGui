import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/loginComponent/login.component';
import { SignupComponentComponent } from './auth/signup-component/signup-component.component';
import { HomeComponent } from './components/home/home.component';
import { UsersListComponent } from './components/users-list/users-list.component';
import { AuthGuard } from "./auth/auth.guard";  
import { RoleGuard } from "./auth/role.guard";
import { FilesComponent } from './components/filesInput/files.component';
import { FilesListComponent } from './components/files-list/files-list.component';
import { PersonalHistoryComponent } from './components/personal-history/personal-history.component';
import { ErrorPageComponent } from './components/error-page/error-page.component';
import { AddDestinationComponent } from './components/add-destination/add-destination.component';
import { MonitoringComponent } from './components/monitoring/monitoring.component';
import { HistoryComponent } from './components/history/history.component';
import { EndpointsComponent } from './components/monitoring/endpoints/endpoints.component';
import { ComponentComponent } from './components/monitoring/component/component.component';
import { FileByRouteComponent } from './components/monitoring/file-by-route/file-by-route.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: SignupComponentComponent },
  { path: 'users/list', component: UsersListComponent, canActivate: [RoleGuard], 
  data: { 
    expectedRole: 'ROLE_ADMIN'
  }   },
  { path: 'file/input', component: FilesComponent, canActivate:[AuthGuard] },
  { path: 'file/list', component: FilesListComponent, canActivate: [RoleGuard], 
  data: { 
    expectedRole: 'ROLE_ADMIN'
  }   },
  { path: 'personalHistory', component: PersonalHistoryComponent, canActivate:[AuthGuard] },
  { path: 'history', component: HistoryComponent, canActivate: [RoleGuard], 
  data: { 
    expectedRole: 'ROLE_ADMIN'
  }   },
  { path: 'addDestination', component: AddDestinationComponent, canActivate: [RoleGuard], 
  data: { 
    expectedRole: 'ROLE_ADMIN'
  }  },
  { path: 'monitoring', component: MonitoringComponent, canActivate: [RoleGuard], 
  data: { 
    expectedRole: 'ROLE_ADMIN'
  }  },
  { path: 'monitoring/endpoints', component: EndpointsComponent, canActivate: [RoleGuard], 
  data: { 
    expectedRole: 'ROLE_ADMIN'
  }  },
  { path: 'monitoring/components', component: ComponentComponent, canActivate: [RoleGuard], 
  data: { 
    expectedRole: 'ROLE_ADMIN'
  }  },
  { path: 'monitoring/files', component: FileByRouteComponent, canActivate: [RoleGuard], 
  data: { 
    expectedRole: 'ROLE_ADMIN'
  }  },
  { path: 'notFound', component: ErrorPageComponent}

  




];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AuthGuard]
})
export class AppRoutingModule { }
