package study.automatedtestscourse.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

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
}
