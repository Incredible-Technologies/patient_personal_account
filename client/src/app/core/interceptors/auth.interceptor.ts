import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { isPlatformBrowser } from '@angular/common';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(
    private authService: AuthService,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (isPlatformBrowser(this.platformId)) {
      const authToken = this.authService.getAuthToken();
      if (authToken) {
        req = req.clone({
          headers: req.headers.set('Authorization', `Bearer ${authToken}`)
        });
      }
    }
    return next.handle(req);
  }
}
