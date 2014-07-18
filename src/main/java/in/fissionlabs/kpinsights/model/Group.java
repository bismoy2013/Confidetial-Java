package in.fissionlabs.kpinsights.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="GROUPS")
public class Group extends AbstractModel{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "GROUP_ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "GROUP_NAME", unique = true, nullable = false)	
	private String name;
	
	@Column(name="PARENT_GROUP_ID")
	private Integer parentGroupId;
	
	@Transient
	private Status status;
	
	
	public Group(){
		
	}
	
	public Group(Long id, String name, int parentGroupId) {
		super();
		this.id = id;
		this.name = name;
		this.parentGroupId = parentGroupId;
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

	public Integer getParentGroupId() {
		return parentGroupId;
	}

	public void setParentGroupId(Integer parentGroupId) {
		this.parentGroupId = parentGroupId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
