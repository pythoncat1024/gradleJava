package com.intellij.cat.generic;

public class User {

    private String name;
    private String uid;
    private int age;
    private int gender;

    private User() {
    }

    static class Builder {

        private User self = new User();

        public User build() {
            return self;
        }

        public Builder appendName(String name) {
            self.name = (name);
            return this;
        }

        public Builder appendUid(String uid) {
            self.uid = (uid);
            return this;
        }

        public Builder appendGender(int gender) {
            self.gender = (gender);
            return this;
        }

        public Builder appendAge(int age) {
            self.age = age;
            return this;
        }
    }


    public String getName() {
        return name;
    }


    public String getUid() {
        return uid;
    }


    public int getAge() {
        return age;
    }


    public int getGender() {
        return gender;
    }
}
