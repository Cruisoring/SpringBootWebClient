package com.springboot.client.model;

public interface UserDetails {
    String getEmail();

    String getName();

    String getUserName();

    String getPassword();

    default boolean isEqualTo(UserDetails another){
        if(another == null)
            return false;

        return this.getEmail().equals(another.getEmail())
                && this.getName().equals(another.getName())
                && this.getUserName().equals(another.getUserName());
    }
}
