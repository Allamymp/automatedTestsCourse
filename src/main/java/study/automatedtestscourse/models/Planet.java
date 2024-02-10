package study.automatedtestscourse.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Getter
@NoArgsConstructor
@Entity
@Table(name="TB_PLANET", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planetId;
    @Setter
    private String name;
    @Setter
    private String climate;
    @Setter
    private String terrain;

    public Planet(String name, String climate, String terrain) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
    }

    @Override
    public boolean equals(Object object){
        return EqualsBuilder.reflectionEquals(object,this);
    }
}
