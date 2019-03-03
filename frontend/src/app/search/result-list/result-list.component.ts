import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-search-result-list',
  templateUrl: './result-list.component.html',
  styleUrls: ['./result-list.component.scss']
})
export class ResultListComponent implements OnInit {
  @Input() private results: any[];

  constructor() {}

  ngOnInit() {}
}
