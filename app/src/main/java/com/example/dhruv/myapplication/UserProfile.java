package com.example.dhruv.myapplication;

public class UserProfile {
    public String Age;
    public String Email;
    public String Name;

    public UserProfile(){

    }

    public UserProfile(String userAge, String userEmail, String userName) {
        this.Age = userAge;
        this.Email = userEmail;
        this.Name = userName;
    }

    public String getAge() {
        return Age;
    }

    public String getEmail() {
        return Email;
    }

    public String getName() {
        return Name;
    }


}
