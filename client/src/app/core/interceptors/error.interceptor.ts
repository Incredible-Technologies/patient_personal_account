import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpErrorResponse, HttpEvent } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      return next.handle(request).pipe(
        catchError((error: HttpErrorResponse) => {
          let errorMessage = 'Произошла неизвестная ошибка!';

          if (error.error instanceof ErrorEvent) {
            // Client-side error
            errorMessage = `Ошибка на стороне клиента: ${error.error.message}`;
          } else {
            // Server-side error
            switch (error.status) {
              case 400:
                errorMessage = 'Неверный запрос: ' + (error.error.message || 'Неверный запрос');
                break;
              case 401:
                errorMessage = 'Неавторизован: ' + (error.error.message || 'Вы не авторизованы');
                // Redirect to login page or show login modal
                this.router.navigate(['/login-reg']);
                break;
              case 404:
                errorMessage = 'Не найдено: ' + (error.error.message || 'Запрашиваемый ресурс не найден');
                break;
              case 500:
                errorMessage = 'Внутренняя ошибка сервера: ' + (error.error.message || 'Что-то пошло не так');
                break;
              case 503:
                  errorMessage = 'Сервис недоступен: ' + (error.error.message || 'Сервис недоступен');
                  break;
              // Add more cases as needed
            }
          }

          // Show a global error message or use some error handling service
          // console.error(errorMessage);

          return throwError(errorMessage);
        })
      );
    }
}
