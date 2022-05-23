import {
    HttpInterceptor,
    HttpRequest,
    HttpHandler
  } from "@angular/common/http";
  import { Injectable } from "@angular/core";
  
  import { AuthenticationServiceService } from "./authentication-service.service";
  
  @Injectable()
  export class AuthInterceptor implements HttpInterceptor {
    constructor(private authService: AuthenticationServiceService) {}
  
    intercept(req: HttpRequest<any>, next: HttpHandler) {
      if(req.url!='http://localhost:8090/api/user/save'){
        const token = localStorage.getItem("token")
        const authRequest = req.clone({
        headers: req.headers.set("Authorization", "Bearer " + token)
      });
      return next.handle(authRequest);
    }else{
      const authRequest = req.clone({
        headers: req.headers
      });
      return next.handle(authRequest);
    }
      }
      
  }