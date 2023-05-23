package rocks.zipcode.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import rocks.zipcode.IntegrationTest;
import rocks.zipcode.domain.Log;
import rocks.zipcode.repository.LogRepository;

/**
 * Integration tests for the {@link LogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LogResourceIT {

    private static final String DEFAULT_ENTRY = "AAAAAAAAAA";
    private static final String UPDATED_ENTRY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLogMockMvc;

    private Log log;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Log createEntity(EntityManager em) {
        Log log = new Log().entry(DEFAULT_ENTRY);
        return log;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Log createUpdatedEntity(EntityManager em) {
        Log log = new Log().entry(UPDATED_ENTRY);
        return log;
    }

    @BeforeEach
    public void initTest() {
        log = createEntity(em);
    }

    @Test
    @Transactional
    void createLog() throws Exception {
        int databaseSizeBeforeCreate = logRepository.findAll().size();
        // Create the Log
        restLogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(log)))
            .andExpect(status().isCreated());

        // Validate the Log in the database
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeCreate + 1);
        Log testLog = logList.get(logList.size() - 1);
        assertThat(testLog.getEntry()).isEqualTo(DEFAULT_ENTRY);
    }

    @Test
    @Transactional
    void createLogWithExistingId() throws Exception {
        // Create the Log with an existing ID
        log.setId(1L);

        int databaseSizeBeforeCreate = logRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(log)))
            .andExpect(status().isBadRequest());

        // Validate the Log in the database
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLogs() throws Exception {
        // Initialize the database
        logRepository.saveAndFlush(log);

        // Get all the logList
        restLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(log.getId().intValue())))
            .andExpect(jsonPath("$.[*].entry").value(hasItem(DEFAULT_ENTRY)));
    }

    @Test
    @Transactional
    void getLog() throws Exception {
        // Initialize the database
        logRepository.saveAndFlush(log);

        // Get the log
        restLogMockMvc
            .perform(get(ENTITY_API_URL_ID, log.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(log.getId().intValue()))
            .andExpect(jsonPath("$.entry").value(DEFAULT_ENTRY));
    }

    @Test
    @Transactional
    void getNonExistingLog() throws Exception {
        // Get the log
        restLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLog() throws Exception {
        // Initialize the database
        logRepository.saveAndFlush(log);

        int databaseSizeBeforeUpdate = logRepository.findAll().size();

        // Update the log
        Log updatedLog = logRepository.findById(log.getId()).get();
        // Disconnect from session so that the updates on updatedLog are not directly saved in db
        em.detach(updatedLog);
        updatedLog.entry(UPDATED_ENTRY);

        restLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedLog))
            )
            .andExpect(status().isOk());

        // Validate the Log in the database
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeUpdate);
        Log testLog = logList.get(logList.size() - 1);
        assertThat(testLog.getEntry()).isEqualTo(UPDATED_ENTRY);
    }

    @Test
    @Transactional
    void putNonExistingLog() throws Exception {
        int databaseSizeBeforeUpdate = logRepository.findAll().size();
        log.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, log.getId()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(log))
            )
            .andExpect(status().isBadRequest());

        // Validate the Log in the database
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLog() throws Exception {
        int databaseSizeBeforeUpdate = logRepository.findAll().size();
        log.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(log))
            )
            .andExpect(status().isBadRequest());

        // Validate the Log in the database
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLog() throws Exception {
        int databaseSizeBeforeUpdate = logRepository.findAll().size();
        log.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLogMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(log)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Log in the database
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLogWithPatch() throws Exception {
        // Initialize the database
        logRepository.saveAndFlush(log);

        int databaseSizeBeforeUpdate = logRepository.findAll().size();

        // Update the log using partial update
        Log partialUpdatedLog = new Log();
        partialUpdatedLog.setId(log.getId());

        restLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLog))
            )
            .andExpect(status().isOk());

        // Validate the Log in the database
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeUpdate);
        Log testLog = logList.get(logList.size() - 1);
        assertThat(testLog.getEntry()).isEqualTo(DEFAULT_ENTRY);
    }

    @Test
    @Transactional
    void fullUpdateLogWithPatch() throws Exception {
        // Initialize the database
        logRepository.saveAndFlush(log);

        int databaseSizeBeforeUpdate = logRepository.findAll().size();

        // Update the log using partial update
        Log partialUpdatedLog = new Log();
        partialUpdatedLog.setId(log.getId());

        partialUpdatedLog.entry(UPDATED_ENTRY);

        restLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLog))
            )
            .andExpect(status().isOk());

        // Validate the Log in the database
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeUpdate);
        Log testLog = logList.get(logList.size() - 1);
        assertThat(testLog.getEntry()).isEqualTo(UPDATED_ENTRY);
    }

    @Test
    @Transactional
    void patchNonExistingLog() throws Exception {
        int databaseSizeBeforeUpdate = logRepository.findAll().size();
        log.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, log.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(log))
            )
            .andExpect(status().isBadRequest());

        // Validate the Log in the database
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLog() throws Exception {
        int databaseSizeBeforeUpdate = logRepository.findAll().size();
        log.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(log))
            )
            .andExpect(status().isBadRequest());

        // Validate the Log in the database
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLog() throws Exception {
        int databaseSizeBeforeUpdate = logRepository.findAll().size();
        log.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLogMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(log)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Log in the database
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLog() throws Exception {
        // Initialize the database
        logRepository.saveAndFlush(log);

        int databaseSizeBeforeDelete = logRepository.findAll().size();

        // Delete the log
        restLogMockMvc.perform(delete(ENTITY_API_URL_ID, log.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Log> logList = logRepository.findAll();
        assertThat(logList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
