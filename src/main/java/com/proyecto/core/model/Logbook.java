package com.proyecto.core.model;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "log_book")
public class Logbook implements Serializable{

    //private static final long serialVersionUID = 436235141242353L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private Date eventDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String eventDescrip;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Logbook(){}

    public Logbook(Integer id, Date eventDate, User user, String eventDescrip) {
        this.id = id;
        this.eventDate = eventDate;
        this.user = user;
        this.eventDescrip = eventDescrip;
    }

    @Override
    public String toString() {
        return "Logbook{" +
                "id=" + id +
                ", eventDate=" + eventDate +
                ", user=" + user +
                ", eventDescrip='" + eventDescrip + '\'' +
                '}';
    }
}