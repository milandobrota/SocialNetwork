/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Comment;
import entity.Person;
import entity.Post;
import java.beans.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.ejb.Local;


/**
 *
 * @author milandobrota
 */
@Stateless
@Local(CommentDao.class)
public class CommentDaoBean extends GenericDaoBean<Comment, Integer> implements CommentDao {
    
    @EJB
    private NotificationDao notificationDao;
    
    @EJB
    private PostDao postDao;
    
    @EJB
    private PersonDao personDao;
    
    
    public boolean createComment(String text, Integer personId, Integer postId) {
        Comment comment = new Comment();
        comment.setDate(new Date());
        //comment.setPersonId(personId);
        comment.setCommenter(personDao.findById(personId));
        comment.setPostId(postId);
        comment.setText(text);
        em.persist(comment);
        
        //dropping to native query so the database takes care of race conditions; parameter is safe to pass;
        Post post = postDao.findById(postId);
        Query q = em.createNativeQuery("update posts set popularity = (popularity + 1) where id=" + post.getId() + ";");
        q.executeUpdate();
        
        Integer ownerId = post.getOwnerId();
        Integer posterId = post.getPoster().getId();
        Person commenter = personDao.findById(personId);
        if(ownerId != personId)
            notificationDao.createNotification(commenter.getFirstName() + " " + commenter.getLastName() + " commented on the post on your wall.", "wall", ownerId);
        if(posterId != personId)
            notificationDao.createNotification(commenter.getFirstName() + " " + commenter.getLastName() + " commented on the post you have written.", "wall?ownerId=" + ownerId.toString(), posterId);
        return true;
    }
  
    public List<Comment> commentsFor(Integer postId) {
        Query q = em.createQuery("SELECT c FROM Comment c JOIN FETCH c.commenter WHERE c.postId=:postId ORDER BY c.id DESC");
        q.setParameter("postId", postId);
        return q.getResultList();
    }

}

