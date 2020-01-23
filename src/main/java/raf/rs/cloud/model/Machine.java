package raf.rs.cloud.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Data
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "uid", nullable = false , unique = true)
    private String uid;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_fk",nullable = false)
    private User createdBy;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "active")
    @Type(type="yes_no")
    private Boolean active;

    public  Machine(){

    }

    public Machine(User user) {
        this.createdBy = user;
        this.uid = UUID.randomUUID().toString();
        this.state = State.STOPPED;
        this.active = true;
    }

    public long getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public State getState() {
        return state;
    }

    public Boolean getActive() {
        return active;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
