package com.github.geekarist.wtt;

public class App {
    public static void main(String[] args) {
        Movie movie = Movie.get("http://www.allocine.fr/film/fichefilm_gen_cfilm=27405.html");
        System.out.println(movie.getTitle() + ":");
        System.out.println("- Original title: " + movie.getOriginalTitle());
        System.out.println("- Year of release: " + movie.getYear());
    }
}
