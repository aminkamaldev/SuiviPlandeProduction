import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { JobExecutionComponent } from './job-execution.component';
import { JobExecutionDetailComponent } from './job-execution-detail.component';
import { JobExecutionPopupComponent } from './job-execution-dialog.component';
import { JobExecutionDeletePopupComponent } from './job-execution-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class JobExecutionResolvePagingParams implements Resolve<any> {

  constructor(private paginationUtil: PaginationUtil) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
      let page = route.queryParams['page'] ? route.queryParams['page'] : '1';
      let sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
      return {
          page: this.paginationUtil.parsePage(page),
          predicate: this.paginationUtil.parsePredicate(sort),
          ascending: this.paginationUtil.parseAscending(sort)
    };
  }
}

export const jobExecutionRoute: Routes = [
  {
    path: 'job-execution',
    component: JobExecutionComponent,
    resolve: {
      'pagingParams': JobExecutionResolvePagingParams
    },
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'JobExecutions'
    }
  }, {
    path: 'job-execution/:id',
    component: JobExecutionDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'JobExecutions'
    }
  }
];

export const jobExecutionPopupRoute: Routes = [
  {
    path: 'job-execution-new',
    component: JobExecutionPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'JobExecutions'
    },
    outlet: 'popup'
  },
  {
    path: 'job-execution/:id/edit',
    component: JobExecutionPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'JobExecutions'
    },
    outlet: 'popup'
  },
  {
    path: 'job-execution/:id/delete',
    component: JobExecutionDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'JobExecutions'
    },
    outlet: 'popup'
  }
];
