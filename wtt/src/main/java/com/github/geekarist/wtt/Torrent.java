package com.github.geekarist.wtt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Torrent {

	public static File find(String query) {
		try {
			String queryString = StringUtils.join(StringUtils.split(query), '+');
			
			// Get torrent download page url
			Document document = Jsoup.connect(String.format("http://fenopy.se/search/%s.html?cat=3", queryString))
					.userAgent(Movie.USER_AGENT).referrer(Movie.REFERRER).timeout(Movie.TIMEOUT).get();
			String downloadUrl = "http://fenopy.se" + document.select("tr:first-child td.c1 a").attr("href");

			// Get torrent file url
			Document document2 = Jsoup.connect(downloadUrl).userAgent(Movie.USER_AGENT).referrer(Movie.REFERRER)
					.timeout(Movie.TIMEOUT).get();
			String torrentUrl = document2.select(".bt.ttip[href^=http://torcache.net]").attr("href");

			// Download torrent file
			Response execute = Jsoup.connect(torrentUrl).userAgent(Movie.USER_AGENT).referrer(torrentUrl)
					.timeout(Movie.TIMEOUT).ignoreContentType(true).followRedirects(true).method(Method.GET).execute();
			byte[] torrentContents = execute.bodyAsBytes();
			String torrentPath = Paths.get(torrentUrl).getFileName().toString();
			Files.write(Paths.get(torrentPath), torrentContents);
			return new File(torrentPath);
		} catch (IOException e) {
			System.err.printf("Error while searching [%s]\n", query);
			e.printStackTrace(System.err);
			return null;
		}
	}

}
