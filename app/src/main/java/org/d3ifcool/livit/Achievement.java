package org.d3ifcool.livit;

/**
 * Created by Multimedia on 13/03/2018.
 */

public class Achievement {
    private String title;
    private String description;
    private int progress;
    private int category;

    public static final int CATEGORY_EXCERSISE = 0;
    public static final int CATEGORY_NUTRITION = 1;
    public static final int CATEGORY_OTHER = 2;

    public Achievement(String title, String description, int category) {
        this.title = title;
        this.description = description;
        this.progress = 0;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isCompleted() {
        return progress==100;
    }

    public int getCategory() {
        return category;
    }
}
