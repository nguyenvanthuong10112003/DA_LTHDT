package model;

import java.sql.SQLException;
import java.util.Date;
import define.table.USER;

public class User {
	private String name;
	private String tenDangNhap;
	private String passWord;
	private String phoneNumber;
	private String email;
	private Date dateCreated;
	private Boolean sex;
	private String address;
	private Date dateOfBirth;
	private Folder root;

	@SuppressWarnings("deprecation")
	public User(String tendangnhap, String password) {
		this.tenDangNhap = tendangnhap;
		this.passWord = password;
		this.name = "Thành viên mới";
		this.phoneNumber = "";
		this.email = "";
		this.dateCreated = java.util.Calendar.getInstance().getTime();
		this.dateCreated.setYear(java.time.LocalDate.now().getYear());
		this.sex = null;
		this.address = "";
		this.dateOfBirth = null;
		this.root = null;
	}

	@SuppressWarnings("deprecation")
	public User(String tendangnhap, String password, String name, String phone, String email, Boolean sex,
			String country, Date birth, Folder root) {
		this.tenDangNhap = tendangnhap;
		this.passWord = password;
		this.name = name;
		this.phoneNumber = phone;
		this.email = email;
		this.dateCreated = (Date) java.util.Calendar.getInstance().getTime();
		this.dateCreated.setYear(java.time.LocalDate.now().getYear());
		this.sex = sex;
		this.address = country;
		this.dateOfBirth = birth;
		this.root = root;
	}

	public User(String tendangnhap, String password, String name, String phone, String email, Boolean sex,
			String country, Date create, Date birth, Folder root) {
		this.tenDangNhap = tendangnhap;
		this.passWord = password;
		this.name = name;
		this.phoneNumber = phone;
		this.email = email;
		this.dateCreated = create;
		this.sex = sex;
		this.address = country;
		this.dateOfBirth = birth;
		this.root = root;
	}

	public Folder getRoot() {
		return root;
	}

	public void setRoot(Folder root) {
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

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddess(String country) {
		address = country;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@SuppressWarnings("deprecation")
	public String stringDateOfBirth()
	{
		if(dateOfBirth == null)
			return "";
		return check(dateOfBirth.getDate()) +  "/" + check(dateOfBirth.getMonth()) + 
				"/" + check(dateOfBirth.getYear());
	}
	
	@SuppressWarnings("deprecation")
	public String getTime(Date date) {
		if (date != null)
			return check(date.getDate()) + "/" + check(date.getMonth()) + "/" + check(date.getYear()) + " "
					+ check(date.getHours()) + ":" + check(date.getMinutes()) + ":" + check(date.getSeconds());
		return "";
	}
	
	public String check(int a) {
		if (a >= 10)
			return ((Integer) a).toString();
		return "0" + a;
	}
	
	@SuppressWarnings("deprecation")
	public void updateToSql(java.sql.Statement sta)
	{
		String sql = "Update " + USER.nametable + " SET ";
		if(sex != null)
		{
			sql += USER.sex + " = " + (sex == true ? 1 : 0) + " , ";
		}
		if(dateOfBirth != null)
		{
			sql += USER.birth + " = '" + dateOfBirth.getDate() + "-" + dateOfBirth.getMonth() + "-" + dateOfBirth.getYear() + "' , "; 
		}
		if(!"".equals(name))
		{
			sql += USER.name + " = N'" + name + "', ";
		}
		if(!"".equals(address))
		{
			sql += USER.address + " = N'" + address + "', ";	
		}
		if(!"".equals(email))
		{
			sql += USER.email + " = '" + email + "', ";	
		}
		if(!"".equals(phoneNumber))
		{
			sql += USER.phone + " = '" + phoneNumber + "', ";	
		}
		sql = sql.substring(0, sql.length() - 2);
		sql += " WHERE " + USER.username + " = '" + tenDangNhap + "'";
		try {
			int check = sta.executeUpdate(sql);
			if(check > 0)
				System.out.println("Update " + USER.nametable + " SUCCESS");
		} catch (SQLException e) {
			System.out.println("Update " + USER.nametable + " ERROR");
			e.printStackTrace();
		}
	}
	
	public void UpdatePassWordToSql(java.sql.Statement sta)
	{
		String sql = "Update " + USER.nametable + " SET " + USER.password + " = '" + passWord + "' WHERE " + USER.username + " = '" + tenDangNhap + "'";
		try {
			int check = sta.executeUpdate(sql);
			if(check > 0)
				System.out.println("Update PassWord" + USER.nametable + " SUCCESS");
		} catch (SQLException e) {
			System.out.println("Update PassWord" + USER.nametable + " ERROR");
			e.printStackTrace();
		}
	}
}
