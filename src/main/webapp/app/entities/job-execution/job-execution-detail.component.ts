import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JobExecution } from './job-execution.model';
import { JobExecutionService } from './job-execution.service';

@Component({
    selector: 'jhi-job-execution-detail',
    templateUrl: './job-execution-detail.component.html'
})
export class JobExecutionDetailComponent implements OnInit, OnDestroy {

    jobExecution: JobExecution;
    private subscription: any;

    constructor(
        private jobExecutionService: JobExecutionService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe(params => {
            this.load(params['id']);
        });
    }

    load (id) {
        this.jobExecutionService.find(id).subscribe(jobExecution => {
            this.jobExecution = jobExecution;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}
