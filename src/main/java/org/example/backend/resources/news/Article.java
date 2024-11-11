package org.example.backend.resources.news;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article {

    private String author;
    private String title;
    private String description;
    private String url;
    private String publishedAt;
    private Source source;
}
