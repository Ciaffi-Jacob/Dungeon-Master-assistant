package rocks.zipcode.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import rocks.zipcode.domain.Log;
import rocks.zipcode.repository.LogRepository;
import rocks.zipcode.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link rocks.zipcode.domain.Log}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LogResource {

    private final Logger log = LoggerFactory.getLogger(LogResource.class);

    private static final String ENTITY_NAME = "log";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LogRepository logRepository;

    public LogResource(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    /**
     * {@code POST  /logs} : Create a new log.
     *
     * @param log the log to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new log, or with status {@code 400 (Bad Request)} if the log has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/logs")
    public ResponseEntity<Log> createLog(@RequestBody Log log) throws URISyntaxException {
        log.debug("REST request to save Log : {}", null, log);
        if (log.getId() != null) {
            throw new BadRequestAlertException("A new log cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Log result = logRepository.save(log);
        return ResponseEntity
            .created(new URI("/api/logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /logs/:id} : Updates an existing log.
     *
     * @param id the id of the log to save.
     * @param log the log to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated log,
     * or with status {@code 400 (Bad Request)} if the log is not valid,
     * or with status {@code 500 (Internal Server Error)} if the log couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/logs/{id}")
    public ResponseEntity<Log> updateLog(@PathVariable(value = "id", required = false) final Long id, @RequestBody Log log)
        throws URISyntaxException {
        log.debug("REST request to update Log : {}, {}", id, log);
        if (log.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, log.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!logRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Log result = logRepository.save(log);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, log.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /logs/:id} : Partial updates given fields of an existing log, field will ignore if it is null
     *
     * @param id the id of the log to save.
     * @param log the log to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated log,
     * or with status {@code 400 (Bad Request)} if the log is not valid,
     * or with status {@code 404 (Not Found)} if the log is not found,
     * or with status {@code 500 (Internal Server Error)} if the log couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Log> partialUpdateLog(@PathVariable(value = "id", required = false) final Long id, @RequestBody Log log)
        throws URISyntaxException {
        log.debug("REST request to partial update Log partially : {}, {}", id, log);
        if (log.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, log.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!logRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Log> result = logRepository
            .findById(log.getId())
            .map(existingLog -> {
                if (log.getEntry() != null) {
                    existingLog.setEntry(log.getEntry());
                }

                return existingLog;
            })
            .map(logRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, log.getId().toString())
        );
    }

    /**
     * {@code GET  /logs} : get all the logs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of logs in body.
     */
    @GetMapping("/logs")
    public List<Log> getAllLogs() {
        log.debug("REST request to get all Logs");
        return logRepository.findAll();
    }

    /**
     * {@code GET  /logs/:id} : get the "id" log.
     *
     * @param id the id of the log to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the log, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/logs/{id}")
    public ResponseEntity<Log> getLog(@PathVariable Long id) {
        log.debug("REST request to get Log : {}", id);
        Optional<Log> log = logRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(log);
    }

    /**
     * {@code DELETE  /logs/:id} : delete the "id" log.
     *
     * @param id the id of the log to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/logs/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable Long id) {
        log.debug("REST request to delete Log : {}", id);
        logRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
