import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (req.url.indexOf("/login") < 0 || req.url.indexOf("/signUp") < 0) {
      const idToken = localStorage.getItem("id_token");

      if (idToken) {
        const cloned = req.clone({
          headers: req.headers.set("Authorization", "Bearer " + idToken),
        });

        return next.handle(cloned);
      }
    }
    return next.handle(req);
  }
}

