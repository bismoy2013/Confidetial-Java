package in.fissionlabs.kpinsights.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ROLES")
public class Role extends AbstractModel{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "ROLE_NAME", unique = true, nullable = false)
	private String name;
	
	@Transient
	private Status status;
	
	public Role(){
		
	}
	
	public Role(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
