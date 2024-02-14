package study.automatedtestscourse.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="TB_PLANET")
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planetId;
    @NotBlank
    @Column(unique = true)
    private String name;
    @NotBlank
    private String climate;
    @NotBlank
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
