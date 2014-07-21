package com.flickr.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


import java.io.IOException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

public class RSSFeedParserTest {

	@Test
	public void test() throws JSONException {
		RSSFeedParser feedparser = null;
		try {
			feedparser = new RSSFeedParser("http://www.google.com");
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		JSONObject jsonExpectedDifferent = new JSONObject(
				"{\"test\": {\"content1\": \"data\", \"attr\": \"a\"}}");
		
		JSONObject jsonExpected = new JSONObject(
				"{\"test\": {\"content\": \"data\", \"attr\": \"a\"}}");
		JSONObject jsonResult = new JSONObject(
				feedparser
						.xml2json("<?xml version=\"1.0\" ?><test attr=\"a\">data</test>"));
		
		
		// Tests
		assertEquals("Converting xml to json has bad result.",
				jsonExpected.toString(), jsonResult.toString());
		
		assertFalse("Match is not good.",jsonExpectedDifferent.equals(jsonResult));
	}

}
