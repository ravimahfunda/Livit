package org.d3ifcool.livit.entity;

/**
 * Represent data of a user
 * Consisting displayName, email, username , bloodType, goals , height , weight , age , sex
 */

public class User {
    private String bloodType ;
    private String goals ;
    private String height ;
    private String weight ;
    private String age ;
    private String sex ;

    public User(String bloodType, String goals ,String height , String weight ,String age , String sex) {
        this.bloodType= bloodType;
        this.goals = goals;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getGoals() {
        return goals;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }
}
