package com.roomreservation.roomservice.core.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "PHOTO_LINK")
public class PhotoLinkEntity extends PanacheEntity {
    //    There are no Repository, since this entity is using the active record pattern
    //    Be aware, that Quarkus encourage to use public fields in entity classes
    //    ref. https://quarkus.io/guides/hibernate-orm-panache
    @Column(nullable = false, unique = true)
    public String link;

    public Boolean main;

    @ManyToOne
    @JoinColumn(name="room_id")
    public RoomTypeEntity room;

    public PhotoLinkEntity() {
    }

    public PhotoLinkEntity(String link, Boolean main, RoomTypeEntity room) {
        this.link = link;
        this.main = main;
        this.room = room;
    }
}
