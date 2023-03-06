package kh.spring.s01.member.model.vo;

public class MemberVo {
//
//	ID    |PASSWD|NAME|EMAIL            |
//	------+------+----+-----------------+
//	admin |admin |관리자 |admin@test.or.kr |
//	user11|pass11|사용자1|user11@test.or.kr|
//	user22|pass22|사용자2|user22@test.or.kr|
//	user3 |user3 |관리자 |user3@test.or.kr |
//	user4 |user4 |사용자4|user4@test.or.kr |
	
	
	private String id;
	private String passwd;
	private String name;
	private String email;
	
	@Override
	public String toString() {
		return "MemberVo [id=" + id + ", passwd=" + passwd + ", name=" + name + ", email=" + email + "]";
	}

	public MemberVo(String id, String passwd, String name, String email) {
		super();
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.email = email;
	}

	public MemberVo() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
