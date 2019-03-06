import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(private http: HttpClient) {}

  public search(data: any): Observable<any> {
    return this.http.post(environment.api + '/search', data);
  }

  public getUniversity(id: number) {
    return this.http.get(environment.api + '/universities/' + id);
  }
}
