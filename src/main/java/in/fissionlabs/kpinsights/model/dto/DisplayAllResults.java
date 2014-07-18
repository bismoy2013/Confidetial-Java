package in.fissionlabs.kpinsights.model.dto;

import in.fissionlabs.kpinsights.model.AbstractModel;
import in.fissionlabs.kpinsights.model.Status;

import java.util.Collection;
/**
 * Data transfer object to return mutiple model objects.
 * @author bismoy
 *
 */
public class DisplayAllResults {

	Collection<? extends AbstractModel> results;
	
	Status status;

	/**
	 * 
	 * @returns Collection of model objects which implements AbstractModel
	 */
	public Collection<? extends AbstractModel> getResults() {
		return results;
	}

	public void setResults(Collection<? extends AbstractModel> results) {
		this.results = results;
	}

	/**
	 *
	 * @returns Status
	 */
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
