import { Injectable } from "@angular/core";
import { LoadingService } from "../services/loading.service";
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Observable, finalize } from "rxjs";


@Injectable()
export class LoadingInterceptor implements HttpInterceptor {
  constructor(private loadingService: LoadingService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.loadingService.show();
    return next.handle(request).pipe(
      finalize(() => this.loadingService.hide())
    );
  }
}
