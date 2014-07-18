package in.fissionlabs.kpinsights.model.hibernate;

import java.util.HashSet;

public class SearchCriteria {

	private String matchCategory;
	private Object matchValue;
	private Boolean matchExact;
	
	public SearchCriteria(){
		
	}
	
	public SearchCriteria(String matchCategory, Object matchValue, Boolean matchExact){
		this.matchCategory = matchCategory;
		this.matchValue = matchValue;
		this.matchExact = matchExact;
	}

	public String getMatchCategory() {
		return matchCategory;
	}

	public void setMatchCategory(String matchCategory) {
		this.matchCategory = matchCategory;
	}

	public Object getMatchValue() {
		return matchValue;
	}

	public void setMatchValue(Object matchValue) {
		this.matchValue = matchValue;
	}

	public Boolean getMatchExact() {
		return matchExact;
	}

	public void setMatchExact(Boolean matchExact) {
		this.matchExact = matchExact;
	}
	
	
}
