import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { ProfileData } from '../models/profile-data.model';



@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient, private authService: AuthService) { }

  getProfile(): Observable<ProfileData> {
    const headers = this.createAuthorizationHeader();
    return this.http.get<ProfileData>(`${this.baseUrl}/profile`, { headers });
  }

  saveProfile(profileData: ProfileData): Observable<ProfileData> {
    const headers = this.createAuthorizationHeader();
    return this.http.post<ProfileData>(`${this.baseUrl}/profile`, profileData, { headers });
  }

  private createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getAuthToken()}`
    });
  }
}
