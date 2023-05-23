package rocks.zipcode.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Log.
 */
@Entity
@Table(name = "log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "entry")
    private String entry;

    @JsonIgnoreProperties(value = { "characters", "log" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Game game;

    @ManyToOne
    @JsonIgnoreProperties(value = { "logs", "characters" }, allowSetters = true)
    private Profile profile;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Log id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntry() {
        return this.entry;
    }

    public Log entry(String entry) {
        this.setEntry(entry);
        return this;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Log game(Game game) {
        this.setGame(game);
        return this;
    }

    public Profile getProfile() {
        return this.profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Log profile(Profile profile) {
        this.setProfile(profile);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Log)) {
            return false;
        }
        return id != null && id.equals(((Log) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Log{" +
            "id=" + getId() +
            ", entry='" + getEntry() + "'" +
            "}";
    }
}
