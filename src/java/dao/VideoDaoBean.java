/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Video;
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
@Local(VideoDao.class)
public class VideoDaoBean extends GenericDaoBean<Video, Integer> implements VideoDao {

    @EJB
    private PostDao postDao;
    
    public Video createVideo(String link, Integer postId) {
        if(link == null || link.equals("")) return null;
        Video video = new Video();
        video.setLink(link);
        // video.setPostId(postId);
        video.setPost(postDao.findById(postId));
        em.persist(video);
        return video;
    }
  
//  public Admin login(String username, String password) {
//    Query q = em.createNamedQuery("loginAdmin");
//    q.setParameter("username", username);
//    q.setParameter("password", password);
//    Admin a = (Admin)q.getSingleResult();
//    return a;
//  }
  
}


