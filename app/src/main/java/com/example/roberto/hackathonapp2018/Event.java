package com.example.roberto.hackathonapp2018;

public class Event
{
    //Picture
    private String name;
    //Date
    //Time
    private String location;
    private String discription;
    private boolean rsvp;
    private Organization organization;
    private String tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public boolean isRsvp() {
        return rsvp;
    }

    public void setRsvp(boolean rsvp) {
        this.rsvp = rsvp;
    }
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }



    public Event(String name, String location, String discription, boolean rsvp, String tags) {
        this.name = name;
        this.location = location;
        this.discription = discription;
        this.rsvp = rsvp;
        this.tags = tags;
        this.organization = organization;
    }
}
