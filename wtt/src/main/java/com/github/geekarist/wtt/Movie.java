package com.github.geekarist.wtt;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Movie {

    private static Logger LOGGER = Logger.getLogger(Movie.class);

    public static final String YEAR_SELECTOR = "span[itemprop=datePublished]";
    public static final String ORIGINAL_TITLE_SELECTOR = "tr:has(th div:contains(Titre original)) th + td";
    public static final String TITLE_SELECTOR = "#title span";

    public static final String USER_AGENT = "\"Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
    public static final String REFERRER = "http://www.google.com";
    public static final int TIMEOUT = 20000;

    private String title;
    private String originalTitle;
    private String year;

    public Movie(String title, String originalTitle, String year) {
        this.title = title;
        this.originalTitle = originalTitle;
        this.year = year;
    }

    public static Movie get(String s) {
        try {
            Document document = Jsoup.connect(s).userAgent(USER_AGENT).referrer(REFERRER).timeout(TIMEOUT).get();
            String title = StringUtils.trim(document.select(TITLE_SELECTOR).get(0).text());
            Elements originalTitleElements = document.select(ORIGINAL_TITLE_SELECTOR);
            String originalTitle = originalTitleElements.size() == 0 ? title : originalTitleElements.get(0).text();
            String year = StringUtils.split(document.select(YEAR_SELECTOR).attr("content"), '-')[0];
            return new Movie(title, originalTitle, year);
        } catch (IOException e) {
            LOGGER.error("Error while getting movie from [" + s + "]", e);
            return null;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getYear() {
        return year;
    }

}
