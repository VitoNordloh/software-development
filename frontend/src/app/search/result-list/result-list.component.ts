import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-search-result-list',
  templateUrl: './result-list.component.html'
})
export class ResultListComponent {
  @Input() results: any[];

  page = 0;
  pageSize = 5;
  maxSize = 5;
}
