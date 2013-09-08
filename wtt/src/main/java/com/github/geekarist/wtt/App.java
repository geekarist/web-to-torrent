package com.github.geekarist.wtt;

import java.io.File;

public class App {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Usage: wtt <url>");
			System.exit(1);
		}
		Movie movie = Movie.get(args[0]);
		System.out.println(movie.getTitle() + ":");
		System.out.println("- Original title: " + movie.getOriginalTitle());
		System.out.println("- Year of release: " + movie.getYear());
		File torrent = Torrent.find(movie.getOriginalTitle() + " " + movie.getYear());
		System.out.println(String.format("Torrent saved to [%s]", torrent.getPath()));
	}
}
