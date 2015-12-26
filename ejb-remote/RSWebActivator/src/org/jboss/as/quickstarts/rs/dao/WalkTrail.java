package org.jboss.as.quickstarts.rs.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class WalkTrail {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private Integer length;

   // @OneToOne(fetch=FetchType.EAGER)
   // private Mountain mountain;
}
