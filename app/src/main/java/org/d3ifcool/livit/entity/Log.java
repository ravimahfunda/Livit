package org.d3ifcool.livit.entity;

/**
 * Represent data of user's recent activities
 * Consisting time and description of the activities
 */
public class Log {
    private String time;
    private String description;

    public Log(String time, String description) {
        this.time = time;
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }


    public static void d() {
    }

    public static void d(String nutritions, String s) {
    }
}
