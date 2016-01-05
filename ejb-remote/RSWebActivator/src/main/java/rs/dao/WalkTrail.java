package rs.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "walktrail",
        uniqueConstraints = @UniqueConstraint(columnNames = {"description", "length"}))
public class WalkTrail implements Comparable<WalkTrail> {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "description", unique=true)
    private String description;

    @Column(name = "length", unique=false)
    private Integer length;

    public WalkTrail(Long id, String description, Integer length) {
        this.id = id;
        this.description=description;
        this.length=length;
    }

    public WalkTrail() {
    }

    @Override
    public int compareTo(WalkTrail s) {
        return length.compareTo(s.getLength());
    }

}
