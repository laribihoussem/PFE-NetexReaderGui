import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { NgxDropzoneModule } from 'ngx-dropzone';
import {NgxPaginationModule} from 'ngx-pagination'; 
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { SidebarModule } from "ng-sidebar";
import { ChartsModule } from 'ng2-charts';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';




import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/loginComponent/login.component';
import { SignupComponentComponent } from './auth/signup-component/signup-component.component';
import { AuthInterceptor } from "./auth/services/authenticationService/auth-interceptor";
import { NavbarComponent } from './components/shared/navbar/navbar.component';
import { FooterComponent } from './components/shared/footer/footer.component';
import { UsersListComponent } from './components/users-list/users-list.component';
import { HomeComponent } from './components/home/home.component';
import { JwtHelperService, JwtModule } from '@auth0/angular-jwt';
import { FilesComponent } from './components/filesInput/files.component';
import { FilesListComponent } from './components/files-list/files-list.component';
import { HistoryComponent } from './components/history/history.component';
import { PersonalHistoryComponent } from './components/personal-history/personal-history.component';
import { ErrorPageComponent } from './components/error-page/error-page.component';
import { AddDestinationComponent } from './components/add-destination/add-destination.component';
import { MonitoringComponent } from './components/monitoring/monitoring.component';
import { EndpointsComponent } from './components/monitoring/endpoints/endpoints.component';
import { ComponentComponent } from './components/monitoring/component/component.component';




@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponentComponent,
    NavbarComponent,
    FooterComponent,
    UsersListComponent,
    HomeComponent,
    FilesComponent,
    FilesListComponent,
    HistoryComponent,
    PersonalHistoryComponent,
    ErrorPageComponent,
    AddDestinationComponent,
    MonitoringComponent,
    EndpointsComponent,
    ComponentComponent,
   
    

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FontAwesomeModule,
    FormsModule,
    ReactiveFormsModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: function  tokenGetter() { 
        return localStorage.getItem('token');
        } 
     }
   }),
   NgxDropzoneModule,
   NgxPaginationModule,
   Ng2SearchPipeModule,
   SidebarModule.forRoot(),
   ChartsModule,
   BrowserAnimationsModule, // required animations module
   // ToastrModule added
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
