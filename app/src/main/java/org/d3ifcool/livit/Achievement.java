package org.d3ifcool.livit;


/**
 * Represent data of user's achievements
 * Consisting title, description, progress, and category of the achievement
 */
public class Achievement {
    private String title;
    private String description;
    private int progress;
    private int category;

    public static final int CATEGORY_EXCERSISE = 0;
    public static final int CATEGORY_NUTRITION = 1;
    public static final int CATEGORY_OTHER = 2;

    public Achievement(String title, String description, int progress, int category) {
        this.title = title;
        this.description = description;
        this.progress = progress;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getProgress() {
        return progress;
    }

    //Returns true if the achievemnt is complete, false if not
    public boolean isCompleted() {
        return progress==100;
    }

    public int getCategory() {
        return category;
    }
}
