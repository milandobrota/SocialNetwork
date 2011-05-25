/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Picture;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

/**
 *
 * @author milandobrota
 */

@Stateless
@Local(PictureDao.class)
public class PictureDaoBean extends GenericDaoBean<Picture, Integer> implements PictureDao {

    @EJB
    private PostDao postDao;
    
    public Picture createPicture(String image, Integer postId) {
        if(image == null || image.equals("")) return null;
        Picture picture = new Picture();
        picture.setImage(image);
        //picture.setPostId(postId);
	picture.setPost(postDao.findById(postId));
        em.persist(picture);
        return picture;
    }
  
//  public Admin login(String username, String password) {
//    Query q = em.createNamedQuery("loginAdmin");
//    q.setParameter("username", username);
//    q.setParameter("password", password);
//    Admin a = (Admin)q.getSingleResult();
//    return a;
//  }
  
}


