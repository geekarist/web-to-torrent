package com.github.geekarist.wtt;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class MovieTest {

    @Test
    public void shouldGetMovieFromAllocineFr() {
        // GIVEN
        String url = "http://www.allocine.fr/film/fichefilm_gen_cfilm=27405.html";
        // WHEN
        Movie m = Movie.fromUrl(url);
        // THEN
        Assertions.assertThat(m.getTitle()).isEqualTo("Oblivion");
        Assertions.assertThat(m.getOriginalTitle()).isEqualTo("Oblivion");
        Assertions.assertThat(m.getYear()).isEqualTo("2013");
    }

    @Test
    public void shouldGetOriginalTitleWhenAvailable() {
        // GIVEN
        String url = "http://www.allocine.fr/film/fichefilm_gen_cfilm=1628.html";
        // WHEN
        Movie m = Movie.fromUrl(url);
        // THEN
        Assertions.assertThat(m.getTitle()).isEqualTo("Le Parrain");
        Assertions.assertThat(m.getOriginalTitle()).isEqualTo("The Godfather");
        Assertions.assertThat(m.getYear()).isEqualTo("1972");
    }

}
