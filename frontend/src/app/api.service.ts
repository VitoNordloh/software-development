import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(private http: HttpClient) {}

  public search(data: any): Observable<any> {
    return this.http.post('http://localhost:8080/search', data);
  }

  public get(id: number) {
    return this.http.get('http://localhost:8080/universities/' + id);
  }
}
