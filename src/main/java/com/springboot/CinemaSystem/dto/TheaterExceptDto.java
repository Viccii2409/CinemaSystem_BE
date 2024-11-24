package com.springboot.CinemaSystem.dto;

public class TheaterExceptDto {

        private Long id;
        private String name;

        public TheaterExceptDto(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        // Getters và Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

