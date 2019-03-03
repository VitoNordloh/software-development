import { Component } from '@angular/core';
import { ApiService } from '../../api.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent {
  private results: any[];

  constructor(private api: ApiService) {}

  private onSearched(data) {
    this.api.search(data).subscribe(results => {
      this.results = results;
    });
  }
}
