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
	public String parseFeed(Locale locale, Model model) throws IOException {
	    RSSFeedParser parser = new RSSFeedParser("https://api.flickr.com/services/feeds/photos_public.gne");
		return parse(parser).toString();
	}
	
	/*
	 * Gets Flickr feed with a given author
	 */
	@RequestMapping(value = "/feed/author/{author}", method = RequestMethod.GET)
	@ResponseBody
	public String parseFeedOfAuthor(@PathVariable String author) throws IOException {
	    RSSFeedParser parser = new RSSFeedParser("https://api.flickr.com/services/feeds/photos_public.gne?id=" + author);
		return parse(parser).toString();
	}
	
	/*
	 * Turns the fees into a JSON String
	 */
	private JSONArray parse(RSSFeedParser parser) {
	    String feed = parser.readFeed();
	    JSONObject jsonObj = new JSONObject(feed);
	    JSONObject feedjson = jsonObj.getJSONObject("feed");
	    JSONArray arrayOfEntries = feedjson.getJSONArray("entry");
		return arrayOfEntries;
	}
	
}
