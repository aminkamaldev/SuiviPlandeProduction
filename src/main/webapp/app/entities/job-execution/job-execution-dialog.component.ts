import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { JobExecution } from './job-execution.model';
import { JobExecutionPopupService } from './job-execution-popup.service';
import { JobExecutionService } from './job-execution.service';
@Component({
    selector: 'jhi-job-execution-dialog',
    templateUrl: './job-execution-dialog.component.html'
})
export class JobExecutionDialogComponent implements OnInit {

    jobExecution: JobExecution;
    authorities: any[];
    isSaving: boolean;
    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private jobExecutionService: JobExecutionService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }
    clear () {
        this.activeModal.dismiss('cancel');
    }

    save () {
        this.isSaving = true;
        if (this.jobExecution.id !== undefined) {
            this.jobExecutionService.update(this.jobExecution)
                .subscribe((res: JobExecution) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        } else {
            this.jobExecutionService.create(this.jobExecution)
                .subscribe((res: JobExecution) => this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        }
    }

    private onSaveSuccess (result: JobExecution) {
        this.eventManager.broadcast({ name: 'jobExecutionListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError (error) {
        this.isSaving = false;
        this.onError(error);
    }

    private onError (error) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-job-execution-popup',
    template: ''
})
export class JobExecutionPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private jobExecutionPopupService: JobExecutionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            if ( params['id'] ) {
                this.modalRef = this.jobExecutionPopupService
                    .open(JobExecutionDialogComponent, params['id']);
            } else {
                this.modalRef = this.jobExecutionPopupService
                    .open(JobExecutionDialogComponent);
            }

        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
