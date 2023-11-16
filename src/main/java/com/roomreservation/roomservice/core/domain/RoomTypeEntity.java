package com.roomreservation.roomservice.core.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "ROOM_TYPE")
public class RoomTypeEntity extends PanacheEntity{
    //    There are no Repository, since this entity is using the active record pattern
    //    Be aware, that Quarkus encourage to use public fields in entity classes
    //    ref. https://quarkus.io/guides/hibernate-orm-panache

    @Column(nullable = false)
    public Double basePrice;

    @Column(nullable = false)
    public Integer capacity;

    @Column(nullable = false, unique = true)
    public String name;

    public String description;

    @OneToMany(mappedBy = "room")
    public List<PhotoLinkEntity> photoLinks;

    @OneToMany(mappedBy = "type")
    public List<RoomEntity> rooms;

    public static Optional<RoomTypeEntity> findByNameOptional(String name) {
        return find("name", name).firstResultOptional();
    }

    public RoomTypeEntity() {
    }

    public RoomTypeEntity(Double basePrice, Integer capacity, String name, String description, List<PhotoLinkEntity> photoLinks, List<RoomEntity> rooms) {
        this.basePrice = basePrice;
        this.capacity = capacity;
        this.name = name;
        this.description = description;
        this.photoLinks = photoLinks;
        this.rooms = rooms;
    }
}
