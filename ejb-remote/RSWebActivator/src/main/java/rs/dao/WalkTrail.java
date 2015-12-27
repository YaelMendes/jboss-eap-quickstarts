package rs.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class WalkTrail implements Comparable<WalkTrail> {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

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

    // @OneToOne(fetch=FetchType.EAGER)
   // private Mountain mountain;
}
