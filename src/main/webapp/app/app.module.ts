import './vendor.ts';

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { Ng2Webstorage } from 'ng2-webstorage';

import { SuiviPlanProductionSharedModule, UserRouteAccessService } from './shared';
import { SuiviPlanProductionHomeModule } from './home/home.module';
import { SuiviPlanProductionAdminModule } from './admin/admin.module';
import { SuiviPlanProductionAccountModule } from './account/account.module';
import { SuiviPlanProductionEntityModule } from './entities/entity.module';

import { LayoutRoutingModule } from './layouts';
import { customHttpProvider } from './blocks/interceptor/http.provider';
import { PaginationConfig } from './blocks/config/uib-pagination.config';

import {
    JhiMainComponent,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ErrorComponent
} from './layouts';


@NgModule({
    imports: [
        BrowserModule,
        LayoutRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        SuiviPlanProductionSharedModule,
        SuiviPlanProductionHomeModule,
        SuiviPlanProductionAdminModule,
        SuiviPlanProductionAccountModule,
        SuiviPlanProductionEntityModule
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        FooterComponent
    ],
    providers: [
        ProfileService,
        { provide: Window, useValue: window },
        { provide: Document, useValue: document },
        customHttpProvider(),
        PaginationConfig,
        UserRouteAccessService
    ],
    bootstrap: [ JhiMainComponent ]
})
export class SuiviPlanProductionAppModule {}
