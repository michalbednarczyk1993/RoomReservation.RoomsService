package com.roomreservation.reservationservice.core.domain;

import lombok.*;
import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
@Table(name="room")
public class RoomEntity {

    @Id
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @GeneratedValue
    @Column(nullable = false, unique = true, insertable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String number;

    private String name;

    @ManyToOne
    @JoinColumn(name="type_id", nullable = false)
    private RoomTypeEntity type;
}
