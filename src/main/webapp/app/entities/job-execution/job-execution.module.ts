import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuiviPlanProductionSharedModule } from '../../shared';

import {
    JobExecutionService,
    JobExecutionPopupService,
    JobExecutionComponent,
    JobExecutionDetailComponent,
    JobExecutionDialogComponent,
    JobExecutionPopupComponent,
    JobExecutionDeletePopupComponent,
    JobExecutionDeleteDialogComponent,
    jobExecutionRoute,
    jobExecutionPopupRoute,
    JobExecutionResolvePagingParams,
} from './';

let ENTITY_STATES = [
    ...jobExecutionRoute,
    ...jobExecutionPopupRoute,
];

@NgModule({
    imports: [
        SuiviPlanProductionSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        JobExecutionComponent,
        JobExecutionDetailComponent,
        JobExecutionDialogComponent,
        JobExecutionDeleteDialogComponent,
        JobExecutionPopupComponent,
        JobExecutionDeletePopupComponent,
    ],
    entryComponents: [
        JobExecutionComponent,
        JobExecutionDialogComponent,
        JobExecutionPopupComponent,
        JobExecutionDeleteDialogComponent,
        JobExecutionDeletePopupComponent,
    ],
    providers: [
        JobExecutionService,
        JobExecutionPopupService,
        JobExecutionResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SuiviPlanProductionJobExecutionModule {}
