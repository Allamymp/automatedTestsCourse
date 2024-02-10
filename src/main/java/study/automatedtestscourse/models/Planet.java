package study.automatedtestscourse.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name="TB_PLANET", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID planetId;
    private String name;
    private String climate;
    private String terrain;
}
