package com.roomreservation.roomservice.core.domain;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
@Table(name = "photo_link")
public class PhotoLinkEntity {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @Column(nullable = false, unique = true, insertable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String link;

    private Boolean main = false;

    @ManyToOne
    @JoinColumn(name="room_id")
    private RoomTypeEntity room;

    public Boolean isMain() {
        return main;
    }
}
