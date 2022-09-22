package ru.job4j.io;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.driver_class")).isEqualTo("org.postgresql.Driver");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres");
    }

    @Test
    public void whenPairWithExc() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    String path = "./data/pair_with_comment.txt";
                    Config config = new Config(path);
                    config.value("hibernate.connection.password=");
                });
    }
}