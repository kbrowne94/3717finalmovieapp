package ca.kbrowne3717.movieapp;

import java.util.Date;

public class MovieModel {
//    private int id;
    private String title;
    private String description;
    private String imdb;

    public MovieModel(String title, String description, String imdb) {
//        this.id = id;
        this.title = title;
        this.description = description;
        this.imdb = imdb;
    }

    public MovieModel() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    @Override
    public String toString() {
        return "title:" + title+ "\n" +
                "description:" + description+ "\n"+
                "imdb:" + imdb +  "\n";
    }
}
