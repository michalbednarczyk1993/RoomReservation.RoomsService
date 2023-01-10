package com.roomreservation.reservationservice.core.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@ToString
@Getter
@Setter
@Entity
@Table(name = "room_type")
public class RoomTypeEntity {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @Column(nullable = false, unique = true, insertable = false)
    private Long id;

    @Column(nullable = false)
    private BigDecimal basePrice;

    @Column(nullable = false)
    private Integer capacity;

    @ToString.Exclude
    @OneToMany(mappedBy = "roomId")
    private List<PhotoLinkEntity> photoLinks;

    @ToString.Exclude
    @OneToMany(mappedBy = "typeId")
    private List<RoomEntity> rooms;

}
