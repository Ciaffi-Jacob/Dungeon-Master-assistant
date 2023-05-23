package rocks.zipcode.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Profile.
 */
@Entity
@Table(name = "profile")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "uid")
    private Long uid;

    @OneToMany(mappedBy = "profile")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "game", "profile" }, allowSetters = true)
    private Set<Log> logs = new HashSet<>();

    @OneToMany(mappedBy = "profile")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "game", "profile" }, allowSetters = true)
    private Set<Character> characters = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Profile id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public Profile userName(String userName) {
        this.setUserName(userName);
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public Profile password(String password) {
        this.setPassword(password);
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUid() {
        return this.uid;
    }

    public Profile uid(Long uid) {
        this.setUid(uid);
        return this;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Set<Log> getLogs() {
        return this.logs;
    }

    public void setLogs(Set<Log> logs) {
        if (this.logs != null) {
            this.logs.forEach(i -> i.setProfile(null));
        }
        if (logs != null) {
            logs.forEach(i -> i.setProfile(this));
        }
        this.logs = logs;
    }

    public Profile logs(Set<Log> logs) {
        this.setLogs(logs);
        return this;
    }

    public Profile addLog(Log log) {
        this.logs.add(log);
        log.setProfile(this);
        return this;
    }

    public Profile removeLog(Log log) {
        this.logs.remove(log);
        log.setProfile(null);
        return this;
    }

    public Set<Character> getCharacters() {
        return this.characters;
    }

    public void setCharacters(Set<Character> characters) {
        if (this.characters != null) {
            this.characters.forEach(i -> i.setProfile(null));
        }
        if (characters != null) {
            characters.forEach(i -> i.setProfile(this));
        }
        this.characters = characters;
    }

    public Profile characters(Set<Character> characters) {
        this.setCharacters(characters);
        return this;
    }

    public Profile addCharacter(Character character) {
        this.characters.add(character);
        character.setProfile(this);
        return this;
    }

    public Profile removeCharacter(Character character) {
        this.characters.remove(character);
        character.setProfile(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Profile)) {
            return false;
        }
        return id != null && id.equals(((Profile) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Profile{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            ", password='" + getPassword() + "'" +
            ", uid=" + getUid() +
            "}";
    }
}
