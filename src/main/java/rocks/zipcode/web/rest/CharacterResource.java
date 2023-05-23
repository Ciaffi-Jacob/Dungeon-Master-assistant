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
import rocks.zipcode.domain.Character;
import rocks.zipcode.repository.CharacterRepository;
import rocks.zipcode.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link rocks.zipcode.domain.Character}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CharacterResource {

    private final Logger log = LoggerFactory.getLogger(CharacterResource.class);

    private static final String ENTITY_NAME = "character";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CharacterRepository characterRepository;

    public CharacterResource(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    /**
     * {@code POST  /characters} : Create a new character.
     *
     * @param character the character to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new character, or with status {@code 400 (Bad Request)} if the character has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/characters")
    public ResponseEntity<Character> createCharacter(@RequestBody Character character) throws URISyntaxException {
        log.debug("REST request to save Character : {}", character);
        if (character.getId() != null) {
            throw new BadRequestAlertException("A new character cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Character result = characterRepository.save(character);
        return ResponseEntity
            .created(new URI("/api/characters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /characters/:id} : Updates an existing character.
     *
     * @param id the id of the character to save.
     * @param character the character to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated character,
     * or with status {@code 400 (Bad Request)} if the character is not valid,
     * or with status {@code 500 (Internal Server Error)} if the character couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/characters/{id}")
    public ResponseEntity<Character> updateCharacter(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Character character
    ) throws URISyntaxException {
        log.debug("REST request to update Character : {}, {}", id, character);
        if (character.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, character.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!characterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Character result = characterRepository.save(character);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, character.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /characters/:id} : Partial updates given fields of an existing character, field will ignore if it is null
     *
     * @param id the id of the character to save.
     * @param character the character to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated character,
     * or with status {@code 400 (Bad Request)} if the character is not valid,
     * or with status {@code 404 (Not Found)} if the character is not found,
     * or with status {@code 500 (Internal Server Error)} if the character couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/characters/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Character> partialUpdateCharacter(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Character character
    ) throws URISyntaxException {
        log.debug("REST request to partial update Character partially : {}, {}", id, character);
        if (character.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, character.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!characterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Character> result = characterRepository
            .findById(character.getId())
            .map(existingCharacter -> {
                if (character.getUid() != null) {
                    existingCharacter.setUid(character.getUid());
                }
                if (character.getName() != null) {
                    existingCharacter.setName(character.getName());
                }
                if (character.getLevel() != null) {
                    existingCharacter.setLevel(character.getLevel());
                }
                if (character.getStrength() != null) {
                    existingCharacter.setStrength(character.getStrength());
                }
                if (character.getStrengthProficiency() != null) {
                    existingCharacter.setStrengthProficiency(character.getStrengthProficiency());
                }
                if (character.getDexterity() != null) {
                    existingCharacter.setDexterity(character.getDexterity());
                }
                if (character.getDexterityProficiency() != null) {
                    existingCharacter.setDexterityProficiency(character.getDexterityProficiency());
                }
                if (character.getConstitution() != null) {
                    existingCharacter.setConstitution(character.getConstitution());
                }
                if (character.getConstitutionProficiency() != null) {
                    existingCharacter.setConstitutionProficiency(character.getConstitutionProficiency());
                }
                if (character.getIntelligence() != null) {
                    existingCharacter.setIntelligence(character.getIntelligence());
                }
                if (character.getIntelligenceProficiency() != null) {
                    existingCharacter.setIntelligenceProficiency(character.getIntelligenceProficiency());
                }
                if (character.getCharisma() != null) {
                    existingCharacter.setCharisma(character.getCharisma());
                }
                if (character.getCharismaProficiency() != null) {
                    existingCharacter.setCharismaProficiency(character.getCharismaProficiency());
                }
                if (character.getWisdom() != null) {
                    existingCharacter.setWisdom(character.getWisdom());
                }
                if (character.getWisdomProficiency() != null) {
                    existingCharacter.setWisdomProficiency(character.getWisdomProficiency());
                }
                if (character.getPassiveInsight() != null) {
                    existingCharacter.setPassiveInsight(character.getPassiveInsight());
                }
                if (character.getPassivePerception() != null) {
                    existingCharacter.setPassivePerception(character.getPassivePerception());
                }
                if (character.getAcrobatics() != null) {
                    existingCharacter.setAcrobatics(character.getAcrobatics());
                }
                if (character.getAnimalHandling() != null) {
                    existingCharacter.setAnimalHandling(character.getAnimalHandling());
                }
                if (character.getArcana() != null) {
                    existingCharacter.setArcana(character.getArcana());
                }
                if (character.getAthletics() != null) {
                    existingCharacter.setAthletics(character.getAthletics());
                }
                if (character.getDeception() != null) {
                    existingCharacter.setDeception(character.getDeception());
                }
                if (character.getHistory() != null) {
                    existingCharacter.setHistory(character.getHistory());
                }
                if (character.getInsight() != null) {
                    existingCharacter.setInsight(character.getInsight());
                }
                if (character.getIntimidation() != null) {
                    existingCharacter.setIntimidation(character.getIntimidation());
                }
                if (character.getInvestigation() != null) {
                    existingCharacter.setInvestigation(character.getInvestigation());
                }
                if (character.getMedicine() != null) {
                    existingCharacter.setMedicine(character.getMedicine());
                }
                if (character.getNature() != null) {
                    existingCharacter.setNature(character.getNature());
                }
                if (character.getPerception() != null) {
                    existingCharacter.setPerception(character.getPerception());
                }
                if (character.getPerformance() != null) {
                    existingCharacter.setPerformance(character.getPerformance());
                }
                if (character.getPersuasion() != null) {
                    existingCharacter.setPersuasion(character.getPersuasion());
                }
                if (character.getReligion() != null) {
                    existingCharacter.setReligion(character.getReligion());
                }
                if (character.getSleightOfHand() != null) {
                    existingCharacter.setSleightOfHand(character.getSleightOfHand());
                }
                if (character.getStealth() != null) {
                    existingCharacter.setStealth(character.getStealth());
                }
                if (character.getSurvival() != null) {
                    existingCharacter.setSurvival(character.getSurvival());
                }

                return existingCharacter;
            })
            .map(characterRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, character.getId().toString())
        );
    }

    /**
     * {@code GET  /characters} : get all the characters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of characters in body.
     */
    @GetMapping("/characters")
    public List<Character> getAllCharacters() {
        log.debug("REST request to get all Characters");
        return characterRepository.findAll();
    }

    /**
     * {@code GET  /characters/:id} : get the "id" character.
     *
     * @param id the id of the character to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the character, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/characters/{id}")
    public ResponseEntity<Character> getCharacter(@PathVariable Long id) {
        log.debug("REST request to get Character : {}", id);
        Optional<Character> character = characterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(character);
    }

    /**
     * {@code DELETE  /characters/:id} : delete the "id" character.
     *
     * @param id the id of the character to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/characters/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        log.debug("REST request to delete Character : {}", id);
        characterRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
