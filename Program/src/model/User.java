package model;

import java.time.LocalTime;
import java.util.Date;

import javax.lang.model.util.Elements;

public class User {
    private String name;
    private String tenDangNhap;
    private String passWord;
    private String phoneNumber;
    private String email;
    private Date dateCreated;
    private String state;
    private Boolean sex;
    private String Country;
    private Date dateOfBirth;
    private Element root;
    public User(String tendangnhap, String password)
    {
        this.tenDangNhap = tendangnhap;
        this.passWord = password;
        this.name = "Thành viên mới";
        this.phoneNumber = "";
        this.email = "";
        this.dateCreated = java.util.Calendar.getInstance().getTime();
        this.state = "";
        this.sex = null;
        this.Country = "";
        this.dateOfBirth = null;
        this.root = new Folder(0);
    } 
    public User(String tendangnhap, String password, String name, 
    		String phone, String email, String state, 
    		Boolean sex, String country, Date birth, Element root)
    {
        this.tenDangNhap = tendangnhap;
        this.passWord = password;
        this.name = name;
        this.phoneNumber = phone;
        this.email = email;
        this.dateCreated = (Date) java.util.Calendar.getInstance().getTime();
        this.state = state;
        this.sex = sex;
        this.Country = country;
        this.dateOfBirth = birth;
        this.root = root;
    }
	public Element getRoot() {
		return root;
	}
	public void setRoot(Element root) {
		this.root = root;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
    
}
