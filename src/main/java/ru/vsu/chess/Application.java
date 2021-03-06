package ru.vsu.chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.neo4j.Neo4jAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EntityScan(value = "ru.vsu.chess.model")
@EnableNeo4jRepositories(basePackages = "ru.vsu.chess.repository.neo4j")
@EnableJpaRepositories(basePackages = "ru.vsu.chess.repository.jpa")
public class Application extends Neo4jAutoConfiguration {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
