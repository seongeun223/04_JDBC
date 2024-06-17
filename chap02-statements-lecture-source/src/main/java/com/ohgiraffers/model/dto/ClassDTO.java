package com.ohgiraffers.model.dto;

public class ClassDTO {
    private int stuNo;
    private String stuName;
    private String gender;
    private String githubId;
    private String email;
    private String mbti;
    private String subNo;

    public ClassDTO() {
    }

    public ClassDTO(int stuNo, String stuName, String gender, String githubId, String email, String mbti, String subNo) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.gender = gender;
        this.githubId = githubId;
        this.email = email;
        this.mbti = mbti;
        this.subNo = subNo;
    }

    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGithubId() {
        return githubId;
    }

    public void setGithubId(String githubId) {
        this.githubId = githubId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMbti() {
        return mbti;
    }

    public void setMbti(String mbti) {
        this.mbti = mbti;
    }

    public String getSubNo() {
        return subNo;
    }

    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }

    @Override
    public String toString() {
        return "ClassDTO{" +
                "stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                ", gender='" + gender + '\'' +
                ", githubId='" + githubId + '\'' +
                ", email='" + email + '\'' +
                ", mbti='" + mbti + '\'' +
                ", subNo=" + subNo +
                '}';
    }
}
