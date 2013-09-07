package com.github.geekarist.wtt;

import java.io.File;

public class App {
    public static void main(String[] args) {
        Movie movie = Movie.get("http://www.allocine.fr/film/fichefilm_gen_cfilm=26251.html");
        System.out.println(movie.getTitle() + ":");
        System.out.println("- Original title: " + movie.getOriginalTitle());
        System.out.println("- Year of release: " + movie.getYear());
        File torrent = Torrent.find(movie.getOriginalTitle() + " " + movie.getYear());
        System.out.println(String.format("Torrent saved to [%s]", torrent.getPath()));
    }
}
