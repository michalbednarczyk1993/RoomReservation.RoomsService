package com.roomreservation.roomservice.core.domain;

import lombok.*;

import javax.persistence.*;
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
    private Double basePrice;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private String name;

    private String description;

    @ToString.Exclude
    @OneToMany(mappedBy = "room")
    private List<PhotoLinkEntity> photoLinks;

    @ToString.Exclude
    @OneToMany(mappedBy = "type")
    private List<RoomEntity> rooms;

    public PhotoLinkEntity getMainPhoto() {
        List<PhotoLinkEntity> links = getPhotoLinks();
        if (links.size() > 0) {
            return links.stream().filter(PhotoLinkEntity::isMain).findFirst().orElseGet(() -> links.get(0));
        } else {
            throw new EntityNotFoundException("There are no photos related to this room type!");
        }
    }
}
