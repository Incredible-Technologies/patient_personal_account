// core/services/auth.service.ts
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root',
})
export class AuthService {
    private baseUrl = environment.apiUrl;

    constructor(private http: HttpClient) { }

    login(credentials: { email: string; password: string }): Observable<any> {
        return this.http.post(`${this.baseUrl}/auth`, credentials);
    }

    register(user: {
        email: string;
        password: string;
        confirmPassword: string;
    }): Observable<any> {
        return this.http.post(`${this.baseUrl}/registration`, user);
    }

    getAuthToken(): string | null {
        return localStorage.getItem('authToken');
    }

    isLoggedIn(): boolean {
        const token = localStorage.getItem('authToken');
        // Implement logic to check if the token is valid
        return !!token;
      }

     logout(): void {
        localStorage.removeItem('authToken');
    }
}


// // core/services/auth.service.ts
// import { HttpClient } from '@angular/common/http';
// import { Injectable } from '@angular/core';
// import { Observable } from 'rxjs';

// @Injectable({
//     providedIn: 'root',
// })
// export class AuthService {
//     constructor(private http: HttpClient) { }

//     login(credentials: { email: string; password: string }): Observable<any> {
//         return this.http.post(`/api/auth`, credentials);
//     }

//     register(user: { email: string; password: string; confirmPassword: string }): Observable<any> {
//         return this.http.post(`/api/registration`, user);
//     }

//     getAuthToken(): string | null {
//         return localStorage.getItem('authToken');
//     }

//     isLoggedIn(): boolean {
//         const token = localStorage.getItem('authToken');
//         // Implement logic to check if the token is valid
//         return !!token;
//     }

//     logout(): void {
//         localStorage.removeItem('authToken');
//     }
// }
