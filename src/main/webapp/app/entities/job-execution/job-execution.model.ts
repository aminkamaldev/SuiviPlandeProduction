export class JobExecution {
    constructor(
        public id?: number,
        public jobId?: string,
        public jobName?: string,
        public status?: string,
        public startTime?: any,
        public endTime?: any,
        public deleted?: boolean,
        public oDate?: number,
        public orderId?: string,
        public averageRunTime?: number,
        public rerunCount?: number,
    ) { }
}
