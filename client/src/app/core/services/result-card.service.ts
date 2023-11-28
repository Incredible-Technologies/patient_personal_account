import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { ResultCard } from '../models/result-card.model';
import { ResultFile } from '../models/result-file.model';
import { environment } from 'src/environments/environment';

@Injectable({ providedIn: 'root' })
export class ResultCardService {
  private apiBaseUrl = `${environment.apiUrl}/result-card`;
  private filesApiUrl = `${environment.apiUrl}/files`;

  constructor(private http: HttpClient, private authService: AuthService) {}

  private getHeaders(): HttpHeaders {
    const token = this.authService.getAuthToken();
    return new HttpHeaders({ 'Authorization': `Bearer ${token}` });
  }

  createResultCard(resultCardData: ResultCard): Observable<ResultCard> {
    return this.http.post<ResultCard>(`${this.apiBaseUrl}/create`, resultCardData, { headers: this.getHeaders() });
  }

  viewResultCard(cardId: number): Observable<ResultFile> {
    return this.http.get<ResultFile>(`${this.filesApiUrl}/result-file/${cardId}`, { headers: this.getHeaders() });
  }

  downloadResultFile(cardId: number): Observable<Blob> {
    return this.http.post<Blob>(`${this.apiBaseUrl}/download`, { cardId }, { headers: this.getHeaders(), responseType: 'blob' as 'json' });
  }

  getAllUserCards(): Observable<ResultCard[]> {
    return this.http.get<ResultCard[]>(`${environment.apiUrl}/all-cards`, { headers: this.getHeaders() });
  }

  getAllDoneUserCards(): Observable<ResultCard[]> {
    return this.http.get<ResultCard[]>(`${environment.apiUrl}/all-done-cards`, { headers: this.getHeaders() });
  }

  getAllNotDoneUserCards(): Observable<ResultCard[]> {
    return this.http.get<ResultCard[]>(`${environment.apiUrl}/all-not-done-cards`, { headers: this.getHeaders() });
  }

  // Additional methods as needed...
}
