import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../api.service';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-university',
  templateUrl: './university.component.html'
})
export class UniversityComponent implements OnInit {
  university$: Observable<any>;

  page = 0;
  pageSize = 5;
  maxSize = 5;

  constructor(private route: ActivatedRoute, private api: ApiService) {}

  ngOnInit() {
    this.university$ = this.route.paramMap.pipe(
        switchMap((params: ParamMap) => {
          return this.api.getUniversity(parseInt(params.get('id'), 10));
        })
    );
  }
}
