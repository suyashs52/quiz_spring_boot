package com.quiz.response;

public class JwtResponse {

	private String token;
    private String type = "Bearer";
    private String loginuser;
    private String role;
    private Integer userid;
	public JwtResponse(String token, String loginuser, String role,Integer id) {
		this.token = token;
		this.loginuser = loginuser;
		this.role = role;
		this.userid=id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLoginuser() {
		return loginuser;
	}

	public void setLoginuser(String loginuser) {
		this.loginuser = loginuser;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
 
	
	
}