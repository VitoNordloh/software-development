import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html'
})
export class CourseComponent {
  @Input() course;
  @Input() showFooter;
}
