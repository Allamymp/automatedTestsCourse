package study.automatedtestscourse.common;

import study.automatedtestscourse.models.Planet;

import java.util.ArrayList;
import java.util.List;

public class PlanetConstants {
    public static final Planet PLANET = new Planet("name","climate","terrain");
    public static final Planet INVALID_PLANET = new Planet("","","");
    public static final Planet EMPTY_PLANET = new Planet();

    public static final Planet DAGOBAH = new Planet("Dagobah","Wet","Swamp");
    public static final Planet TATOOINE = new Planet("Tatooine","Arid","Desert");
    public static final Planet ALDERAAN = new Planet("Tatooine","Arid","Desert");

    public static  final List<Planet> PLANETS = new ArrayList<>(){
        {
            add(DAGOBAH);
            add(TATOOINE);
            add(ALDERAAN);
        }
    };
    public static final List<Planet> NULL_PLANETS = new ArrayList<>();
}
