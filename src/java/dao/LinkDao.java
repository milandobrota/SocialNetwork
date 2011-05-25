/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Link;

/**
 *
 * @author milandobrota
 */
public interface LinkDao extends GenericDao<Link, Integer> {
    
    public String test();

    public Link createLink(String link, Integer postId);
  
}
