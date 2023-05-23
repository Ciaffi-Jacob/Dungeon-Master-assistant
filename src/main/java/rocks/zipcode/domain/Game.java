package rocks.zipcode.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Game.
 */
@Entity
@Table(name = "game")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uid")
    private Long uid;

    @OneToMany(mappedBy = "game")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "game", "profile" }, allowSetters = true)
    private Set<Character> characters = new HashSet<>();

    @JsonIgnoreProperties(value = { "game", "profile" }, allowSetters = true)
    @OneToOne(mappedBy = "game")
    private Log log;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Game id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return this.uid;
    }

    public Game uid(Long uid) {
        this.setUid(uid);
        return this;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Set<Character> getCharacters() {
        return this.characters;
    }

    public void setCharacters(Set<Character> characters) {
        if (this.characters != null) {
            this.characters.forEach(i -> i.setGame(null));
        }
        if (characters != null) {
            characters.forEach(i -> i.setGame(this));
        }
        this.characters = characters;
    }

    public Game characters(Set<Character> characters) {
        this.setCharacters(characters);
        return this;
    }

    public Game addCharacter(Character character) {
        this.characters.add(character);
        character.setGame(this);
        return this;
    }

    public Game removeCharacter(Character character) {
        this.characters.remove(character);
        character.setGame(null);
        return this;
    }

    public Log getLog() {
        return this.log;
    }

    public void setLog(Log log) {
        if (this.log != null) {
            this.log.setGame(null);
        }
        if (log != null) {
            log.setGame(this);
        }
        this.log = log;
    }

    public Game log(Log log) {
        this.setLog(log);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Game)) {
            return false;
        }
        return id != null && id.equals(((Game) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Game{" +
            "id=" + getId() +
            ", uid=" + getUid() +
            "}";
    }
}
