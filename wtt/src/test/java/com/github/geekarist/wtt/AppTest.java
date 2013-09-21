package com.github.geekarist.wtt;

import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.fest.assertions.Assertions.assertThat;

public class AppTest {
    @Test
    public void shouldRenameTorrentFileIfOptionGiven() {
        // GIVEN
        new File("oblivion-2013.torrent").delete();
        // WHEN
        App.main((String[]) Arrays.asList("-r", "http://www.allocine.fr/film/fichefilm_gen_cfilm=27405.html").toArray());
        // THEN
        assertThat(new File("oblivion-2013.torrent")).exists();
    }
}
