import { Component } from '@angular/core';
import { ApiService } from '../../api.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html'
})
export class SearchComponent {
  results: any[];

  constructor(private api: ApiService) {}

  onSearched(data) {
    this.api.search(data).subscribe(results => {
      this.results = results;
    });
  }
}
