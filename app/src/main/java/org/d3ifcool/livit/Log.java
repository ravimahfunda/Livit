package org.d3ifcool.livit;

/**
 * Created by haaniifaa on 13/03/2018.
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
}
