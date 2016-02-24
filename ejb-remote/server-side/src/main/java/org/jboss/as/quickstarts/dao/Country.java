package org.jboss.as.quickstarts.dao;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name="countries")
public class Country {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinTable(name="countries_join_mountains",
            joinColumns = @JoinColumn(name="country_fk"),
            inverseJoinColumns = @JoinColumn(name="country_mountain_fk")
    )
    @OrderBy("name desc")
    private List<Mountain> mountains;

}
