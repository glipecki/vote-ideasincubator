import {Component} from '@angular/core';

@Component({
  selector: 'vii-list',
  templateUrl: './list.component.html',
  styleUrls: [
    './list.component.scss'
  ]
})
export class ListComponent {

  //noinspection JSUnusedGlobalSymbols: used by view
  items = [
    {title: 'Super prezentacja o Angularze', dateSince: '3 dni temu', author: 'Jan', stars: 1, type: 'proposal', tags: [{title: 'Proposal', type: 'success'}, {title: 'Angular'}]},
    {title: 'Ale dlaczego nie Java 5?', dateSince: '1 dzień temu', author: 'Jan', stars: 3, type: 'request', tags: [{title: 'Request', type: 'warning'}, {title: 'Java 8'}]},
    {title: 'Dependency-hell dla każdego', dateSince: '1 tydzień temu', author: 'Jan', stars: 0, type: 'proposal', tags: [{title: 'Proposal', type: 'success'}, {title: 'Java EE'}]},
    {title: 'Czy testy są dla miękkich fryt?', dateSince: '2 dni temu', author: 'Jan', stars: 13, type: 'proposal', tags: [{title: 'Proposal', type: 'success'}, {title: 'Spring'}, {title: 'Testing'}]}
  ];

  tagClass(tag: any): string {
    if (tag.type) {
      return 'label-' + tag.type;
    } else {
      return 'label-default';
    }
  }

}
