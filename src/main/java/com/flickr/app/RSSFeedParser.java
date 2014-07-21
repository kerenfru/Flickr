package com.flickr.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class RSSFeedParser {
	final URL url;

	public RSSFeedParser(String feedUrl) throws IOException {
		this.url = new URL(feedUrl);
	}

	// read the feed by the given url
	public String readFeed() {
		try {

			InputStream in = read();
			String result = getStringFromInputStream(in);
			String v = xml2json(result);

			return v;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// convert InputStream to String
	private String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();
	}

	// Convert xml string to json string
	private String xml2json(String xml) {
		String jsonPrettyPrintString = null;
		try {
			JSONObject xmlJSONObj = XML.toJSONObject(xml);
			jsonPrettyPrintString = xmlJSONObj.toString(4);
		} catch (JSONException je) {
			System.out.println(je.toString());
		}
		return jsonPrettyPrintString;
	}

	private InputStream read() {
		try {
			return url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
