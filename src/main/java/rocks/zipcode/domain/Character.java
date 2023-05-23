package rocks.zipcode.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Character.
 */
@Entity
@Table(name = "jhi_character")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Character implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uid")
    private Long uid;

    @Column(name = "name")
    private String name;

    @Column(name = "level")
    private Integer level;

    @Column(name = "strength")
    private Integer strength;

    @Column(name = "strength_proficiency")
    private Boolean strengthProficiency;

    @Column(name = "dexterity")
    private Integer dexterity;

    @Column(name = "dexterity_proficiency")
    private Boolean dexterityProficiency;

    @Column(name = "constitution")
    private Integer constitution;

    @Column(name = "constitution_proficiency")
    private Boolean constitutionProficiency;

    @Column(name = "intelligence")
    private Integer intelligence;

    @Column(name = "intelligence_proficiency")
    private Boolean intelligenceProficiency;

    @Column(name = "charisma")
    private Integer charisma;

    @Column(name = "charisma_proficiency")
    private Boolean charismaProficiency;

    @Column(name = "wisdom")
    private Integer wisdom;

    @Column(name = "wisdom_proficiency")
    private Boolean wisdomProficiency;

    @Column(name = "passive_insight")
    private Integer passiveInsight;

    @Column(name = "passive_perception")
    private Integer passivePerception;

    @Column(name = "acrobatics")
    private Boolean acrobatics;

    @Column(name = "animal_handling")
    private Boolean animalHandling;

    @Column(name = "arcana")
    private Boolean arcana;

    @Column(name = "athletics")
    private Boolean athletics;

    @Column(name = "deception")
    private Boolean deception;

    @Column(name = "history")
    private Boolean history;

    @Column(name = "insight")
    private Boolean insight;

    @Column(name = "intimidation")
    private Boolean intimidation;

    @Column(name = "investigation")
    private Boolean investigation;

    @Column(name = "medicine")
    private Boolean medicine;

    @Column(name = "nature")
    private Boolean nature;

    @Column(name = "perception")
    private Boolean perception;

    @Column(name = "performance")
    private Boolean performance;

    @Column(name = "persuasion")
    private Boolean persuasion;

    @Column(name = "religion")
    private Boolean religion;

    @Column(name = "sleight_of_hand")
    private Boolean sleightOfHand;

    @Column(name = "stealth")
    private Boolean stealth;

    @Column(name = "survival")
    private Boolean survival;

    @ManyToOne
    @JsonIgnoreProperties(value = { "characters", "log" }, allowSetters = true)
    private Game game;

    @ManyToOne
    @JsonIgnoreProperties(value = { "logs", "characters" }, allowSetters = true)
    private Profile profile;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Character id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return this.uid;
    }

    public Character uid(Long uid) {
        this.setUid(uid);
        return this;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return this.name;
    }

    public Character name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return this.level;
    }

    public Character level(Integer level) {
        this.setLevel(level);
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getStrength() {
        return this.strength;
    }

    public Character strength(Integer strength) {
        this.setStrength(strength);
        return this;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Boolean getStrengthProficiency() {
        return this.strengthProficiency;
    }

    public Character strengthProficiency(Boolean strengthProficiency) {
        this.setStrengthProficiency(strengthProficiency);
        return this;
    }

    public void setStrengthProficiency(Boolean strengthProficiency) {
        this.strengthProficiency = strengthProficiency;
    }

    public Integer getDexterity() {
        return this.dexterity;
    }

    public Character dexterity(Integer dexterity) {
        this.setDexterity(dexterity);
        return this;
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }

    public Boolean getDexterityProficiency() {
        return this.dexterityProficiency;
    }

    public Character dexterityProficiency(Boolean dexterityProficiency) {
        this.setDexterityProficiency(dexterityProficiency);
        return this;
    }

    public void setDexterityProficiency(Boolean dexterityProficiency) {
        this.dexterityProficiency = dexterityProficiency;
    }

    public Integer getConstitution() {
        return this.constitution;
    }

    public Character constitution(Integer constitution) {
        this.setConstitution(constitution);
        return this;
    }

    public void setConstitution(Integer constitution) {
        this.constitution = constitution;
    }

    public Boolean getConstitutionProficiency() {
        return this.constitutionProficiency;
    }

    public Character constitutionProficiency(Boolean constitutionProficiency) {
        this.setConstitutionProficiency(constitutionProficiency);
        return this;
    }

    public void setConstitutionProficiency(Boolean constitutionProficiency) {
        this.constitutionProficiency = constitutionProficiency;
    }

    public Integer getIntelligence() {
        return this.intelligence;
    }

    public Character intelligence(Integer intelligence) {
        this.setIntelligence(intelligence);
        return this;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Boolean getIntelligenceProficiency() {
        return this.intelligenceProficiency;
    }

    public Character intelligenceProficiency(Boolean intelligenceProficiency) {
        this.setIntelligenceProficiency(intelligenceProficiency);
        return this;
    }

    public void setIntelligenceProficiency(Boolean intelligenceProficiency) {
        this.intelligenceProficiency = intelligenceProficiency;
    }

    public Integer getCharisma() {
        return this.charisma;
    }

    public Character charisma(Integer charisma) {
        this.setCharisma(charisma);
        return this;
    }

    public void setCharisma(Integer charisma) {
        this.charisma = charisma;
    }

    public Boolean getCharismaProficiency() {
        return this.charismaProficiency;
    }

    public Character charismaProficiency(Boolean charismaProficiency) {
        this.setCharismaProficiency(charismaProficiency);
        return this;
    }

    public void setCharismaProficiency(Boolean charismaProficiency) {
        this.charismaProficiency = charismaProficiency;
    }

    public Integer getWisdom() {
        return this.wisdom;
    }

    public Character wisdom(Integer wisdom) {
        this.setWisdom(wisdom);
        return this;
    }

    public void setWisdom(Integer wisdom) {
        this.wisdom = wisdom;
    }

    public Boolean getWisdomProficiency() {
        return this.wisdomProficiency;
    }

    public Character wisdomProficiency(Boolean wisdomProficiency) {
        this.setWisdomProficiency(wisdomProficiency);
        return this;
    }

    public void setWisdomProficiency(Boolean wisdomProficiency) {
        this.wisdomProficiency = wisdomProficiency;
    }

    public Integer getPassiveInsight() {
        return this.passiveInsight;
    }

    public Character passiveInsight(Integer passiveInsight) {
        this.setPassiveInsight(passiveInsight);
        return this;
    }

    public void setPassiveInsight(Integer passiveInsight) {
        this.passiveInsight = passiveInsight;
    }

    public Integer getPassivePerception() {
        return this.passivePerception;
    }

    public Character passivePerception(Integer passivePerception) {
        this.setPassivePerception(passivePerception);
        return this;
    }

    public void setPassivePerception(Integer passivePerception) {
        this.passivePerception = passivePerception;
    }

    public Boolean getAcrobatics() {
        return this.acrobatics;
    }

    public Character acrobatics(Boolean acrobatics) {
        this.setAcrobatics(acrobatics);
        return this;
    }

    public void setAcrobatics(Boolean acrobatics) {
        this.acrobatics = acrobatics;
    }

    public Boolean getAnimalHandling() {
        return this.animalHandling;
    }

    public Character animalHandling(Boolean animalHandling) {
        this.setAnimalHandling(animalHandling);
        return this;
    }

    public void setAnimalHandling(Boolean animalHandling) {
        this.animalHandling = animalHandling;
    }

    public Boolean getArcana() {
        return this.arcana;
    }

    public Character arcana(Boolean arcana) {
        this.setArcana(arcana);
        return this;
    }

    public void setArcana(Boolean arcana) {
        this.arcana = arcana;
    }

    public Boolean getAthletics() {
        return this.athletics;
    }

    public Character athletics(Boolean athletics) {
        this.setAthletics(athletics);
        return this;
    }

    public void setAthletics(Boolean athletics) {
        this.athletics = athletics;
    }

    public Boolean getDeception() {
        return this.deception;
    }

    public Character deception(Boolean deception) {
        this.setDeception(deception);
        return this;
    }

    public void setDeception(Boolean deception) {
        this.deception = deception;
    }

    public Boolean getHistory() {
        return this.history;
    }

    public Character history(Boolean history) {
        this.setHistory(history);
        return this;
    }

    public void setHistory(Boolean history) {
        this.history = history;
    }

    public Boolean getInsight() {
        return this.insight;
    }

    public Character insight(Boolean insight) {
        this.setInsight(insight);
        return this;
    }

    public void setInsight(Boolean insight) {
        this.insight = insight;
    }

    public Boolean getIntimidation() {
        return this.intimidation;
    }

    public Character intimidation(Boolean intimidation) {
        this.setIntimidation(intimidation);
        return this;
    }

    public void setIntimidation(Boolean intimidation) {
        this.intimidation = intimidation;
    }

    public Boolean getInvestigation() {
        return this.investigation;
    }

    public Character investigation(Boolean investigation) {
        this.setInvestigation(investigation);
        return this;
    }

    public void setInvestigation(Boolean investigation) {
        this.investigation = investigation;
    }

    public Boolean getMedicine() {
        return this.medicine;
    }

    public Character medicine(Boolean medicine) {
        this.setMedicine(medicine);
        return this;
    }

    public void setMedicine(Boolean medicine) {
        this.medicine = medicine;
    }

    public Boolean getNature() {
        return this.nature;
    }

    public Character nature(Boolean nature) {
        this.setNature(nature);
        return this;
    }

    public void setNature(Boolean nature) {
        this.nature = nature;
    }

    public Boolean getPerception() {
        return this.perception;
    }

    public Character perception(Boolean perception) {
        this.setPerception(perception);
        return this;
    }

    public void setPerception(Boolean perception) {
        this.perception = perception;
    }

    public Boolean getPerformance() {
        return this.performance;
    }

    public Character performance(Boolean performance) {
        this.setPerformance(performance);
        return this;
    }

    public void setPerformance(Boolean performance) {
        this.performance = performance;
    }

    public Boolean getPersuasion() {
        return this.persuasion;
    }

    public Character persuasion(Boolean persuasion) {
        this.setPersuasion(persuasion);
        return this;
    }

    public void setPersuasion(Boolean persuasion) {
        this.persuasion = persuasion;
    }

    public Boolean getReligion() {
        return this.religion;
    }

    public Character religion(Boolean religion) {
        this.setReligion(religion);
        return this;
    }

    public void setReligion(Boolean religion) {
        this.religion = religion;
    }

    public Boolean getSleightOfHand() {
        return this.sleightOfHand;
    }

    public Character sleightOfHand(Boolean sleightOfHand) {
        this.setSleightOfHand(sleightOfHand);
        return this;
    }

    public void setSleightOfHand(Boolean sleightOfHand) {
        this.sleightOfHand = sleightOfHand;
    }

    public Boolean getStealth() {
        return this.stealth;
    }

    public Character stealth(Boolean stealth) {
        this.setStealth(stealth);
        return this;
    }

    public void setStealth(Boolean stealth) {
        this.stealth = stealth;
    }

    public Boolean getSurvival() {
        return this.survival;
    }

    public Character survival(Boolean survival) {
        this.setSurvival(survival);
        return this;
    }

    public void setSurvival(Boolean survival) {
        this.survival = survival;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Character game(Game game) {
        this.setGame(game);
        return this;
    }

    public Profile getProfile() {
        return this.profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Character profile(Profile profile) {
        this.setProfile(profile);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Character)) {
            return false;
        }
        return id != null && id.equals(((Character) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Character{" +
            "id=" + getId() +
            ", uid=" + getUid() +
            ", name='" + getName() + "'" +
            ", level=" + getLevel() +
            ", strength=" + getStrength() +
            ", strengthProficiency='" + getStrengthProficiency() + "'" +
            ", dexterity=" + getDexterity() +
            ", dexterityProficiency='" + getDexterityProficiency() + "'" +
            ", constitution=" + getConstitution() +
            ", constitutionProficiency='" + getConstitutionProficiency() + "'" +
            ", intelligence=" + getIntelligence() +
            ", intelligenceProficiency='" + getIntelligenceProficiency() + "'" +
            ", charisma=" + getCharisma() +
            ", charismaProficiency='" + getCharismaProficiency() + "'" +
            ", wisdom=" + getWisdom() +
            ", wisdomProficiency='" + getWisdomProficiency() + "'" +
            ", passiveInsight=" + getPassiveInsight() +
            ", passivePerception=" + getPassivePerception() +
            ", acrobatics='" + getAcrobatics() + "'" +
            ", animalHandling='" + getAnimalHandling() + "'" +
            ", arcana='" + getArcana() + "'" +
            ", athletics='" + getAthletics() + "'" +
            ", deception='" + getDeception() + "'" +
            ", history='" + getHistory() + "'" +
            ", insight='" + getInsight() + "'" +
            ", intimidation='" + getIntimidation() + "'" +
            ", investigation='" + getInvestigation() + "'" +
            ", medicine='" + getMedicine() + "'" +
            ", nature='" + getNature() + "'" +
            ", perception='" + getPerception() + "'" +
            ", performance='" + getPerformance() + "'" +
            ", persuasion='" + getPersuasion() + "'" +
            ", religion='" + getReligion() + "'" +
            ", sleightOfHand='" + getSleightOfHand() + "'" +
            ", stealth='" + getStealth() + "'" +
            ", survival='" + getSurvival() + "'" +
            "}";
    }
}
