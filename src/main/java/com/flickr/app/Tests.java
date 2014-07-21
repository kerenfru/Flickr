package com.flickr.app;

import static org.junit.Assert.*;

import java.io.IOException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;
import org.springframework.http.converter.feed.RssChannelHttpMessageConverter;

public class Tests {

	@Test
	public void test() throws JSONException {
		// HomeController homecontroller = new HomeController();
		RSSFeedParser feedparser = null;
		try {
			feedparser = new RSSFeedParser("http://www.google.com");
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject jsonResult = new JSONObject(
				"{\"test\": {\"content\": \"data\", \"attr\": \"a\"}}");
		JSONObject jsonResult2 = new JSONObject(
				feedparser
						.xml2json("<?xml version=\"1.0\" ?><test attr=\"a\">data</test>"));
		assertEquals("Converting xml to json has bad result.",
				jsonResult2.toString(), jsonResult.toString());
	}

}
