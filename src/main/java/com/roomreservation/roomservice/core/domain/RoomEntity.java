package com.roomreservation.roomservice.core.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "ROOM")
public class RoomEntity extends PanacheEntity {
    //    Be aware, that Quarkus encourage to use public fields in entity classes
    //    ref. https://quarkus.io/guides/hibernate-orm-panache

    @Column(nullable = false, unique = true)
    public Integer number;

    @Column(nullable = false)
    public String name;

    @ManyToOne
    @JoinColumn(name="type_id", nullable = false)
    public RoomTypeEntity type;


    public RoomEntity() {
    }

    public RoomEntity(Integer number, String name, RoomTypeEntity type) {
        this.number = number;
        this.name = name;
        this.type = type;
    }
}
