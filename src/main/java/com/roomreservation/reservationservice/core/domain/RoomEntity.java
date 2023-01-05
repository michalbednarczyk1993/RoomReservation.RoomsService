package com.roomreservation.reservationservice.core.domain;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name="room")
public class RoomEntity {
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
    private Long id;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @Column(name = "name")
    private String name;

    @Enumerated
    @Column(name = "type", nullable = false)
    private RoomType type;

    @Column(name = "base_price", nullable = false)
    private BigDecimal basePrice;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    //@Column(name = "linkToPhoto",

    enum RoomType {
        SINGLE_ROOM,
        DOUBLE_ROOM,
        APARTMENT
    }
}
