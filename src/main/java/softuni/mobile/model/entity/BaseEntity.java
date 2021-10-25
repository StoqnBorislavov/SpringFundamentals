package softuni.mobile.model.entity;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(nullable = false)
    private Instant created;

    private Instant modified;

    public BaseEntity() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    @PrePersist
    public void beforeCreate(){
        setCreated(Instant.now());
    }
//    @PostPersist
//    public void onUpdate(){
//        this.modified = Instant.now();
//    }
    @PreUpdate
    public void onUpdate(){
        setModified(Instant.now());
    }
}
