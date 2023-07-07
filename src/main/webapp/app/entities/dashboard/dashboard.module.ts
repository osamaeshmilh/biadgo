import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {dashboardRoutes} from './';
import {DashboardComponent} from './dashboard.component';
import SharedModule from "../../shared/shared.module";

const ENTITY_STATES = [...dashboardRoutes];

@NgModule({
  imports: [SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [DashboardComponent],
  entryComponents: [DashboardComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class DashboardModule {
}
