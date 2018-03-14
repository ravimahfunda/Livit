package org.d3ifcool.livit;

/**
 * Created by haaniifaa on 14/03/2018.
 */

public class User {
    private String displayName ;
    private String email ;
    private String username ;
    private String bloodType ;
    private String goals ;
    private String height ;
    private String weight ;
    private String age ;
    private String sex ;

    public User(String displayName, String username, String email, String bloodType, String goals ,String height , String weight ,String age , String sex) {
        this.displayName = displayName;
        this.email = email;
        this.username= username;
        this.bloodType= bloodType;
        this.goals = goals;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
    }


    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
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
