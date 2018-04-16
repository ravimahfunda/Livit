package org.d3ifcool.livit.entity;

/**
 * Represent data of a user
 * Consisting name, email, total track, average speed, and total calori burned
 */
public class UserSummary {
    private String name;
    private String email;
    private String track;
    private String avgSpeed;
    private String caloriBurned;

    public UserSummary(String name, String email, String track, String avgSpeed, String caloriBurned) {
        this.name = name;
        this.email = email;
        this.track = track;
        this.avgSpeed = avgSpeed;
        this.caloriBurned = caloriBurned;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTrack() {
        return track;
    }

    public String getAvgSpeed() {
        return avgSpeed;
    }

    public String getCaloriBurned() {
        return caloriBurned;
    }
}
