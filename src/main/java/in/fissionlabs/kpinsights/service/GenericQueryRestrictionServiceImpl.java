package in.fissionlabs.kpinsights.service;

import in.fissionlabs.kpinsights.model.hibernate.SearchCriteria;

import java.util.HashSet;

import org.springframework.stereotype.Service;

@Service("GenericQueryRestrictionService")
public class GenericQueryRestrictionServiceImpl implements GenericQueryRestrictionService{


	private HashSet<SearchCriteria> restrictions = null;
	private SearchCriteria searchCriteria = null;

	public HashSet<SearchCriteria> resetSearchParameters(String matchCategory, Object matchValue, Boolean matchExact){
		resetSearchParameters();
		restrictions.add(new SearchCriteria(matchCategory, matchValue, matchExact));

		return restrictions;
	}

	public HashSet<SearchCriteria> updateSearchParameters(HashSet<SearchCriteria> restrictions, String matchCategory, Object matchValue, Boolean matchExact){
		restrictions.add(new SearchCriteria(matchCategory, matchValue, matchExact));

		return restrictions;
	}

	public void resetSearchParameters(){
		initRestrictions();
		initSearchCriteria();
	}

	private void initRestrictions(){
		restrictions = null;
		restrictions = new HashSet<SearchCriteria>();
	}
	private void initSearchCriteria(){
		searchCriteria = null;
		searchCriteria = new SearchCriteria();
	}
}
