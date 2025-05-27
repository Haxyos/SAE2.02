package app.ai;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import app.model.parser.JSONArray;
import app.model.parser.JSONObject;

public class API {
	private  String key = "AIzaSyAJVgJVwXPU5eImYwnnHdcXFy0xeAU4QzM"; 
	private URL url;
	private HttpURLConnection httpConn;
	private String result;
	private int codeErr;
	
	public int getCodeErr() {
		return codeErr;
	}

	public String getKey() {
		return key;
	}

	public URL getUrl() {
		return url;
	}

	public String getResult() {
		return result;
	}

	public JSONObject getGenerationConfig() {
		return generationConfig;
	}

	private JSONObject responseSchema = new JSONObject().put("type", "object").put("properties", new JSONObject().put("nom", new JSONObject().put("type", "string")).put("texte", new JSONObject().put("type", "string"))).put("required", new JSONArray().add("nom").add("texte"));
	
	private JSONObject generationConfig = new JSONObject().put("maxOutputTokens", 200).put("responseMimeType", "application/json").put("responseSchema", responseSchema);
	
	private JSONObject jsonText = new JSONObject().put("text", "Crée des lieux cohérents pour un jeu de rôle basé sur de la fantasy? ");
	private JSONArray partsArr = new JSONArray().add(jsonText);
	private JSONObject jsonParts = new JSONObject().put("parts", partsArr);
	private JSONArray jsonContents = new JSONArray().add(jsonParts);
	//private JSONObject jsonConf = new JSONObject().put("generationConfig", JSONObject);
	private JSONObject jsonGlobal= new JSONObject().put("contents", jsonContents).put("generationConfig", generationConfig);
	
	
			
			
	public API() {
		try {
			url = new URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + key);
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		try {
			httpConn = (HttpURLConnection)url.openConnection();
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("Content-Type", "application/json");
			httpConn.setDoOutput(true);
			httpConn.setRequestProperty("Content-Type", "application/json");
			OutputStream os = httpConn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");    
			osw.write(jsonGlobal.toString());
			osw.flush();
			osw.close();
			os.close();
			httpConn.connect();

			
			BufferedInputStream bis = new BufferedInputStream(httpConn.getInputStream());
			ByteArrayOutputStream buf = new ByteArrayOutputStream();
			int result2 = bis.read();
			while(result2 != -1) {
			    buf.write((byte) result2);
			    result2 = bis.read();
			}
			result = buf.toString();
			codeErr = httpConn.getResponseCode();
			
			//System.out.println(httpConn.getResponseCode());
			System.out.println(result);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String  arg[]) {
		new API();
	}
}