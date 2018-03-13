package org.d3ifcool.livit;

/**
 * Created by haaniifaa on 12/03/2018.
 */

public class Recommendation {
    private String title;
    private String description;
    private boolean isReminding;

    public Recommendation(String title, String description) {
        this.title = title;
        this.description = description;
        this.isReminding = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isReminding() {
        return isReminding;
    }

    public void setReminding(boolean reminding) {
        isReminding = reminding;
    }
}
