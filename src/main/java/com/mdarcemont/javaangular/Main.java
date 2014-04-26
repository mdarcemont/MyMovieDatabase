package com.mdarcemont.javaangular;

import com.mdarcemont.javaangular.models.Director;
import com.mdarcemont.javaangular.resources.DirectorResource;
import net.codestory.http.WebServer;

public class Main {

    public static void main(String args[]) {
        new WebServer(routes -> routes.add(DirectorResource.class)
        ).start();
    }
}
