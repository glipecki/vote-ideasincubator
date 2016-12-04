import {Component} from '@angular/core';

@Component({
  selector: 'vii-list',
  templateUrl: './list.component.html',
  styleUrls: [
    './list.component.scss'
  ]
})
export class ListComponent {
  items = [
    {title: 'Super prezentacja o Angularze', stars: 1, type: 'proposal', tags: [{title: 'Proposal', type: 'success'}, {title: 'Angular'}]},
    {title: 'Ale dlaczego nie Java 5?', stars: 3, type: 'request', tags: [{title: 'Request', type: 'warning'}, {title: 'Java 8'}]},
    {title: 'Dependency-hell dla każdego', stars: 0, type: 'proposal', tags: [{title: 'Proposal', type: 'success'}, {title: 'Java EE'}]},
    {title: 'Czy testy są dla miękki fryt?', stars: 13, type: 'proposal', tags: [{title: 'Proposal', type: 'success'}, {title: 'Spring'}, {title: 'Testing'}]}
  ]

  tagClass(tag: any): string {
    if (tag.type) {
      return 'label-' + tag.type;
    } else {
      return 'label-default';
    }
  }
}
