package com.dev.frontend.services.facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RestfulClientFacade {

    static final Logger logger = Logger.getLogger(RestfulClientFacade.class);
    public static String BASEURL = "http://localhost:8181/";

    public static JSONObject getRequest(String urlString) {

	logger.debug("getRequest() | URLString: " + urlString);
	String response = "";
	try {
	    URL url = new URL(urlString);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Accept", "application/json");
	    conn.setRequestProperty("Content-Type", "application/json");

	    if (conn.getResponseCode() != 200) {
		throw new RuntimeException("Failed : HTTP error code : "
			+ conn.getResponseCode());
	    }

	    BufferedReader br = new BufferedReader(new InputStreamReader(
		    (conn.getInputStream())));

	    String responseLine = "";
	    while ((responseLine = br.readLine()) != null) {
		response += responseLine;
	    }

	    conn.disconnect();

	} catch (MalformedURLException e) {

	    response = "{\"error\":\"" + e.getMessage() + "\"}";

	} catch (IOException e) {

	    response = "{\"error\":\"" + e.getMessage() + "\"}";

	} finally {
	    logger.debug("getRequest() | Rest Response: " + response);
	    return convertToJSON(response);
	}

    }

    public static JSONArray getRequestForArray(String urlString) {

	logger.debug("getRequestForArray() | URLString: " + urlString);
	String response = "";
	try {
	    URL url = new URL(urlString);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Content-Type", "application/json");

	    if (conn.getResponseCode() != 200) {
		System.out.println("ERROR!!!!!!! Failed : HTTP error code : " + conn.getResponseCode());
		throw new RuntimeException("Failed : HTTP error code : "
			+ conn.getResponseCode());
	    }

	    BufferedReader br = new BufferedReader(new InputStreamReader(
		    (conn.getInputStream())));

	    String responseLine = "";
	    while ((responseLine = br.readLine()) != null) {
		response += responseLine;
	    }
	    conn.disconnect();

	} catch (MalformedURLException e) {

	    response = "{\"error\":\"" + e.getMessage() + "\"}";

	} catch (IOException e) {

	    response = "{\"error\":\"" + e.getMessage() + "\"}";

	} finally {
	    logger.debug("getRequestForArray() | Rest Response: " + response);
	    return convertToJSONArray(response);
	}

    }

    public static JSONObject postRequest(String urlString, String body) {

	logger.debug("postRequest() | URLString: " + urlString);
	logger.debug("postRequest() | REST Request: " + body);
	String response = "";
	try {

	    URL url = new URL(urlString);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setDoOutput(true);
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type", "application/json");

	    OutputStream os = conn.getOutputStream();
	    os.write(body.getBytes());
	    os.flush();

	    if (conn.getResponseCode() != 200) {
		throw new RuntimeException("Failed : HTTP error code : "
			+ conn.getResponseCode());
	    }

	    BufferedReader br = new BufferedReader(new InputStreamReader(
		    (conn.getInputStream())));

	    String responseLine = "";
	    while ((responseLine = br.readLine()) != null) {
		System.out.println("RESPONSELINE: " + responseLine);
		response += responseLine;
	    }

	    conn.disconnect();

	} catch (MalformedURLException e) {
	    response = "{\"error\":\"" + e.getMessage() + "\"}";
	    e.printStackTrace();

	} catch (IOException e) {
	    response = "{\"error\":\"" + e.getMessage() + "\"}";
	    e.printStackTrace();

	} finally {
	    logger.debug("postRequest() | REST Response: " + response);
	    return convertToJSON(response);
	}
    }

    public static JSONObject deleteRequest(String urlString) {

	logger.debug("deleteRequest() | URLString: " + urlString);
	String response = "";
	try {
	    URL url = new URL(urlString);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("DELETE");
	    conn.setRequestProperty("Content-Type", "application/json");

	    if (conn.getResponseCode() != 200) {
		throw new RuntimeException("Failed : HTTP error code : "
			+ conn.getResponseCode());
	    }

	    BufferedReader br = new BufferedReader(new InputStreamReader(
		    (conn.getInputStream())));

	    String responseLine = "";
	    while ((responseLine = br.readLine()) != null) {
		response += responseLine;
	    }

	    conn.disconnect();

	} catch (MalformedURLException e) {

	    response = "{\"error\":\"" + e.getMessage() + "\"}";

	} catch (IOException e) {

	    response = "{\"error\":\"" + e.getMessage() + "\"}";

	} finally {
	    logger.debug("deleteRequest() | REST Response: " + response);
	    return convertToJSON(response);
	}

    }

    static JSONObject convertToJSON(String response) {
	JSONObject jsonResponse = null;
	try {
	    if (!response.equals("")) {
		jsonResponse = (JSONObject) new JSONParser().parse(response);
	    }
	    else {
		jsonResponse = (JSONObject) new JSONParser().parse("{\"error\":\"Unexpected error in HttpClient\"}");
	    }

	} catch (ParseException e) {
	    e.printStackTrace();
	}
	return jsonResponse;
    }

    static JSONArray convertToJSONArray(String response) {
	JSONArray jsonResponse = null;
	try {
	    if (!response.equals("")) {
		jsonResponse = (JSONArray) new JSONParser().parse(response);
	    }
	    else {
		jsonResponse = (JSONArray) new JSONParser().parse("[{\"error\":\"Unexpected error in HttpClient\"}]");
	    }

	} catch (ParseException e) {
	    e.printStackTrace();
	}
	return jsonResponse;
    }
}
