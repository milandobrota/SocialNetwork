/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.FriendRequest;
import entity.Notification;
import entity.Person;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

/**
 *
 * @author milandobrota
 */
@Stateless
@Local(PersonDao.class)
public class PersonDaoBean extends GenericDaoBean<Person, Integer> implements PersonDao {
	private String Random;
    
    public Person login(String email, String password) {
        Query q = em.createQuery("SELECT p FROM Person p WHERE p.email=:email AND p.password=:password");
        q.setParameter("email", email);
        q.setParameter("password", password);
        Person person = null;
        try {
            person = (Person) q.getSingleResult();
        } catch (Exception e) {
            // handle exception here
        }
        return person;
    }

    public boolean register(String firstName, String lastName, Date dateOfBirth, boolean sex, String email, String place, String website, String education, String occupation, String employment, String picture) {
        // extra query + race condition but coulnd't catch the exception being thrown
        Query q = em.createQuery("SELECT COUNT(p) FROM Person p WHERE p.email=:email");
        q.setParameter("email", email);
        if ((Long) q.getSingleResult() > 0) {
            return false;
        }
	if (picture == null) picture = "person.gif";
        Person person = new Person();
        person.setId(null);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setDateOfBirth(dateOfBirth);
        person.setSex(sex);
        person.setEmail(email);
        person.setEducation(education);
        person.setPlace(place);
	person.setWebsite(website);
        person.setOccupation(occupation);
        person.setEmployment(employment);
        person.setPicture(picture);
        person.setPassword("" + (new Random()).nextLong());
        em.persist(person);
	emailConfirmation(person);
        return true;
    }

    public void emailConfirmation(Person person){
	    try {
		    String host = "smtp.gmail.com";
		    String from = "mbsdva@gmail.com";
		    String pass = "mreznobaziranisistemi2";
		    Properties props = System.getProperties();
		    props.put("mail.smtp.starttls.enable", "true"); // added this line
		    props.put("mail.smtp.host", host);
		    props.put("mail.smtp.user", from);
		    props.put("mail.smtp.password", pass);
		    props.put("mail.smtp.port", "587");
		    props.put("mail.smtp.auth", "true");
		    
		    String[] to = {person.getEmail()}; // added this line
		    
		    Session session = Session.getDefaultInstance(props, null);
		    MimeMessage message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(from));
		    
		    InternetAddress[] toAddress = new InternetAddress[to.length];
		    
		    // To get the array of addresses
		    for( int i=0; i < to.length; i++ ) { // changed from a while loop
			    toAddress[i] = new InternetAddress(to[i]);
		    }
		    System.out.println(Message.RecipientType.TO);
		    
		    for( int i=0; i < toAddress.length; i++) { // changed from a while loop
			    message.addRecipient(Message.RecipientType.TO, toAddress[i]);
		    }
		    message.setSubject("SocialNetwork Email Confirmation");
		    message.setText("http://localhost:8080/socialNetwork/activate?pass=" + person.getPassword());
		    Transport transport = session.getTransport("smtp");
		    transport.connect(host, from, pass);
		    transport.sendMessage(message, message.getAllRecipients());
		    transport.close();
	    } catch (MessagingException ex) {
		    Logger.getLogger(PersonDaoBean.class.getName()).log(Level.SEVERE, null, ex);
	    } 
    }
//
//  public List<Post> selectedPostsFor(Person person) {
//    // Query q = em.createQuery("SELECT p FROM posts LEFT JOIN FETCH p.comments WHERE p.id = :id");
//    // q.setParameter("id", postId);
//  }

  public List<Person> unansweredFriendRequestsFor(Integer personId) {
    Query q = em.createQuery("SELECT p FROM Person p WHERE EXISTS (SELECT f FROM FriendRequest f WHERE f.targetId=:targetId AND f.status='UNANSWERED' AND p.id=f.sourceId)");
    q.setParameter("targetId", personId);
    return q.getResultList();
  }

    public boolean updatePassword(Integer personId, String oldPassword, String newPassword, String passwordConfirmation) {
        // extra query, again try/catch fails
        Query q = em.createQuery("SELECT COUNT(p) FROM Person p WHERE p.id=:id AND p.password=:password");
        q.setParameter("id", personId);
        q.setParameter("password", oldPassword);
        if ((Long) q.getSingleResult() == 0) {
            return false;
        } else {
            Query q2 = em.createQuery("UPDATE Person p SET p.password=:password WHERE p.id=:id");
            q2.setParameter("id", personId);
            q2.setParameter("password", newPassword);
            q2.executeUpdate();
            return true;
        }
    }

  public boolean updatePersonalInformation(Integer personId, String firstName, String lastName, String dayOfBirth, String monthOfBirth, String yearOfBirth, boolean sex, String email, String place, String website, String education, String occupation, String employment, String picture) {
        Query q = em.createQuery("SELECT p FROM Person p WHERE p.id=:id");
        q.setParameter("id", personId);
        Person person = (Person) q.getSingleResult();
        
        if(firstName != null) {
            person.setFirstName(firstName);
        }
        if(lastName != null) {
            person.setLastName(lastName);
        }
        if(dayOfBirth != null && monthOfBirth != null && yearOfBirth != null) {
            person.setDateOfBirth(java.sql.Date.valueOf(yearOfBirth + "-" + monthOfBirth + "-" + dayOfBirth));
        }

	person.setSex(sex);

        if(email != null) {
            person.setEmail(email);
        }
        if(website != null) {
            person.setWebsite(website);
        }
        if(place != null) {
            person.setPlace(place);
        }
        if(education != null) {
            person.setEducation(education);
        }
        if(occupation != null) {
            person.setOccupation(occupation);
        }
        if(employment != null) {
            person.setEmployment(employment);
        }
        if(picture != null) {
            person.setPicture(picture);
        }
        
        em.persist(person);
        return true;
    }
//
    public List<Person> friendsFor(Integer personId) {
        Query q = em.createQuery("SELECT p FROM Person p WHERE EXISTS (SELECT f FROM FriendRequest f WHERE ((f.sourceId=:personId AND f.targetId=p.id) OR (f.sourceId=p.id AND f.targetId=:personId)) AND f.status='ACCEPTED')");
        q.setParameter("personId", personId);
        return q.getResultList();
    }

    public List<Person> friendsSortedByName(Integer personId, boolean isAscending) {
	String order = isAscending ? "ASC" : "DESC";
        Query q = em.createQuery("SELECT p FROM Person p WHERE EXISTS (SELECT f FROM FriendRequest f WHERE ((f.sourceId=:personId AND f.targetId=p.id) OR (f.sourceId=p.id AND f.targetId=:personId)) AND f.status='ACCEPTED') ORDER BY p.firstName " + order + ", p.lastName " + order );
        q.setParameter("personId", personId);
        return q.getResultList();
    }

    public List<Person> friendsSortedByDateOfBirth(Integer personId, boolean isAscending) {
	String order = isAscending ? "ASC" : "DESC";
        Query q = em.createQuery("SELECT p FROM Person p WHERE EXISTS (SELECT f FROM FriendRequest f WHERE ((f.sourceId=:personId AND f.targetId=p.id) OR (f.sourceId=p.id AND f.targetId=:personId)) AND f.status='ACCEPTED') ORDER BY p.dateOfBirth " + order);
        q.setParameter("personId", personId);
        return q.getResultList();
    }

    public List<Person> friendsSortedByPlace(Integer personId, boolean isAscending) {
	String order = isAscending ? "ASC" : "DESC";
        Query q = em.createQuery("SELECT p FROM Person p WHERE EXISTS (SELECT f FROM FriendRequest f WHERE ((f.sourceId=:personId AND f.targetId=p.id) OR (f.sourceId=p.id AND f.targetId=:personId)) AND f.status='ACCEPTED') ORDER BY p.place " + order);
        q.setParameter("personId", personId);
        return q.getResultList();
    }

    public List<Person> nonFriendsFor(Integer personId) {
        Query q = em.createQuery("SELECT p FROM Person p WHERE p.id<>:personId AND NOT EXISTS (SELECT f FROM FriendRequest f WHERE ((f.sourceId=:personId AND f.targetId=p.id) OR (f.sourceId=p.id AND f.targetId=:personId)) AND f.status='ACCEPTED')");
        q.setParameter("personId", personId);
        return q.getResultList();
    }
    
    public Person findById(Integer personId){
        Query q = em.createQuery("SELECT p FROM Person p WHERE p.id=:personId");
        q.setParameter("personId", personId);
        Person person = null;
        try {
            person = (Person) q.getSingleResult();
        } catch (Exception e) {
            // handle exception here
        }
        return person;
    }

    public Person findByPassword(String password){
        Query q = em.createQuery("SELECT p FROM Person p WHERE p.password=:password");
        q.setParameter("password", password);
        Person person = null;
        try {
            person = (Person) q.getSingleResult();
        } catch (Exception e) {
            // handle exception here
        }
        return person;
    }

    public List<Person> findAllSortedByName(boolean isAscending) {
	String order = isAscending ? "ASC" : "DESC";
	Query q = em.createQuery("SELECT p FROM Person p ORDER BY p.firstName " + order + ", p.lastName " + order);
	return q.getResultList();
    }

    public List<Person> findAllSortedByDateOfBirth(boolean isAscending) {
	String order = isAscending ? "ASC" : "DESC";
	Query q = em.createQuery("SELECT p FROM Person p ORDER BY p.dateOfBirth " + order);
	return q.getResultList();
    }

    public List<Person> findAllSortedByPlace(boolean isAscending) {
	String order = isAscending ? "ASC" : "DESC";
	Query q = em.createQuery("SELECT p FROM Person p ORDER BY p.place " + order);
	return q.getResultList();
    }

//
//  public List<Post> wallFor(Person person) {
//  }
 
}


