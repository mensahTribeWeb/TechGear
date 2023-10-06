package com.example.demo.domain;

public class AboutInfo {
    private String missionStatement;
    private String aboutParagraph;

    //constructors
    public AboutInfo() {
    }

    public AboutInfo(String missionStatement, String aboutParagraph) {
        this.missionStatement = missionStatement;
        this.aboutParagraph = aboutParagraph;
    }

    //getters

    public String getMissionStatement() {
        return missionStatement;
    }

    public String getAboutParagraph() {
        return aboutParagraph;
    }

    //setters

    public void setMissionStatement(String missionStatement) {
        this.missionStatement = missionStatement;
    }

    public void setAboutParagraph(String aboutParagraph) {
        this.aboutParagraph = aboutParagraph;
    }

    @Override
    public String toString() {
        return "AboutInfo{" +
                "missionStatement='" + missionStatement + '\'' +
                ", aboutParagraph='" + aboutParagraph + '\'' +
                '}';
    }
}
