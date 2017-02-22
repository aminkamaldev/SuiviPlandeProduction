import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { JobExecution } from './job-execution.model';
import { JobExecutionPopupService } from './job-execution-popup.service';
import { JobExecutionService } from './job-execution.service';

@Component({
    selector: 'jhi-job-execution-delete-dialog',
    templateUrl: './job-execution-delete-dialog.component.html'
})
export class JobExecutionDeleteDialogComponent {

    jobExecution: JobExecution;

    constructor(
        private jobExecutionService: JobExecutionService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear () {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete (id: number) {
        this.jobExecutionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'jobExecutionListModification',
                content: 'Deleted an jobExecution'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-job-execution-delete-popup',
    template: ''
})
export class JobExecutionDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private jobExecutionPopupService: JobExecutionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            this.modalRef = this.jobExecutionPopupService
                .open(JobExecutionDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
