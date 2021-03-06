package com.mdarcemont.mymoviedatabase.models;

import java.time.LocalDate;
import java.util.Optional;

public class Director {

    private String name;

    private LocalDate birthDate;

    private Optional<LocalDate> deathDate;

    private String profile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Optional<LocalDate> getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Optional<LocalDate> deathDate) {
        this.deathDate = deathDate;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
