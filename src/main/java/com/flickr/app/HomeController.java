package com.flickr.app;

import java.io.IOException;
import java.util.Locale;

import org.json.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private final String flickrFeedUrl = "https://api.flickr.com/services/feeds/photos_public.gne"; 
	private final String FEED = "feed";
	private final String ENTRY = "entry";
	private final String NOTVALID = " is not a valid xml feed.";
	private final String ID = "?id=";
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws IOException 
	 */
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() throws IOException {
		return "redirect:/resources/index.html";
	}
	
	/*
	 * Gets Flickr feed
	 */
	@RequestMapping(value = "/feed", method = RequestMethod.GET)
	@ResponseBody
	public String parseFeed(Locale locale, Model model) {
	    RSSFeedParser parser;
		try {
			parser = new RSSFeedParser(flickrFeedUrl);
			return parse(parser).toString();
		} catch (IOException e) {
			System.err.println(flickrFeedUrl + NOTVALID);
		}
		return null;
	}
	
	/*
	 * Gets Flickr feed with a given author
	 */
	@RequestMapping(value = "/feed/author/{author}", method = RequestMethod.GET)
	@ResponseBody
	public String parseFeedOfAuthor(@PathVariable String author) {
	    RSSFeedParser parser;
	    String authorUrl = flickrFeedUrl + ID + author;
		try {
			parser = new RSSFeedParser(authorUrl);
			return parse(parser).toString();
		} catch (IOException e) {
			System.err.println(authorUrl + NOTVALID);
		}
		return null;
	}
	
	/*
	 * Turns the fees into a JSON String
	 */
	private JSONArray parse(RSSFeedParser parser) {
	    String feed = parser.readFeed();
	    JSONObject jsonObj = new JSONObject(feed);
	    JSONObject feedjson = jsonObj.getJSONObject(FEED);
	    JSONArray arrayOfEntries = feedjson.getJSONArray(ENTRY);
		return arrayOfEntries;
	}
	
}
