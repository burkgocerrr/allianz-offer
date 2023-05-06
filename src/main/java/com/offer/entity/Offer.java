package com.offer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "offer")
public class Offer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "advert_title",length = 50)
    private String advertTitle;

    @Column(name = "description",length = 200)
    private String description;
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private OfferCategory category;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StatusLog> statusLogs;
}
