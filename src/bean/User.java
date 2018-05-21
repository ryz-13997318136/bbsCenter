package bean;

import org.apache.struts.action.ActionForm;

public class User extends ActionForm {
	private static final long serialVersionUID = -734609785788348370L;
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String name;
    public String password;

}
