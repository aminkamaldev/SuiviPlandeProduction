import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JobExecution } from './job-execution.model';
import { JobExecutionService } from './job-execution.service';
@Injectable()
export class JobExecutionPopupService {
    private isOpen = false;
    constructor (
        private modalService: NgbModal,
        private router: Router,
        private jobExecutionService: JobExecutionService
    ) {}

    open (component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.jobExecutionService.find(id).subscribe(jobExecution => {
                if (jobExecution.startTime) {
                    jobExecution.startTime = {
                        year: jobExecution.startTime.getFullYear(),
                        month: jobExecution.startTime.getMonth() + 1,
                        day: jobExecution.startTime.getDate()
                    };
                }
                if (jobExecution.endTime) {
                    jobExecution.endTime = {
                        year: jobExecution.endTime.getFullYear(),
                        month: jobExecution.endTime.getMonth() + 1,
                        day: jobExecution.endTime.getDate()
                    };
                }
                this.jobExecutionModalRef(component, jobExecution);
            });
        } else {
            return this.jobExecutionModalRef(component, new JobExecution());
        }
    }

    jobExecutionModalRef(component: Component, jobExecution: JobExecution): NgbModalRef {
        let modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.jobExecution = jobExecution;
        modalRef.result.then(result => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}
