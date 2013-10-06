package com.github.geekarist.wtt;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class App {
	private static class Parameters {
		@Parameter(names = { "-r", "--rename" }, description = "Rename torrent file after file name")
		private boolean rename = false;

		@Parameter
		private List<String> arguments = new ArrayList<>();
	}

	public static void main(String[] args) {
		Parameters parameters = new Parameters();
		new JCommander(parameters, args);
		if (parameters.arguments.size() == 0) {
			System.out.println("Usage: wtt [-r|--rename] <url>");
			System.exit(1);
		}
		Movie movie = Movie.fromUrl(parameters.arguments.get(0));
		System.out.println(movie.getTitle() + ":");
		System.out.println("- Original title: " + movie.getOriginalTitle());
		System.out.println("- Year of release: " + movie.getYear());
		File torrent = Torrent.find(movie.getOriginalTitle() + " " + movie.getYear());
		System.out.println(String.format("Torrent saved to [%s]", torrent.getPath()));
		if (parameters.rename) {
			String newTorrentPath = String.format("%s-%s.torrent", movie.getOriginalTitle().replace(" ", "").toLowerCase(),
					movie.getYear());
			torrent.renameTo(new File(newTorrentPath));
			System.out.println(String.format("And renamed to [%s]", newTorrentPath));
		}
	}
}
