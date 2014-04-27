package com.mdarcemont.javaangular.resources;

import com.mdarcemont.javaangular.models.Director;
import com.mdarcemont.javaangular.models.Movie;
import com.mdarcemont.javaangular.models.Role;
import net.codestory.http.annotations.Get;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DirectorResource {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Get("/director/:directorId")
    public Director getDirector(String directorId) throws Exception {
        URL url = new URL("https://api.themoviedb.org/3/person/" + directorId + "?api_key=cf200859a44c319de6e4d6bf83d4c556");
        return jsonToDirector(ResourcesUtils.getJsonContentFromUrl(url));
    }

    @Get("/director/:directorId/movies")
    public JSONObject getDirectorMovies(String directorId) throws Exception {
        URL url = new URL("https://api.themoviedb.org/3/person/" + directorId + "/movie_credits?api_key=cf200859a44c319de6e4d6bf83d4c556");
        return jsonToMovies(ResourcesUtils.getJsonContentFromUrl(url));
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

    private JSONObject jsonToMovies(JSONObject jsonObject) throws JSONException {
        List<Role> allRoles = new ArrayList<>();
        JSONArray arrayMovies = jsonObject.getJSONArray("crew");

        for (int i = 0; i < arrayMovies.length(); i++) {
            allRoles.add(jsonToRole(arrayMovies.getJSONObject(i)));
        }

        return new JSONObject(allRoles
                .stream()
                .sorted((m1, m2) -> m1.getMovie().getReleaseDate().compareTo(m2.getMovie().getReleaseDate()))
                .collect(
                        Collectors.groupingBy(
                                Role::getJob,
                                Collectors.mapping(
                                        (Role r) -> r.getMovie(),
                                        Collectors.toList()
                                )
                        )));
    }

    private Role jsonToRole(JSONObject jsonObject) throws JSONException {
        Movie movie = new Movie();
        movie.setId(jsonObject.getString("id"));
        movie.setTitle(jsonObject.getString("title"));
        movie.setReleaseDate(LocalDate.parse(jsonObject.getString("release_date"), formatter));
        movie.setPoster(jsonObject.getString("poster_path"));

        Role role = new Role();
        role.setJob(jsonObject.getString("job"));
        role.setMovie(movie);

        return role;
    }
}