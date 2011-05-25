/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Link;
import entity.Person;
import entity.Picture;
import entity.Post;
import entity.Video;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
@Local(PostDao.class)
public class PostDaoBean extends GenericDaoBean<Post, Integer> implements PostDao {
    
    @EJB
    private NotificationDao notificationDao;
    
    @EJB
    private PersonDao personDao;
    
    @EJB
    private PictureDao pictureDao;
    
    @EJB
    private VideoDao videoDao;
    
    @EJB
    private LinkDao linkDao;
  
    public boolean createPost(String title, String text, Integer posterId, Integer ownerId, String picture, String video, String link) {        
        Post post = new Post();
        post.setTitle(title);
        post.setText(text);
        //post.setPosterId(posterId);
        post.setPoster(personDao.findById(posterId));
        post.setOwnerId(ownerId);
        post.setDate(new Date());
        post.setPopularity(0);
        //em.persist(post);
        
        //post = findByAttributes(post.getTitle(), post.getText(), post.getOwnerId(), post.getDate());
        
        if(picture != null && !picture.equals("")) {
            // pictureDao.createPicture(picture, post.getId());
	    Set<Picture> pictures = new HashSet<Picture>();
	    Picture pictureEntity = new Picture();
	    pictureEntity.setImage(picture);
	    pictureEntity.setPost(post);
	    pictures.add(pictureEntity);
	    post.setPictures(pictures);
        }
        if(video != null && !video.equals("")) {
            //videoDao.createVideo(video, post.getId());
	    Set<Video> videos = new HashSet<Video>();
	    Video videoEntity = new Video();
	    videoEntity.setLink("http://youtube.com/v/" + video);
	    videoEntity.setPost(post);
	    videos.add(videoEntity);
	    post.setVideos(videos);
        }
        if(link != null && !link.equals("")) {
            // linkDao.createLink(link, post.getId());
	    Set<Link> links = new HashSet<Link>();
	    Link linkEntity = new Link();
	    linkEntity.setLink(link);
	    linkEntity.setPost(post);
	    links.add(linkEntity);
	    post.setLinks(links);
        }
	em.persist(post);

        Person poster = personDao.findById(posterId);
        notificationDao.createNotification(poster.getFirstName() + " " + poster.getLastName() + " created a new post on your wall.", "wall", ownerId);
        return true;
    }
    
    public Post findByAttributes(String title, String text, Integer ownerId, Date date) {
        Query q = em.createQuery("SELECT p FROM Post p WHERE p.title=:title AND p.text=:text AND p.ownerId=:ownerId AND p.date=:date");
        q.setParameter("title", title);
        q.setParameter("text", text);
        q.setParameter("ownerId", ownerId);
        q.setParameter("date", date);
        return (Post)q.getSingleResult();
    }

    public List<Post> topTenFor(Integer personId){
        Query q = em.createQuery("SELECT p FROM Post p WHERE p.ownerId=:personId ORDER BY p.popularity DESC");
        q.setParameter("personId", personId);
        //how to do a sql limit?
        List<Post> allResults = q.getResultList();
        List<Post> topTen = new ArrayList<Post>();
        
        int truncation = (allResults.size() < 10) ? allResults.size() : 10;
        for(int i = 0; i < truncation; i++) {
            topTen.add(allResults.get(i));
        }
        
        return topTen;
    }

    public List<Post> wallFor(Integer personId){
        Query q = em.createQuery("SELECT p FROM Post p WHERE p.ownerId=:personId ORDER BY p.date DESC");
        q.setParameter("personId", personId);
        return q.getResultList();
    }
  
}


