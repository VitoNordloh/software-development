import {Component, EventEmitter, Output} from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-search-form',
  templateUrl: './form.component.html'
})
export class FormComponent {
  @Output() searched = new EventEmitter<any>();

  form = new FormGroup({
    university: new FormControl(''),
    title: new FormControl(''),
    employmentChances: new FormControl(0),
    teachingExcellence: new FormControl(0),
    firstClassRate: new FormControl(0),
    qualityOfLife: new FormControl(0)
  });

  search() {
    this.searched.emit(this.form.value);
  }
}
