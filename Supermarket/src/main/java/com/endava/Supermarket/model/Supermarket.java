package com.endava.Supermarket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "supermarkets")
public class Supermarket {

    @Id
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "work_hours", nullable = false)
    private String workHours;

    @ManyToMany(targetEntity = Item.class   )
    @JoinTable(name = "supermarket_item",
            joinColumns = @JoinColumn(name = "supermarket_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id",
                    referencedColumnName = "id"))
    private List<Item> items;
}
