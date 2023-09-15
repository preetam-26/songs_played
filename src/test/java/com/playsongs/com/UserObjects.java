package com.playsongs.com;

public class UserObjects {
	
	private String name;

    private String user;

    public UserObjects(String name, String user){
        this.name=name;
        this.user=user;
    }

    public String getName(){
        return name;
    }

    public String getUser(){
        return user;
    }

}
