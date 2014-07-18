package in.fissionlabs.kpinsights.model;

import java.util.Date;

import javax.persistence.Column;

public class AbstractModel {

	@Column(name="CREATE_TIMESTAMP")
	private Date createTimestamp;
	
	@Column(name="UPDATE_TIMESTAMP")
	private Date updateTimestamp;

	public Date getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}
	
	
}
