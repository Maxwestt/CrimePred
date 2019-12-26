/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crimepred;

import java.util.Date;

/**
 *
 * @author Chris
 */
public class Crime {

    private Date arrestDate;
    private Date time;
    private int reportingDistrict;
    private int age;
    private String sexCode;
    private String descentCode;
    private int chargeGroupCode;
    private String address;
    private String crossStreet;
    private double latitude;
    private double longitude;

    public Crime(Date arrestDate, Date time, int reportingDistrict, int age, String sexCode, String descentCode, int chargeGroupCode, String address, String crossStreet, double latitude, double longitude) {
        this.arrestDate = arrestDate;
        this.time = time;
        this.reportingDistrict = reportingDistrict;
        this.age = age;
        this.sexCode = sexCode;
        this.descentCode = descentCode;
        this.chargeGroupCode = chargeGroupCode;
        this.address = address;
        this.crossStreet = crossStreet;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Date getArrestDate() {
        return arrestDate;
    }

    public Date getTime() {
        return time;
    }

    public int getReportingDistrict() {
        return reportingDistrict;
    }

    public int getAge() {
        return age;
    }

    public String getSexCode() {
        return sexCode;
    }

    public String getDescentCode() {
        return descentCode;
    }

    public int getChargeGroupCode() {
        return chargeGroupCode;
    }

    public String getAddress() {
        return address;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setArrestDate(Date arrestDate) {
        this.arrestDate = arrestDate;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setReportingDistrict(int reportingDistrict) {
        this.reportingDistrict = reportingDistrict;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    public void setDescentCode(String descentCode) {
        this.descentCode = descentCode;
    }

    public void setChargeGroupCode(int chargeGroupCode) {
        this.chargeGroupCode = chargeGroupCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Crime{" + "arrestDate=" + arrestDate + ", time=" + time + ", reportingDistrict=" + reportingDistrict + ", age=" + age + ", sexCode=" + sexCode + ", descentCode=" + descentCode + ", chargeGroupCode=" + chargeGroupCode + ", address=" + address + ", crossStreet=" + crossStreet + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

}
