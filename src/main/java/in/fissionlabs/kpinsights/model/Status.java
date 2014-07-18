package in.fissionlabs.kpinsights.model;

public class Status {


	private String info;

	private Boolean hasErrors;

	public Status(){

	}

	public Status(Boolean hasErrors, String info){
		this.hasErrors = hasErrors;
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Boolean getHasErrors() {
		return hasErrors;
	}

	public void setHasErrors(Boolean hasErrors) {
		this.hasErrors = hasErrors;
	}


}
