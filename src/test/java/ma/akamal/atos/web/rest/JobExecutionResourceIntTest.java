package ma.akamal.atos.web.rest;

import ma.akamal.atos.SuiviPlanProductionApp;

import ma.akamal.atos.domain.JobExecution;
import ma.akamal.atos.repository.JobExecutionRepository;
import ma.akamal.atos.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the JobExecutionResource REST controller.
 *
 * @see JobExecutionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuiviPlanProductionApp.class)
public class JobExecutionResourceIntTest {

    private static final String DEFAULT_JOB_ID = "AAAAAAAAAA";
    private static final String UPDATED_JOB_ID = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_JOB_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_START_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_DELETED = false;
    private static final Boolean UPDATED_DELETED = true;

    private static final Long DEFAULT_O_DATE = 1L;
    private static final Long UPDATED_O_DATE = 2L;

    private static final String DEFAULT_ORDER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_AVERAGE_RUN_TIME = 1L;
    private static final Long UPDATED_AVERAGE_RUN_TIME = 2L;

    private static final Integer DEFAULT_RERUN_COUNT = 1;
    private static final Integer UPDATED_RERUN_COUNT = 2;

    @Autowired
    private JobExecutionRepository jobExecutionRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restJobExecutionMockMvc;

    private JobExecution jobExecution;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
            JobExecutionResource jobExecutionResource = new JobExecutionResource(jobExecutionRepository);
        this.restJobExecutionMockMvc = MockMvcBuilders.standaloneSetup(jobExecutionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JobExecution createEntity(EntityManager em) {
        JobExecution jobExecution = new JobExecution()
                .jobId(DEFAULT_JOB_ID)
                .jobName(DEFAULT_JOB_NAME)
                .status(DEFAULT_STATUS)
                .startTime(DEFAULT_START_TIME)
                .endTime(DEFAULT_END_TIME)
                .deleted(DEFAULT_DELETED)
                .oDate(DEFAULT_O_DATE)
                .orderId(DEFAULT_ORDER_ID)
                .averageRunTime(DEFAULT_AVERAGE_RUN_TIME)
                .rerunCount(DEFAULT_RERUN_COUNT);
        return jobExecution;
    }

    @Before
    public void initTest() {
        jobExecution = createEntity(em);
    }

    @Test
    @Transactional
    public void createJobExecution() throws Exception {
        int databaseSizeBeforeCreate = jobExecutionRepository.findAll().size();

        // Create the JobExecution

        restJobExecutionMockMvc.perform(post("/api/job-executions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobExecution)))
            .andExpect(status().isCreated());

        // Validate the JobExecution in the database
        List<JobExecution> jobExecutionList = jobExecutionRepository.findAll();
        assertThat(jobExecutionList).hasSize(databaseSizeBeforeCreate + 1);
        JobExecution testJobExecution = jobExecutionList.get(jobExecutionList.size() - 1);
        assertThat(testJobExecution.getJobId()).isEqualTo(DEFAULT_JOB_ID);
        assertThat(testJobExecution.getJobName()).isEqualTo(DEFAULT_JOB_NAME);
        assertThat(testJobExecution.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testJobExecution.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testJobExecution.getEndTime()).isEqualTo(DEFAULT_END_TIME);
        assertThat(testJobExecution.isDeleted()).isEqualTo(DEFAULT_DELETED);
        assertThat(testJobExecution.getoDate()).isEqualTo(DEFAULT_O_DATE);
        assertThat(testJobExecution.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testJobExecution.getAverageRunTime()).isEqualTo(DEFAULT_AVERAGE_RUN_TIME);
        assertThat(testJobExecution.getRerunCount()).isEqualTo(DEFAULT_RERUN_COUNT);
    }

    @Test
    @Transactional
    public void createJobExecutionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = jobExecutionRepository.findAll().size();

        // Create the JobExecution with an existing ID
        JobExecution existingJobExecution = new JobExecution();
        existingJobExecution.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJobExecutionMockMvc.perform(post("/api/job-executions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingJobExecution)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<JobExecution> jobExecutionList = jobExecutionRepository.findAll();
        assertThat(jobExecutionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkJobIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = jobExecutionRepository.findAll().size();
        // set the field null
        jobExecution.setJobId(null);

        // Create the JobExecution, which fails.

        restJobExecutionMockMvc.perform(post("/api/job-executions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobExecution)))
            .andExpect(status().isBadRequest());

        List<JobExecution> jobExecutionList = jobExecutionRepository.findAll();
        assertThat(jobExecutionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkJobNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = jobExecutionRepository.findAll().size();
        // set the field null
        jobExecution.setJobName(null);

        // Create the JobExecution, which fails.

        restJobExecutionMockMvc.perform(post("/api/job-executions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobExecution)))
            .andExpect(status().isBadRequest());

        List<JobExecution> jobExecutionList = jobExecutionRepository.findAll();
        assertThat(jobExecutionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = jobExecutionRepository.findAll().size();
        // set the field null
        jobExecution.setStatus(null);

        // Create the JobExecution, which fails.

        restJobExecutionMockMvc.perform(post("/api/job-executions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobExecution)))
            .andExpect(status().isBadRequest());

        List<JobExecution> jobExecutionList = jobExecutionRepository.findAll();
        assertThat(jobExecutionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllJobExecutions() throws Exception {
        // Initialize the database
        jobExecutionRepository.saveAndFlush(jobExecution);

        // Get all the jobExecutionList
        restJobExecutionMockMvc.perform(get("/api/job-executions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jobExecution.getId().intValue())))
            .andExpect(jsonPath("$.[*].jobId").value(hasItem(DEFAULT_JOB_ID.toString())))
            .andExpect(jsonPath("$.[*].jobName").value(hasItem(DEFAULT_JOB_NAME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME.toString())))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].oDate").value(hasItem(DEFAULT_O_DATE.intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.toString())))
            .andExpect(jsonPath("$.[*].averageRunTime").value(hasItem(DEFAULT_AVERAGE_RUN_TIME.intValue())))
            .andExpect(jsonPath("$.[*].rerunCount").value(hasItem(DEFAULT_RERUN_COUNT)));
    }

    @Test
    @Transactional
    public void getJobExecution() throws Exception {
        // Initialize the database
        jobExecutionRepository.saveAndFlush(jobExecution);

        // Get the jobExecution
        restJobExecutionMockMvc.perform(get("/api/job-executions/{id}", jobExecution.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(jobExecution.getId().intValue()))
            .andExpect(jsonPath("$.jobId").value(DEFAULT_JOB_ID.toString()))
            .andExpect(jsonPath("$.jobName").value(DEFAULT_JOB_NAME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME.toString()))
            .andExpect(jsonPath("$.endTime").value(DEFAULT_END_TIME.toString()))
            .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()))
            .andExpect(jsonPath("$.oDate").value(DEFAULT_O_DATE.intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.toString()))
            .andExpect(jsonPath("$.averageRunTime").value(DEFAULT_AVERAGE_RUN_TIME.intValue()))
            .andExpect(jsonPath("$.rerunCount").value(DEFAULT_RERUN_COUNT));
    }

    @Test
    @Transactional
    public void getNonExistingJobExecution() throws Exception {
        // Get the jobExecution
        restJobExecutionMockMvc.perform(get("/api/job-executions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJobExecution() throws Exception {
        // Initialize the database
        jobExecutionRepository.saveAndFlush(jobExecution);
        int databaseSizeBeforeUpdate = jobExecutionRepository.findAll().size();

        // Update the jobExecution
        JobExecution updatedJobExecution = jobExecutionRepository.findOne(jobExecution.getId());
        updatedJobExecution
                .jobId(UPDATED_JOB_ID)
                .jobName(UPDATED_JOB_NAME)
                .status(UPDATED_STATUS)
                .startTime(UPDATED_START_TIME)
                .endTime(UPDATED_END_TIME)
                .deleted(UPDATED_DELETED)
                .oDate(UPDATED_O_DATE)
                .orderId(UPDATED_ORDER_ID)
                .averageRunTime(UPDATED_AVERAGE_RUN_TIME)
                .rerunCount(UPDATED_RERUN_COUNT);

        restJobExecutionMockMvc.perform(put("/api/job-executions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedJobExecution)))
            .andExpect(status().isOk());

        // Validate the JobExecution in the database
        List<JobExecution> jobExecutionList = jobExecutionRepository.findAll();
        assertThat(jobExecutionList).hasSize(databaseSizeBeforeUpdate);
        JobExecution testJobExecution = jobExecutionList.get(jobExecutionList.size() - 1);
        assertThat(testJobExecution.getJobId()).isEqualTo(UPDATED_JOB_ID);
        assertThat(testJobExecution.getJobName()).isEqualTo(UPDATED_JOB_NAME);
        assertThat(testJobExecution.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testJobExecution.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testJobExecution.getEndTime()).isEqualTo(UPDATED_END_TIME);
        assertThat(testJobExecution.isDeleted()).isEqualTo(UPDATED_DELETED);
        assertThat(testJobExecution.getoDate()).isEqualTo(UPDATED_O_DATE);
        assertThat(testJobExecution.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testJobExecution.getAverageRunTime()).isEqualTo(UPDATED_AVERAGE_RUN_TIME);
        assertThat(testJobExecution.getRerunCount()).isEqualTo(UPDATED_RERUN_COUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingJobExecution() throws Exception {
        int databaseSizeBeforeUpdate = jobExecutionRepository.findAll().size();

        // Create the JobExecution

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restJobExecutionMockMvc.perform(put("/api/job-executions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jobExecution)))
            .andExpect(status().isCreated());

        // Validate the JobExecution in the database
        List<JobExecution> jobExecutionList = jobExecutionRepository.findAll();
        assertThat(jobExecutionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteJobExecution() throws Exception {
        // Initialize the database
        jobExecutionRepository.saveAndFlush(jobExecution);
        int databaseSizeBeforeDelete = jobExecutionRepository.findAll().size();

        // Get the jobExecution
        restJobExecutionMockMvc.perform(delete("/api/job-executions/{id}", jobExecution.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<JobExecution> jobExecutionList = jobExecutionRepository.findAll();
        assertThat(jobExecutionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JobExecution.class);
    }
}
