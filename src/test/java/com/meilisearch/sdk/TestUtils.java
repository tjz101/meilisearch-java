package com.meilisearch.sdk;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TestUtils {

	String movies_data;
	Movie[] movies = new Movie[10];
	Gson gson = new Gson();

	public TestUtils () {
		this.movies = gson.fromJson(moviesFileToString(),Movie[].class);
	}

	public String moviesFileToString () {
		String content = "";
		String filePath = "src/test/java/com/meilisearch/sdk/movies.json";
		try
		{
			content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		this.movies_data = content;
		return content;
	}

	static public void deleteAllIndexes() throws Exception {
		Client ms = new Client(new Config("http://localhost:7700", "masterKey"));
		Index[] indexes = ms.getIndexList();
		for (int i = 0; i < indexes.length; i++) {
			ms.deleteIndex(indexes[i].uid);
		}
	}
}