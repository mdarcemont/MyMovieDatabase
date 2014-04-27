package com.mdarcemont.mymoviedatabase;

import com.mdarcemont.mymoviedatabase.resources.DirectorResource;
import com.mdarcemont.mymoviedatabase.resources.MovieResource;
import net.codestory.http.WebServer;

public class Main {

    public static void main(String args[]) {
        new WebServer(routes ->
                routes.add(DirectorResource.class).add(MovieResource.class)
        ).start();
    }
}
