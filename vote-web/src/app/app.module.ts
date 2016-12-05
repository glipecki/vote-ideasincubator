import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {AppComponent} from 'app/app.component';
import {NavbarComponent} from 'app/navbar/navbar.component';
import {RouterModule} from '@angular/router';

import {ListComponent} from 'app/list/list.component';
import {AddComponent} from 'app/add/add.component';
import {appRouting} from 'app/app.routing';
import {PastComponent} from 'app/past/past.component';
import {PlannedComponent} from 'app/planned/planned.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ListComponent,
    AddComponent,
    PastComponent,
    PlannedComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRouting)
  ],
  providers: [],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {
}
