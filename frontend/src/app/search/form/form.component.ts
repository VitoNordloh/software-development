import {Component, EventEmitter, Output} from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-search-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent {
  @Output() private searched = new EventEmitter<any>();

  private form = new FormGroup({
    location: new FormControl(''),
    programmes: new FormControl(''),
    employmentChances: new FormControl(10),
    teachingExcellence: new FormControl('all'),
    graduationRates: new FormControl(10),
    qualityOfLive: new FormControl(10)
  });

  private search() {
    this.searched.emit(this.form.value);
  }
}
