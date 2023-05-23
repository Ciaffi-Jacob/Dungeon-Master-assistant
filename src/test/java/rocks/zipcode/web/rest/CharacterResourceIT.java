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
import rocks.zipcode.domain.Character;
import rocks.zipcode.repository.CharacterRepository;

/**
 * Integration tests for the {@link CharacterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CharacterResourceIT {

    private static final Long DEFAULT_UID = 1L;
    private static final Long UPDATED_UID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final Integer DEFAULT_STRENGTH = 1;
    private static final Integer UPDATED_STRENGTH = 2;

    private static final Boolean DEFAULT_STRENGTH_PROFICIENCY = false;
    private static final Boolean UPDATED_STRENGTH_PROFICIENCY = true;

    private static final Integer DEFAULT_DEXTERITY = 1;
    private static final Integer UPDATED_DEXTERITY = 2;

    private static final Boolean DEFAULT_DEXTERITY_PROFICIENCY = false;
    private static final Boolean UPDATED_DEXTERITY_PROFICIENCY = true;

    private static final Integer DEFAULT_CONSTITUTION = 1;
    private static final Integer UPDATED_CONSTITUTION = 2;

    private static final Boolean DEFAULT_CONSTITUTION_PROFICIENCY = false;
    private static final Boolean UPDATED_CONSTITUTION_PROFICIENCY = true;

    private static final Integer DEFAULT_INTELLIGENCE = 1;
    private static final Integer UPDATED_INTELLIGENCE = 2;

    private static final Boolean DEFAULT_INTELLIGENCE_PROFICIENCY = false;
    private static final Boolean UPDATED_INTELLIGENCE_PROFICIENCY = true;

    private static final Integer DEFAULT_CHARISMA = 1;
    private static final Integer UPDATED_CHARISMA = 2;

    private static final Boolean DEFAULT_CHARISMA_PROFICIENCY = false;
    private static final Boolean UPDATED_CHARISMA_PROFICIENCY = true;

    private static final Integer DEFAULT_WISDOM = 1;
    private static final Integer UPDATED_WISDOM = 2;

    private static final Boolean DEFAULT_WISDOM_PROFICIENCY = false;
    private static final Boolean UPDATED_WISDOM_PROFICIENCY = true;

    private static final Integer DEFAULT_PASSIVE_INSIGHT = 1;
    private static final Integer UPDATED_PASSIVE_INSIGHT = 2;

    private static final Integer DEFAULT_PASSIVE_PERCEPTION = 1;
    private static final Integer UPDATED_PASSIVE_PERCEPTION = 2;

    private static final Boolean DEFAULT_ACROBATICS = false;
    private static final Boolean UPDATED_ACROBATICS = true;

    private static final Boolean DEFAULT_ANIMAL_HANDLING = false;
    private static final Boolean UPDATED_ANIMAL_HANDLING = true;

    private static final Boolean DEFAULT_ARCANA = false;
    private static final Boolean UPDATED_ARCANA = true;

    private static final Boolean DEFAULT_ATHLETICS = false;
    private static final Boolean UPDATED_ATHLETICS = true;

    private static final Boolean DEFAULT_DECEPTION = false;
    private static final Boolean UPDATED_DECEPTION = true;

    private static final Boolean DEFAULT_HISTORY = false;
    private static final Boolean UPDATED_HISTORY = true;

    private static final Boolean DEFAULT_INSIGHT = false;
    private static final Boolean UPDATED_INSIGHT = true;

    private static final Boolean DEFAULT_INTIMIDATION = false;
    private static final Boolean UPDATED_INTIMIDATION = true;

    private static final Boolean DEFAULT_INVESTIGATION = false;
    private static final Boolean UPDATED_INVESTIGATION = true;

    private static final Boolean DEFAULT_MEDICINE = false;
    private static final Boolean UPDATED_MEDICINE = true;

    private static final Boolean DEFAULT_NATURE = false;
    private static final Boolean UPDATED_NATURE = true;

    private static final Boolean DEFAULT_PERCEPTION = false;
    private static final Boolean UPDATED_PERCEPTION = true;

    private static final Boolean DEFAULT_PERFORMANCE = false;
    private static final Boolean UPDATED_PERFORMANCE = true;

    private static final Boolean DEFAULT_PERSUASION = false;
    private static final Boolean UPDATED_PERSUASION = true;

    private static final Boolean DEFAULT_RELIGION = false;
    private static final Boolean UPDATED_RELIGION = true;

    private static final Boolean DEFAULT_SLEIGHT_OF_HAND = false;
    private static final Boolean UPDATED_SLEIGHT_OF_HAND = true;

    private static final Boolean DEFAULT_STEALTH = false;
    private static final Boolean UPDATED_STEALTH = true;

    private static final Boolean DEFAULT_SURVIVAL = false;
    private static final Boolean UPDATED_SURVIVAL = true;

    private static final String ENTITY_API_URL = "/api/characters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCharacterMockMvc;

    private Character character;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Character createEntity(EntityManager em) {
        Character character = new Character()
            .uid(DEFAULT_UID)
            .name(DEFAULT_NAME)
            .level(DEFAULT_LEVEL)
            .strength(DEFAULT_STRENGTH)
            .strengthProficiency(DEFAULT_STRENGTH_PROFICIENCY)
            .dexterity(DEFAULT_DEXTERITY)
            .dexterityProficiency(DEFAULT_DEXTERITY_PROFICIENCY)
            .constitution(DEFAULT_CONSTITUTION)
            .constitutionProficiency(DEFAULT_CONSTITUTION_PROFICIENCY)
            .intelligence(DEFAULT_INTELLIGENCE)
            .intelligenceProficiency(DEFAULT_INTELLIGENCE_PROFICIENCY)
            .charisma(DEFAULT_CHARISMA)
            .charismaProficiency(DEFAULT_CHARISMA_PROFICIENCY)
            .wisdom(DEFAULT_WISDOM)
            .wisdomProficiency(DEFAULT_WISDOM_PROFICIENCY)
            .passiveInsight(DEFAULT_PASSIVE_INSIGHT)
            .passivePerception(DEFAULT_PASSIVE_PERCEPTION)
            .acrobatics(DEFAULT_ACROBATICS)
            .animalHandling(DEFAULT_ANIMAL_HANDLING)
            .arcana(DEFAULT_ARCANA)
            .athletics(DEFAULT_ATHLETICS)
            .deception(DEFAULT_DECEPTION)
            .history(DEFAULT_HISTORY)
            .insight(DEFAULT_INSIGHT)
            .intimidation(DEFAULT_INTIMIDATION)
            .investigation(DEFAULT_INVESTIGATION)
            .medicine(DEFAULT_MEDICINE)
            .nature(DEFAULT_NATURE)
            .perception(DEFAULT_PERCEPTION)
            .performance(DEFAULT_PERFORMANCE)
            .persuasion(DEFAULT_PERSUASION)
            .religion(DEFAULT_RELIGION)
            .sleightOfHand(DEFAULT_SLEIGHT_OF_HAND)
            .stealth(DEFAULT_STEALTH)
            .survival(DEFAULT_SURVIVAL);
        return character;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Character createUpdatedEntity(EntityManager em) {
        Character character = new Character()
            .uid(UPDATED_UID)
            .name(UPDATED_NAME)
            .level(UPDATED_LEVEL)
            .strength(UPDATED_STRENGTH)
            .strengthProficiency(UPDATED_STRENGTH_PROFICIENCY)
            .dexterity(UPDATED_DEXTERITY)
            .dexterityProficiency(UPDATED_DEXTERITY_PROFICIENCY)
            .constitution(UPDATED_CONSTITUTION)
            .constitutionProficiency(UPDATED_CONSTITUTION_PROFICIENCY)
            .intelligence(UPDATED_INTELLIGENCE)
            .intelligenceProficiency(UPDATED_INTELLIGENCE_PROFICIENCY)
            .charisma(UPDATED_CHARISMA)
            .charismaProficiency(UPDATED_CHARISMA_PROFICIENCY)
            .wisdom(UPDATED_WISDOM)
            .wisdomProficiency(UPDATED_WISDOM_PROFICIENCY)
            .passiveInsight(UPDATED_PASSIVE_INSIGHT)
            .passivePerception(UPDATED_PASSIVE_PERCEPTION)
            .acrobatics(UPDATED_ACROBATICS)
            .animalHandling(UPDATED_ANIMAL_HANDLING)
            .arcana(UPDATED_ARCANA)
            .athletics(UPDATED_ATHLETICS)
            .deception(UPDATED_DECEPTION)
            .history(UPDATED_HISTORY)
            .insight(UPDATED_INSIGHT)
            .intimidation(UPDATED_INTIMIDATION)
            .investigation(UPDATED_INVESTIGATION)
            .medicine(UPDATED_MEDICINE)
            .nature(UPDATED_NATURE)
            .perception(UPDATED_PERCEPTION)
            .performance(UPDATED_PERFORMANCE)
            .persuasion(UPDATED_PERSUASION)
            .religion(UPDATED_RELIGION)
            .sleightOfHand(UPDATED_SLEIGHT_OF_HAND)
            .stealth(UPDATED_STEALTH)
            .survival(UPDATED_SURVIVAL);
        return character;
    }

    @BeforeEach
    public void initTest() {
        character = createEntity(em);
    }

    @Test
    @Transactional
    void createCharacter() throws Exception {
        int databaseSizeBeforeCreate = characterRepository.findAll().size();
        // Create the Character
        restCharacterMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(character)))
            .andExpect(status().isCreated());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeCreate + 1);
        Character testCharacter = characterList.get(characterList.size() - 1);
        assertThat(testCharacter.getUid()).isEqualTo(DEFAULT_UID);
        assertThat(testCharacter.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCharacter.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testCharacter.getStrength()).isEqualTo(DEFAULT_STRENGTH);
        assertThat(testCharacter.getStrengthProficiency()).isEqualTo(DEFAULT_STRENGTH_PROFICIENCY);
        assertThat(testCharacter.getDexterity()).isEqualTo(DEFAULT_DEXTERITY);
        assertThat(testCharacter.getDexterityProficiency()).isEqualTo(DEFAULT_DEXTERITY_PROFICIENCY);
        assertThat(testCharacter.getConstitution()).isEqualTo(DEFAULT_CONSTITUTION);
        assertThat(testCharacter.getConstitutionProficiency()).isEqualTo(DEFAULT_CONSTITUTION_PROFICIENCY);
        assertThat(testCharacter.getIntelligence()).isEqualTo(DEFAULT_INTELLIGENCE);
        assertThat(testCharacter.getIntelligenceProficiency()).isEqualTo(DEFAULT_INTELLIGENCE_PROFICIENCY);
        assertThat(testCharacter.getCharisma()).isEqualTo(DEFAULT_CHARISMA);
        assertThat(testCharacter.getCharismaProficiency()).isEqualTo(DEFAULT_CHARISMA_PROFICIENCY);
        assertThat(testCharacter.getWisdom()).isEqualTo(DEFAULT_WISDOM);
        assertThat(testCharacter.getWisdomProficiency()).isEqualTo(DEFAULT_WISDOM_PROFICIENCY);
        assertThat(testCharacter.getPassiveInsight()).isEqualTo(DEFAULT_PASSIVE_INSIGHT);
        assertThat(testCharacter.getPassivePerception()).isEqualTo(DEFAULT_PASSIVE_PERCEPTION);
        assertThat(testCharacter.getAcrobatics()).isEqualTo(DEFAULT_ACROBATICS);
        assertThat(testCharacter.getAnimalHandling()).isEqualTo(DEFAULT_ANIMAL_HANDLING);
        assertThat(testCharacter.getArcana()).isEqualTo(DEFAULT_ARCANA);
        assertThat(testCharacter.getAthletics()).isEqualTo(DEFAULT_ATHLETICS);
        assertThat(testCharacter.getDeception()).isEqualTo(DEFAULT_DECEPTION);
        assertThat(testCharacter.getHistory()).isEqualTo(DEFAULT_HISTORY);
        assertThat(testCharacter.getInsight()).isEqualTo(DEFAULT_INSIGHT);
        assertThat(testCharacter.getIntimidation()).isEqualTo(DEFAULT_INTIMIDATION);
        assertThat(testCharacter.getInvestigation()).isEqualTo(DEFAULT_INVESTIGATION);
        assertThat(testCharacter.getMedicine()).isEqualTo(DEFAULT_MEDICINE);
        assertThat(testCharacter.getNature()).isEqualTo(DEFAULT_NATURE);
        assertThat(testCharacter.getPerception()).isEqualTo(DEFAULT_PERCEPTION);
        assertThat(testCharacter.getPerformance()).isEqualTo(DEFAULT_PERFORMANCE);
        assertThat(testCharacter.getPersuasion()).isEqualTo(DEFAULT_PERSUASION);
        assertThat(testCharacter.getReligion()).isEqualTo(DEFAULT_RELIGION);
        assertThat(testCharacter.getSleightOfHand()).isEqualTo(DEFAULT_SLEIGHT_OF_HAND);
        assertThat(testCharacter.getStealth()).isEqualTo(DEFAULT_STEALTH);
        assertThat(testCharacter.getSurvival()).isEqualTo(DEFAULT_SURVIVAL);
    }

    @Test
    @Transactional
    void createCharacterWithExistingId() throws Exception {
        // Create the Character with an existing ID
        character.setId(1L);

        int databaseSizeBeforeCreate = characterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCharacterMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(character)))
            .andExpect(status().isBadRequest());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCharacters() throws Exception {
        // Initialize the database
        characterRepository.saveAndFlush(character);

        // Get all the characterList
        restCharacterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(character.getId().intValue())))
            .andExpect(jsonPath("$.[*].uid").value(hasItem(DEFAULT_UID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].strength").value(hasItem(DEFAULT_STRENGTH)))
            .andExpect(jsonPath("$.[*].strengthProficiency").value(hasItem(DEFAULT_STRENGTH_PROFICIENCY.booleanValue())))
            .andExpect(jsonPath("$.[*].dexterity").value(hasItem(DEFAULT_DEXTERITY)))
            .andExpect(jsonPath("$.[*].dexterityProficiency").value(hasItem(DEFAULT_DEXTERITY_PROFICIENCY.booleanValue())))
            .andExpect(jsonPath("$.[*].constitution").value(hasItem(DEFAULT_CONSTITUTION)))
            .andExpect(jsonPath("$.[*].constitutionProficiency").value(hasItem(DEFAULT_CONSTITUTION_PROFICIENCY.booleanValue())))
            .andExpect(jsonPath("$.[*].intelligence").value(hasItem(DEFAULT_INTELLIGENCE)))
            .andExpect(jsonPath("$.[*].intelligenceProficiency").value(hasItem(DEFAULT_INTELLIGENCE_PROFICIENCY.booleanValue())))
            .andExpect(jsonPath("$.[*].charisma").value(hasItem(DEFAULT_CHARISMA)))
            .andExpect(jsonPath("$.[*].charismaProficiency").value(hasItem(DEFAULT_CHARISMA_PROFICIENCY.booleanValue())))
            .andExpect(jsonPath("$.[*].wisdom").value(hasItem(DEFAULT_WISDOM)))
            .andExpect(jsonPath("$.[*].wisdomProficiency").value(hasItem(DEFAULT_WISDOM_PROFICIENCY.booleanValue())))
            .andExpect(jsonPath("$.[*].passiveInsight").value(hasItem(DEFAULT_PASSIVE_INSIGHT)))
            .andExpect(jsonPath("$.[*].passivePerception").value(hasItem(DEFAULT_PASSIVE_PERCEPTION)))
            .andExpect(jsonPath("$.[*].acrobatics").value(hasItem(DEFAULT_ACROBATICS.booleanValue())))
            .andExpect(jsonPath("$.[*].animalHandling").value(hasItem(DEFAULT_ANIMAL_HANDLING.booleanValue())))
            .andExpect(jsonPath("$.[*].arcana").value(hasItem(DEFAULT_ARCANA.booleanValue())))
            .andExpect(jsonPath("$.[*].athletics").value(hasItem(DEFAULT_ATHLETICS.booleanValue())))
            .andExpect(jsonPath("$.[*].deception").value(hasItem(DEFAULT_DECEPTION.booleanValue())))
            .andExpect(jsonPath("$.[*].history").value(hasItem(DEFAULT_HISTORY.booleanValue())))
            .andExpect(jsonPath("$.[*].insight").value(hasItem(DEFAULT_INSIGHT.booleanValue())))
            .andExpect(jsonPath("$.[*].intimidation").value(hasItem(DEFAULT_INTIMIDATION.booleanValue())))
            .andExpect(jsonPath("$.[*].investigation").value(hasItem(DEFAULT_INVESTIGATION.booleanValue())))
            .andExpect(jsonPath("$.[*].medicine").value(hasItem(DEFAULT_MEDICINE.booleanValue())))
            .andExpect(jsonPath("$.[*].nature").value(hasItem(DEFAULT_NATURE.booleanValue())))
            .andExpect(jsonPath("$.[*].perception").value(hasItem(DEFAULT_PERCEPTION.booleanValue())))
            .andExpect(jsonPath("$.[*].performance").value(hasItem(DEFAULT_PERFORMANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].persuasion").value(hasItem(DEFAULT_PERSUASION.booleanValue())))
            .andExpect(jsonPath("$.[*].religion").value(hasItem(DEFAULT_RELIGION.booleanValue())))
            .andExpect(jsonPath("$.[*].sleightOfHand").value(hasItem(DEFAULT_SLEIGHT_OF_HAND.booleanValue())))
            .andExpect(jsonPath("$.[*].stealth").value(hasItem(DEFAULT_STEALTH.booleanValue())))
            .andExpect(jsonPath("$.[*].survival").value(hasItem(DEFAULT_SURVIVAL.booleanValue())));
    }

    @Test
    @Transactional
    void getCharacter() throws Exception {
        // Initialize the database
        characterRepository.saveAndFlush(character);

        // Get the character
        restCharacterMockMvc
            .perform(get(ENTITY_API_URL_ID, character.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(character.getId().intValue()))
            .andExpect(jsonPath("$.uid").value(DEFAULT_UID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.strength").value(DEFAULT_STRENGTH))
            .andExpect(jsonPath("$.strengthProficiency").value(DEFAULT_STRENGTH_PROFICIENCY.booleanValue()))
            .andExpect(jsonPath("$.dexterity").value(DEFAULT_DEXTERITY))
            .andExpect(jsonPath("$.dexterityProficiency").value(DEFAULT_DEXTERITY_PROFICIENCY.booleanValue()))
            .andExpect(jsonPath("$.constitution").value(DEFAULT_CONSTITUTION))
            .andExpect(jsonPath("$.constitutionProficiency").value(DEFAULT_CONSTITUTION_PROFICIENCY.booleanValue()))
            .andExpect(jsonPath("$.intelligence").value(DEFAULT_INTELLIGENCE))
            .andExpect(jsonPath("$.intelligenceProficiency").value(DEFAULT_INTELLIGENCE_PROFICIENCY.booleanValue()))
            .andExpect(jsonPath("$.charisma").value(DEFAULT_CHARISMA))
            .andExpect(jsonPath("$.charismaProficiency").value(DEFAULT_CHARISMA_PROFICIENCY.booleanValue()))
            .andExpect(jsonPath("$.wisdom").value(DEFAULT_WISDOM))
            .andExpect(jsonPath("$.wisdomProficiency").value(DEFAULT_WISDOM_PROFICIENCY.booleanValue()))
            .andExpect(jsonPath("$.passiveInsight").value(DEFAULT_PASSIVE_INSIGHT))
            .andExpect(jsonPath("$.passivePerception").value(DEFAULT_PASSIVE_PERCEPTION))
            .andExpect(jsonPath("$.acrobatics").value(DEFAULT_ACROBATICS.booleanValue()))
            .andExpect(jsonPath("$.animalHandling").value(DEFAULT_ANIMAL_HANDLING.booleanValue()))
            .andExpect(jsonPath("$.arcana").value(DEFAULT_ARCANA.booleanValue()))
            .andExpect(jsonPath("$.athletics").value(DEFAULT_ATHLETICS.booleanValue()))
            .andExpect(jsonPath("$.deception").value(DEFAULT_DECEPTION.booleanValue()))
            .andExpect(jsonPath("$.history").value(DEFAULT_HISTORY.booleanValue()))
            .andExpect(jsonPath("$.insight").value(DEFAULT_INSIGHT.booleanValue()))
            .andExpect(jsonPath("$.intimidation").value(DEFAULT_INTIMIDATION.booleanValue()))
            .andExpect(jsonPath("$.investigation").value(DEFAULT_INVESTIGATION.booleanValue()))
            .andExpect(jsonPath("$.medicine").value(DEFAULT_MEDICINE.booleanValue()))
            .andExpect(jsonPath("$.nature").value(DEFAULT_NATURE.booleanValue()))
            .andExpect(jsonPath("$.perception").value(DEFAULT_PERCEPTION.booleanValue()))
            .andExpect(jsonPath("$.performance").value(DEFAULT_PERFORMANCE.booleanValue()))
            .andExpect(jsonPath("$.persuasion").value(DEFAULT_PERSUASION.booleanValue()))
            .andExpect(jsonPath("$.religion").value(DEFAULT_RELIGION.booleanValue()))
            .andExpect(jsonPath("$.sleightOfHand").value(DEFAULT_SLEIGHT_OF_HAND.booleanValue()))
            .andExpect(jsonPath("$.stealth").value(DEFAULT_STEALTH.booleanValue()))
            .andExpect(jsonPath("$.survival").value(DEFAULT_SURVIVAL.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingCharacter() throws Exception {
        // Get the character
        restCharacterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCharacter() throws Exception {
        // Initialize the database
        characterRepository.saveAndFlush(character);

        int databaseSizeBeforeUpdate = characterRepository.findAll().size();

        // Update the character
        Character updatedCharacter = characterRepository.findById(character.getId()).get();
        // Disconnect from session so that the updates on updatedCharacter are not directly saved in db
        em.detach(updatedCharacter);
        updatedCharacter
            .uid(UPDATED_UID)
            .name(UPDATED_NAME)
            .level(UPDATED_LEVEL)
            .strength(UPDATED_STRENGTH)
            .strengthProficiency(UPDATED_STRENGTH_PROFICIENCY)
            .dexterity(UPDATED_DEXTERITY)
            .dexterityProficiency(UPDATED_DEXTERITY_PROFICIENCY)
            .constitution(UPDATED_CONSTITUTION)
            .constitutionProficiency(UPDATED_CONSTITUTION_PROFICIENCY)
            .intelligence(UPDATED_INTELLIGENCE)
            .intelligenceProficiency(UPDATED_INTELLIGENCE_PROFICIENCY)
            .charisma(UPDATED_CHARISMA)
            .charismaProficiency(UPDATED_CHARISMA_PROFICIENCY)
            .wisdom(UPDATED_WISDOM)
            .wisdomProficiency(UPDATED_WISDOM_PROFICIENCY)
            .passiveInsight(UPDATED_PASSIVE_INSIGHT)
            .passivePerception(UPDATED_PASSIVE_PERCEPTION)
            .acrobatics(UPDATED_ACROBATICS)
            .animalHandling(UPDATED_ANIMAL_HANDLING)
            .arcana(UPDATED_ARCANA)
            .athletics(UPDATED_ATHLETICS)
            .deception(UPDATED_DECEPTION)
            .history(UPDATED_HISTORY)
            .insight(UPDATED_INSIGHT)
            .intimidation(UPDATED_INTIMIDATION)
            .investigation(UPDATED_INVESTIGATION)
            .medicine(UPDATED_MEDICINE)
            .nature(UPDATED_NATURE)
            .perception(UPDATED_PERCEPTION)
            .performance(UPDATED_PERFORMANCE)
            .persuasion(UPDATED_PERSUASION)
            .religion(UPDATED_RELIGION)
            .sleightOfHand(UPDATED_SLEIGHT_OF_HAND)
            .stealth(UPDATED_STEALTH)
            .survival(UPDATED_SURVIVAL);

        restCharacterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCharacter.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCharacter))
            )
            .andExpect(status().isOk());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeUpdate);
        Character testCharacter = characterList.get(characterList.size() - 1);
        assertThat(testCharacter.getUid()).isEqualTo(UPDATED_UID);
        assertThat(testCharacter.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCharacter.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testCharacter.getStrength()).isEqualTo(UPDATED_STRENGTH);
        assertThat(testCharacter.getStrengthProficiency()).isEqualTo(UPDATED_STRENGTH_PROFICIENCY);
        assertThat(testCharacter.getDexterity()).isEqualTo(UPDATED_DEXTERITY);
        assertThat(testCharacter.getDexterityProficiency()).isEqualTo(UPDATED_DEXTERITY_PROFICIENCY);
        assertThat(testCharacter.getConstitution()).isEqualTo(UPDATED_CONSTITUTION);
        assertThat(testCharacter.getConstitutionProficiency()).isEqualTo(UPDATED_CONSTITUTION_PROFICIENCY);
        assertThat(testCharacter.getIntelligence()).isEqualTo(UPDATED_INTELLIGENCE);
        assertThat(testCharacter.getIntelligenceProficiency()).isEqualTo(UPDATED_INTELLIGENCE_PROFICIENCY);
        assertThat(testCharacter.getCharisma()).isEqualTo(UPDATED_CHARISMA);
        assertThat(testCharacter.getCharismaProficiency()).isEqualTo(UPDATED_CHARISMA_PROFICIENCY);
        assertThat(testCharacter.getWisdom()).isEqualTo(UPDATED_WISDOM);
        assertThat(testCharacter.getWisdomProficiency()).isEqualTo(UPDATED_WISDOM_PROFICIENCY);
        assertThat(testCharacter.getPassiveInsight()).isEqualTo(UPDATED_PASSIVE_INSIGHT);
        assertThat(testCharacter.getPassivePerception()).isEqualTo(UPDATED_PASSIVE_PERCEPTION);
        assertThat(testCharacter.getAcrobatics()).isEqualTo(UPDATED_ACROBATICS);
        assertThat(testCharacter.getAnimalHandling()).isEqualTo(UPDATED_ANIMAL_HANDLING);
        assertThat(testCharacter.getArcana()).isEqualTo(UPDATED_ARCANA);
        assertThat(testCharacter.getAthletics()).isEqualTo(UPDATED_ATHLETICS);
        assertThat(testCharacter.getDeception()).isEqualTo(UPDATED_DECEPTION);
        assertThat(testCharacter.getHistory()).isEqualTo(UPDATED_HISTORY);
        assertThat(testCharacter.getInsight()).isEqualTo(UPDATED_INSIGHT);
        assertThat(testCharacter.getIntimidation()).isEqualTo(UPDATED_INTIMIDATION);
        assertThat(testCharacter.getInvestigation()).isEqualTo(UPDATED_INVESTIGATION);
        assertThat(testCharacter.getMedicine()).isEqualTo(UPDATED_MEDICINE);
        assertThat(testCharacter.getNature()).isEqualTo(UPDATED_NATURE);
        assertThat(testCharacter.getPerception()).isEqualTo(UPDATED_PERCEPTION);
        assertThat(testCharacter.getPerformance()).isEqualTo(UPDATED_PERFORMANCE);
        assertThat(testCharacter.getPersuasion()).isEqualTo(UPDATED_PERSUASION);
        assertThat(testCharacter.getReligion()).isEqualTo(UPDATED_RELIGION);
        assertThat(testCharacter.getSleightOfHand()).isEqualTo(UPDATED_SLEIGHT_OF_HAND);
        assertThat(testCharacter.getStealth()).isEqualTo(UPDATED_STEALTH);
        assertThat(testCharacter.getSurvival()).isEqualTo(UPDATED_SURVIVAL);
    }

    @Test
    @Transactional
    void putNonExistingCharacter() throws Exception {
        int databaseSizeBeforeUpdate = characterRepository.findAll().size();
        character.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCharacterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, character.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(character))
            )
            .andExpect(status().isBadRequest());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCharacter() throws Exception {
        int databaseSizeBeforeUpdate = characterRepository.findAll().size();
        character.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCharacterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(character))
            )
            .andExpect(status().isBadRequest());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCharacter() throws Exception {
        int databaseSizeBeforeUpdate = characterRepository.findAll().size();
        character.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCharacterMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(character)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCharacterWithPatch() throws Exception {
        // Initialize the database
        characterRepository.saveAndFlush(character);

        int databaseSizeBeforeUpdate = characterRepository.findAll().size();

        // Update the character using partial update
        Character partialUpdatedCharacter = new Character();
        partialUpdatedCharacter.setId(character.getId());

        partialUpdatedCharacter
            .dexterity(UPDATED_DEXTERITY)
            .dexterityProficiency(UPDATED_DEXTERITY_PROFICIENCY)
            .intelligence(UPDATED_INTELLIGENCE)
            .intelligenceProficiency(UPDATED_INTELLIGENCE_PROFICIENCY)
            .charismaProficiency(UPDATED_CHARISMA_PROFICIENCY)
            .wisdomProficiency(UPDATED_WISDOM_PROFICIENCY)
            .acrobatics(UPDATED_ACROBATICS)
            .animalHandling(UPDATED_ANIMAL_HANDLING)
            .arcana(UPDATED_ARCANA)
            .athletics(UPDATED_ATHLETICS)
            .deception(UPDATED_DECEPTION)
            .history(UPDATED_HISTORY)
            .insight(UPDATED_INSIGHT)
            .persuasion(UPDATED_PERSUASION)
            .sleightOfHand(UPDATED_SLEIGHT_OF_HAND)
            .stealth(UPDATED_STEALTH);

        restCharacterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCharacter.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCharacter))
            )
            .andExpect(status().isOk());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeUpdate);
        Character testCharacter = characterList.get(characterList.size() - 1);
        assertThat(testCharacter.getUid()).isEqualTo(DEFAULT_UID);
        assertThat(testCharacter.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCharacter.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testCharacter.getStrength()).isEqualTo(DEFAULT_STRENGTH);
        assertThat(testCharacter.getStrengthProficiency()).isEqualTo(DEFAULT_STRENGTH_PROFICIENCY);
        assertThat(testCharacter.getDexterity()).isEqualTo(UPDATED_DEXTERITY);
        assertThat(testCharacter.getDexterityProficiency()).isEqualTo(UPDATED_DEXTERITY_PROFICIENCY);
        assertThat(testCharacter.getConstitution()).isEqualTo(DEFAULT_CONSTITUTION);
        assertThat(testCharacter.getConstitutionProficiency()).isEqualTo(DEFAULT_CONSTITUTION_PROFICIENCY);
        assertThat(testCharacter.getIntelligence()).isEqualTo(UPDATED_INTELLIGENCE);
        assertThat(testCharacter.getIntelligenceProficiency()).isEqualTo(UPDATED_INTELLIGENCE_PROFICIENCY);
        assertThat(testCharacter.getCharisma()).isEqualTo(DEFAULT_CHARISMA);
        assertThat(testCharacter.getCharismaProficiency()).isEqualTo(UPDATED_CHARISMA_PROFICIENCY);
        assertThat(testCharacter.getWisdom()).isEqualTo(DEFAULT_WISDOM);
        assertThat(testCharacter.getWisdomProficiency()).isEqualTo(UPDATED_WISDOM_PROFICIENCY);
        assertThat(testCharacter.getPassiveInsight()).isEqualTo(DEFAULT_PASSIVE_INSIGHT);
        assertThat(testCharacter.getPassivePerception()).isEqualTo(DEFAULT_PASSIVE_PERCEPTION);
        assertThat(testCharacter.getAcrobatics()).isEqualTo(UPDATED_ACROBATICS);
        assertThat(testCharacter.getAnimalHandling()).isEqualTo(UPDATED_ANIMAL_HANDLING);
        assertThat(testCharacter.getArcana()).isEqualTo(UPDATED_ARCANA);
        assertThat(testCharacter.getAthletics()).isEqualTo(UPDATED_ATHLETICS);
        assertThat(testCharacter.getDeception()).isEqualTo(UPDATED_DECEPTION);
        assertThat(testCharacter.getHistory()).isEqualTo(UPDATED_HISTORY);
        assertThat(testCharacter.getInsight()).isEqualTo(UPDATED_INSIGHT);
        assertThat(testCharacter.getIntimidation()).isEqualTo(DEFAULT_INTIMIDATION);
        assertThat(testCharacter.getInvestigation()).isEqualTo(DEFAULT_INVESTIGATION);
        assertThat(testCharacter.getMedicine()).isEqualTo(DEFAULT_MEDICINE);
        assertThat(testCharacter.getNature()).isEqualTo(DEFAULT_NATURE);
        assertThat(testCharacter.getPerception()).isEqualTo(DEFAULT_PERCEPTION);
        assertThat(testCharacter.getPerformance()).isEqualTo(DEFAULT_PERFORMANCE);
        assertThat(testCharacter.getPersuasion()).isEqualTo(UPDATED_PERSUASION);
        assertThat(testCharacter.getReligion()).isEqualTo(DEFAULT_RELIGION);
        assertThat(testCharacter.getSleightOfHand()).isEqualTo(UPDATED_SLEIGHT_OF_HAND);
        assertThat(testCharacter.getStealth()).isEqualTo(UPDATED_STEALTH);
        assertThat(testCharacter.getSurvival()).isEqualTo(DEFAULT_SURVIVAL);
    }

    @Test
    @Transactional
    void fullUpdateCharacterWithPatch() throws Exception {
        // Initialize the database
        characterRepository.saveAndFlush(character);

        int databaseSizeBeforeUpdate = characterRepository.findAll().size();

        // Update the character using partial update
        Character partialUpdatedCharacter = new Character();
        partialUpdatedCharacter.setId(character.getId());

        partialUpdatedCharacter
            .uid(UPDATED_UID)
            .name(UPDATED_NAME)
            .level(UPDATED_LEVEL)
            .strength(UPDATED_STRENGTH)
            .strengthProficiency(UPDATED_STRENGTH_PROFICIENCY)
            .dexterity(UPDATED_DEXTERITY)
            .dexterityProficiency(UPDATED_DEXTERITY_PROFICIENCY)
            .constitution(UPDATED_CONSTITUTION)
            .constitutionProficiency(UPDATED_CONSTITUTION_PROFICIENCY)
            .intelligence(UPDATED_INTELLIGENCE)
            .intelligenceProficiency(UPDATED_INTELLIGENCE_PROFICIENCY)
            .charisma(UPDATED_CHARISMA)
            .charismaProficiency(UPDATED_CHARISMA_PROFICIENCY)
            .wisdom(UPDATED_WISDOM)
            .wisdomProficiency(UPDATED_WISDOM_PROFICIENCY)
            .passiveInsight(UPDATED_PASSIVE_INSIGHT)
            .passivePerception(UPDATED_PASSIVE_PERCEPTION)
            .acrobatics(UPDATED_ACROBATICS)
            .animalHandling(UPDATED_ANIMAL_HANDLING)
            .arcana(UPDATED_ARCANA)
            .athletics(UPDATED_ATHLETICS)
            .deception(UPDATED_DECEPTION)
            .history(UPDATED_HISTORY)
            .insight(UPDATED_INSIGHT)
            .intimidation(UPDATED_INTIMIDATION)
            .investigation(UPDATED_INVESTIGATION)
            .medicine(UPDATED_MEDICINE)
            .nature(UPDATED_NATURE)
            .perception(UPDATED_PERCEPTION)
            .performance(UPDATED_PERFORMANCE)
            .persuasion(UPDATED_PERSUASION)
            .religion(UPDATED_RELIGION)
            .sleightOfHand(UPDATED_SLEIGHT_OF_HAND)
            .stealth(UPDATED_STEALTH)
            .survival(UPDATED_SURVIVAL);

        restCharacterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCharacter.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCharacter))
            )
            .andExpect(status().isOk());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeUpdate);
        Character testCharacter = characterList.get(characterList.size() - 1);
        assertThat(testCharacter.getUid()).isEqualTo(UPDATED_UID);
        assertThat(testCharacter.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCharacter.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testCharacter.getStrength()).isEqualTo(UPDATED_STRENGTH);
        assertThat(testCharacter.getStrengthProficiency()).isEqualTo(UPDATED_STRENGTH_PROFICIENCY);
        assertThat(testCharacter.getDexterity()).isEqualTo(UPDATED_DEXTERITY);
        assertThat(testCharacter.getDexterityProficiency()).isEqualTo(UPDATED_DEXTERITY_PROFICIENCY);
        assertThat(testCharacter.getConstitution()).isEqualTo(UPDATED_CONSTITUTION);
        assertThat(testCharacter.getConstitutionProficiency()).isEqualTo(UPDATED_CONSTITUTION_PROFICIENCY);
        assertThat(testCharacter.getIntelligence()).isEqualTo(UPDATED_INTELLIGENCE);
        assertThat(testCharacter.getIntelligenceProficiency()).isEqualTo(UPDATED_INTELLIGENCE_PROFICIENCY);
        assertThat(testCharacter.getCharisma()).isEqualTo(UPDATED_CHARISMA);
        assertThat(testCharacter.getCharismaProficiency()).isEqualTo(UPDATED_CHARISMA_PROFICIENCY);
        assertThat(testCharacter.getWisdom()).isEqualTo(UPDATED_WISDOM);
        assertThat(testCharacter.getWisdomProficiency()).isEqualTo(UPDATED_WISDOM_PROFICIENCY);
        assertThat(testCharacter.getPassiveInsight()).isEqualTo(UPDATED_PASSIVE_INSIGHT);
        assertThat(testCharacter.getPassivePerception()).isEqualTo(UPDATED_PASSIVE_PERCEPTION);
        assertThat(testCharacter.getAcrobatics()).isEqualTo(UPDATED_ACROBATICS);
        assertThat(testCharacter.getAnimalHandling()).isEqualTo(UPDATED_ANIMAL_HANDLING);
        assertThat(testCharacter.getArcana()).isEqualTo(UPDATED_ARCANA);
        assertThat(testCharacter.getAthletics()).isEqualTo(UPDATED_ATHLETICS);
        assertThat(testCharacter.getDeception()).isEqualTo(UPDATED_DECEPTION);
        assertThat(testCharacter.getHistory()).isEqualTo(UPDATED_HISTORY);
        assertThat(testCharacter.getInsight()).isEqualTo(UPDATED_INSIGHT);
        assertThat(testCharacter.getIntimidation()).isEqualTo(UPDATED_INTIMIDATION);
        assertThat(testCharacter.getInvestigation()).isEqualTo(UPDATED_INVESTIGATION);
        assertThat(testCharacter.getMedicine()).isEqualTo(UPDATED_MEDICINE);
        assertThat(testCharacter.getNature()).isEqualTo(UPDATED_NATURE);
        assertThat(testCharacter.getPerception()).isEqualTo(UPDATED_PERCEPTION);
        assertThat(testCharacter.getPerformance()).isEqualTo(UPDATED_PERFORMANCE);
        assertThat(testCharacter.getPersuasion()).isEqualTo(UPDATED_PERSUASION);
        assertThat(testCharacter.getReligion()).isEqualTo(UPDATED_RELIGION);
        assertThat(testCharacter.getSleightOfHand()).isEqualTo(UPDATED_SLEIGHT_OF_HAND);
        assertThat(testCharacter.getStealth()).isEqualTo(UPDATED_STEALTH);
        assertThat(testCharacter.getSurvival()).isEqualTo(UPDATED_SURVIVAL);
    }

    @Test
    @Transactional
    void patchNonExistingCharacter() throws Exception {
        int databaseSizeBeforeUpdate = characterRepository.findAll().size();
        character.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCharacterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, character.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(character))
            )
            .andExpect(status().isBadRequest());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCharacter() throws Exception {
        int databaseSizeBeforeUpdate = characterRepository.findAll().size();
        character.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCharacterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(character))
            )
            .andExpect(status().isBadRequest());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCharacter() throws Exception {
        int databaseSizeBeforeUpdate = characterRepository.findAll().size();
        character.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCharacterMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(character))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Character in the database
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCharacter() throws Exception {
        // Initialize the database
        characterRepository.saveAndFlush(character);

        int databaseSizeBeforeDelete = characterRepository.findAll().size();

        // Delete the character
        restCharacterMockMvc
            .perform(delete(ENTITY_API_URL_ID, character.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Character> characterList = characterRepository.findAll();
        assertThat(characterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
