package com.mdarcemont.javaangular.resources;

import com.mdarcemont.javaangular.models.Director;
import net.codestory.http.annotations.Get;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DirectorResource {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Get("/director/:directorId")
    public Director getDirector(String directorId) throws Exception {
        System.err.println("YAHOO");
        URL url = new URL("https://api.themoviedb.org/3/person/" + directorId + "?api_key=cf200859a44c319de6e4d6bf83d4c556");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));
        StringBuilder buffer = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            buffer.append(output);
        }
        output = buffer.toString();

        return jsonToDirector(new JSONObject(output));
    }

    private Director jsonToDirector(JSONObject jsonObject) throws JSONException {
        Director director = new Director();
        director.setName(jsonObject.getString("name"));
        director.setBirthDate(LocalDate.parse(jsonObject.getString("birthday"), formatter));

        LocalDate deathDate = !jsonObject.getString("deathday").equals("") ? LocalDate.parse(jsonObject.getString("deathday"), formatter) : null;
        director.setDeathDate(Optional.ofNullable(deathDate));

        director.setProfile(jsonObject.getString("profile_path"));
        return director;
    }
}