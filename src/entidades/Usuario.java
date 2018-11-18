package entidades;

public class Usuario {

	private int code;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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

	public Usuario(String userName, String password, String firstName, String lastName) {

		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Usuario(int code, String userName, String password, String firstName, String lastName) {
		this.code = code;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
