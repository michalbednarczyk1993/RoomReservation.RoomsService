package com.roomreservation.roomservice.core.domain;

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

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public RoomTypeEntity getType() {
        return type;
    }
}
