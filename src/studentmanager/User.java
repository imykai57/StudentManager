package studentmanager;

public class User {
	private String account;
	private String password;
	private String idnummber;
	private String phonenummber;
	public User(String account, String password, String idnummber, String phonenummber) {
		super();
		this.account = account;
		this.password = password;
		this.idnummber = idnummber;
		this.phonenummber = phonenummber;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdnummber() {
		return idnummber;
	}
	public void setIdnummber(String idnummber) {
		this.idnummber = idnummber;
	}
	public String getPhonenummber() {
		return phonenummber;
	}
	public void setPhonenummber(String phonenummber) {
		this.phonenummber = phonenummber;
	}
	
}
