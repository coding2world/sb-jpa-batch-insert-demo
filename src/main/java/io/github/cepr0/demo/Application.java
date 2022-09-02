package io.github.cepr0.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.UUID;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

@EnableJpaRepositories(considerNestedRepositories = true)
@SpringBootApplication
public class Application {

    private final CityRepo cityRepo;

    public Application(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener
    public void onReady(ApplicationReadyEvent e) {
        final long start = System.currentTimeMillis();
        cityRepo.saveAll(range(1, 10)
                .mapToObj(i -> new City("beautiful", (int)System.currentTimeMillis()/1000))
                .collect(toList()));
        System.out.println("delta = " + (System.currentTimeMillis() - start));
    }

    @Entity
    @NoArgsConstructor
    @Data
    static class Model {
        @Id
        @GeneratedValue
        private UUID id;
        @PrePersist
        private void prePersist() {
            id = UUID.randomUUID();
        }
    }

    public interface CityRepo extends JpaRepository<City, Long> {
    }


}
