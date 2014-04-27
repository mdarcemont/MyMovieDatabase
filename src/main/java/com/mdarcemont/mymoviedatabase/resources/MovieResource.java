package com.mdarcemont.mymoviedatabase.resources;

import com.mdarcemont.mymoviedatabase.models.Movie;
import net.codestory.http.annotations.Get;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MovieResource {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Get("/movie/:movieId")
    public Movie getMovie(String movieId) throws Exception {
        URL url = new URL("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=cf200859a44c319de6e4d6bf83d4c556");
        return jsonToMovie(ResourcesUtils.getJsonContentFromUrl(url));
    }

    private Movie jsonToMovie(JSONObject jsonObject) throws JSONException {
        Movie movie = new Movie();
        movie.setId(jsonObject.getString("id"));
        movie.setTitle(jsonObject.getString("title"));
        movie.setOverview(jsonObject.getString("overview"));
        movie.setNote(jsonObject.getDouble("vote_average"));
        movie.setReleaseDate(LocalDate.parse(jsonObject.getString("release_date"), formatter));
        movie.setPoster(jsonObject.getString("poster_path"));
        return movie;
    }
}
