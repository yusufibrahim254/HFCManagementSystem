package org.example;

import java.sql.Date;

public class Member {
    private int memberID;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String phone;
    private Date dateOfBirth;
    private String fitnessGoal;
    private double weightGoal;
    private String timeGoal;
    private Date joinDate;
    private int trainerID; // Assuming a member can be associated with a trainer

    // Constructors
    public Member() {
    }

    public Member(int memberID, String firstName, String lastName, String email, String address, String city, String province, String postalCode, String phone, Date dateOfBirth, String fitnessGoal, double weightGoal, String timeGoal, Date joinDate, int trainerID) {
        this.memberID = memberID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.fitnessGoal = fitnessGoal;
        this.weightGoal = weightGoal;
        this.timeGoal = timeGoal;
        this.joinDate = joinDate;
        this.trainerID = trainerID;
    }

    // Getters and Setters
    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFitnessGoal() {
        return fitnessGoal;
    }

    public void setFitnessGoal(String fitnessGoal) {
        this.fitnessGoal = fitnessGoal;
    }

    public double getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(double weightGoal) {
        this.weightGoal = weightGoal;
    }

    public String getTimeGoal() {
        return timeGoal;
    }

    public void setTimeGoal(String timeGoal) {
        this.timeGoal = timeGoal;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public int getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }
}
