package ma.akamal.atos.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A JobExecution.
 */
@Entity
@Table(name = "job_execution")
public class JobExecution implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "job_id", nullable = false)
    private String jobId;

    @NotNull
    @Column(name = "job_name", nullable = false)
    private String jobName;

    @NotNull
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "start_time")
    private LocalDate startTime;

    @Column(name = "end_time")
    private LocalDate endTime;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "o_date")
    private Long oDate;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "average_run_time")
    private Long averageRunTime;

    @Column(name = "rerun_count")
    private Integer rerunCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public JobExecution jobId(String jobId) {
        this.jobId = jobId;
        return this;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public JobExecution jobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getStatus() {
        return status;
    }

    public JobExecution status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public JobExecution startTime(LocalDate startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public JobExecution endTime(LocalDate endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public JobExecution deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getoDate() {
        return oDate;
    }

    public JobExecution oDate(Long oDate) {
        this.oDate = oDate;
        return this;
    }

    public void setoDate(Long oDate) {
        this.oDate = oDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public JobExecution orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getAverageRunTime() {
        return averageRunTime;
    }

    public JobExecution averageRunTime(Long averageRunTime) {
        this.averageRunTime = averageRunTime;
        return this;
    }

    public void setAverageRunTime(Long averageRunTime) {
        this.averageRunTime = averageRunTime;
    }

    public Integer getRerunCount() {
        return rerunCount;
    }

    public JobExecution rerunCount(Integer rerunCount) {
        this.rerunCount = rerunCount;
        return this;
    }

    public void setRerunCount(Integer rerunCount) {
        this.rerunCount = rerunCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JobExecution jobExecution = (JobExecution) o;
        if (jobExecution.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, jobExecution.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "JobExecution{" +
            "id=" + id +
            ", jobId='" + jobId + "'" +
            ", jobName='" + jobName + "'" +
            ", status='" + status + "'" +
            ", startTime='" + startTime + "'" +
            ", endTime='" + endTime + "'" +
            ", deleted='" + deleted + "'" +
            ", oDate='" + oDate + "'" +
            ", orderId='" + orderId + "'" +
            ", averageRunTime='" + averageRunTime + "'" +
            ", rerunCount='" + rerunCount + "'" +
            '}';
    }
}
