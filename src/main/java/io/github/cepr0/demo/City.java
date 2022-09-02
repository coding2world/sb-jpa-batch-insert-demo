package io.github.cepr0.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "city")
@AllArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_id_gen")
    @SequenceGenerator(name = "city_id_gen", sequenceName = "public.city_id_seq",
            allocationSize = 10)
    private Long id;
    private String name;
    private int population;

    public City(String name, int population) {
        this.name = name;
        this.population = population;
    }

    public City() { }

}