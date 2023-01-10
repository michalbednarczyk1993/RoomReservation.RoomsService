package com.roomreservation.reservationservice.core.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
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
    @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
    private Long id;

    @Column(name = "link", nullable = false, unique = true)
    private String link;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomTypeEntity roomId;

}
