/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author milandobrota
 */
@Local
public interface GenericDao<T, ID extends Serializable> {
  
  public Class<T> getEntityType();
  public T findById(ID id);
  public List<T> findAll();
  public List<T> findBy(String query);
  public T persist(T entity);
  public T merge(T entity);
  public void remove(T entity);
  public void flush();
  public void clear();

}


