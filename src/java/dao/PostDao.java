/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Post;
import java.util.List;

/**
 *
 * @author milandobrota
 */
public interface PostDao extends GenericDao<Post, Integer> {

    public boolean createPost(String title, String text, Integer posterId, Integer ownerId, String picture, String video, String link);

    public List<Post> topTenFor(Integer personId);

	public java.util.List<entity.Post> wallFor(java.lang.Integer personId);
  
}
