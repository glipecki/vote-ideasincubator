import {Routes} from '@angular/router';

import {ListComponent} from 'app/list/list.component';
import {AddComponent} from 'app/add/add.component';
import {PastComponent} from 'app/past/past.component';
import {PlannedComponent} from 'app/planned/planned.component';

export const appRouting: Routes = [
  {path: '', redirectTo: '/list', pathMatch: 'full'},
  {path: 'list', component: ListComponent},
  {path: 'test/list', component: ListComponent},
  {path: 'add', component: AddComponent},
  {path: 'past', component: PastComponent},
  {path: 'planned', component: PlannedComponent}
];
