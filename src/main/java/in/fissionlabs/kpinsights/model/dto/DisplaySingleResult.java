package in.fissionlabs.kpinsights.model.dto;

import in.fissionlabs.kpinsights.model.AbstractModel;
import in.fissionlabs.kpinsights.model.Status;


public class DisplaySingleResult {

	AbstractModel result;

	Status status;

	public AbstractModel getResult() {
		return result;
	}

	public void setResult(AbstractModel result) {
		this.result = result;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


}
