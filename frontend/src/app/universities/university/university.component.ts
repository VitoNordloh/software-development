import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../api.service';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-university',
  templateUrl: './university.component.html',
  styleUrls: ['./university.component.scss']
})
export class UniversityComponent implements OnInit {
  private university$: Observable<any>;

  constructor(private route: ActivatedRoute, private api: ApiService) {}

  ngOnInit() {
    this.university$ = this.route.paramMap.pipe(
        switchMap((params: ParamMap) => {
          console.log('Fetching', params.get('id'));
          return this.api.get(parseInt(params.get('id'), 10));
        })
    );
  }
}
