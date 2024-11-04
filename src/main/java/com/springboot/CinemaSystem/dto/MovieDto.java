package com.springboot.CinemaSystem.dto;

public class MovieDto {
        private Long id;
        private String title;
        private String link;

        public MovieDto(long id, String title, String link) {
            this.id=id;
            this.title = title;
            this.link = link;

        }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    // Getters and Setters


}

