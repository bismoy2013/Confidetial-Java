package in.fissionlabs.kpinsights.dao;

import in.fissionlabs.kpinsights.model.AbstractModel;
import in.fissionlabs.kpinsights.model.hibernate.SearchCriteria;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * GenericDAO has some generic methods for doing CRUD operations.
 * <ul>
 * <li>Find by criteria</li>
 * <li>Find by id</li>
 * <li>Find all objects</li>
 * <li>Create</li>
 * <li>Update</li>
 * <li>Delete</li>
 * <li>Save</li>
 * </ul>
 * @author sairaghav
 *
 * @param <T>  Here <T> is any model class which extends AbstractModel class
 * @see AbstractModel
 */

@Repository
public class GenericDAO<T extends AbstractModel> extends HibernateDaoSupport{

	private static Logger logger = Logger.getLogger(GenericDAO.class);

	/**
	 * <h4>This method is used to get single object or multiple objects based on user criteria.</h4>
	 * @param clazz				accepts any model class which extends AbstractModel
	 * @param searchCriteria	consists of all restrictions of Criteria
	 * @param displayUnique 	consists of true or false , if it is true displays a single object else displays multiple objects
	 * @return Object 
	 * if true 
	 * returns single object
	 * else 
	 * returns multiple objects.
	 */
	@Transactional
	public Object findByCriteria(Class<T> clazz, final HashSet<SearchCriteria> searchCriteria, final Boolean displayUnique){

		Criteria criteria = createCriteria(clazz);

		addSearchRestrictions(criteria,searchCriteria);

		if(displayUnique){
			return criteria.uniqueResult();
			
		}else{
			
			return criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
			
		}

	}

	/**
	 * <h4>This method fetches all the records based on search category and like search text.</h4>
	 * @param clazz				accepts any model class which extends AbstractModel
	 * @param searchCategory 	can be an emailAddress category or name category.
	 * @param searchText 		contains emailAddress or name
	 * @return Collection<T>    returns collection of @param clazz type class objects.
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Collection<T> findAppropriateMatchByCategoryAndValue(Class<T> clazz, final String searchCategory, final Object searchText){

		return createCriteria(clazz).add(Restrictions.like(searchCategory, searchText)).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
	}

	/**
	 * <h4>This method fetches all the records based on search category and search text.</h4>
	 * @param clazz  			accepts any model class which extends AbstractModel
	 * @param searchCategory 	can be an emailAddress category or name category.
	 * @param searchText 		contains emailAddress or name
	 * @return Object   		returns Object of @param clazz type.
	 */
	@Transactional
	public Object findExactMatchByCategoryAndValue(Class<T> clazz, final String searchCategory, final Object searchText){

		return createCriteria(clazz).add(Restrictions.eq(searchCategory, searchText)).uniqueResult();
	}

	/**
	 * <h4>This method is used fetch the record from the database based on id.</h4>
	 * @param clazz		accepts any model class which extends AbstractModel
	 * @param id		accepts id of any model class
	 * @return T		returns object of @param clazz type.
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public T findById(Class<T> clazz, final Long id){
		try{
			return (T) createCriteria(clazz).add(Restrictions.eq("id", id)).uniqueResult();
		}catch(Exception e){
			logger.error("Exception", e);
			throw new RuntimeException(e.getMessage());
		}
	}
	
    /**
     * <h4>This method returns a collection of objects.<h4>
     * @param clazz  accepts any model class which extends AbstractModel
     * @return  Collection<T>   returns collection of objects of @param clazz type.
     */
	@SuppressWarnings("unchecked")
	@Transactional
	public Collection<T> findAll(Class<T> clazz){

		return createCriteria(clazz).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();

	}

	/**
	 * <h4>This method is used to save the model object into database.<h4>
	 * @param t  accepts any model class which extends AbstractModel
	 * @return Boolean
	 * if true 
	 * record is inserted into database
	 * else
	 * record insertion failed.
	 */
	@Transactional
	public Boolean create(T t){

		t.setCreateTimestamp(new Date());	
		return save(t,false);
	}

	/**
	 * <h4>This method is used to update the model object in database.<h4>
	 * @param t  accepts any model class which extends AbstractModel
	 * @return Boolean
	 * if true 
	 * record is updated in database
	 * else
	 * record updation failed.
	 */
	@Transactional
	public Boolean update(T t){

		t.setUpdateTimestamp(new Date());
		return save(t,false);
	}

	
	/**
	 * <h4>This method is used to save the model object into database.<h4>
	 * @param t  accepts any model class which extends AbstractModel
	 * @return Boolean
	 * if true 
	 * record is inserted into database
	 * else
	 * record insertion failed.
	 */
	@Transactional
	public Boolean save(T t){

		t.setUpdateTimestamp(new Date());
		return save(t,false);
	}

	/**
	 * <h4>This method is used to delete the model object from database.<h4>
	 * @param t  accepts any model class which extends AbstractModel
	 * @return Boolean
	 * if true 
	 * record deleted from database
	 * else
	 * record deletion failed.
	 */
	@Transactional
	public Boolean delete(T t){

		return save(t,true);
	}


	/**
	 * <h4>This method is used to create Criteria object.</h4>
	 * @param persistentClass  accepts any model class which extends AbstractModel
	 * @return Criteria 
	 */
	private Criteria createCriteria(Class<? extends AbstractModel> persistentClass) {

		Criteria sessionCriteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(persistentClass);

		return sessionCriteria;
	}

	/**
	 * <h4>This method adds all restrictions to the Criteria object.</h4>
	 * @param criteria  accepts Criteria object
	 * @param restrictions  contains set of restricts that are going to be added to Criteria
	 */
	private void addSearchRestrictions(Criteria criteria, HashSet<SearchCriteria> restrictions){

		for(SearchCriteria restriction : restrictions){
			if(restriction.getMatchExact()){
				criteria.add(Restrictions.eq(restriction.getMatchCategory(), restriction.getMatchValue()));
			}else{
				criteria.add(Restrictions.like(restriction.getMatchCategory(), restriction.getMatchValue()));
			}
		}
	}
	
	/**
	 * <h4>This method is used to perform save or delete operation.</h4>
	 * @param t  accepts any model class which extends AbstractModel
	 * @param isDeleteOperation  it accepts true or false
	 * @return Boolean
	 * if @param isDeleteOperation true
	 * performs delete operation
	 * else
	 * performs save operation
	 */
	@Transactional
	private Boolean save(T t, Boolean isDeleteOperation) {

		try{
			if(isDeleteOperation){
				getHibernateTemplate().delete(t);
			}else{
				getHibernateTemplate().saveOrUpdate(t);
			}
			return true;
		}catch (DataIntegrityViolationException e) {
			logger.error("DataIntegrityViolationException", e);
			throw new DataIntegrityViolationException(e.getMessage());
		}
		catch (Exception e) {
			logger.error("Exception", e);
			throw new RuntimeException(e.getMessage());
		}

	}

	@Autowired
	public void init( SessionFactory sessionFactory )
	{
		setSessionFactory( sessionFactory );
	}

}
