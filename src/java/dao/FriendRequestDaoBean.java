/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.FriendRequest;
import java.util.Date;
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
@Local(FriendRequestDao.class)
public class FriendRequestDaoBean extends GenericDaoBean<FriendRequest, Integer> implements FriendRequestDao {

    @EJB
    private PersonDao personDao;
  
    public boolean createFriendRequest(Integer sourceId, Integer targetId){
        //if already requested
        Query q = em.createQuery("SELECT COUNT(f) FROM FriendRequest f WHERE f.sourceId=:sourceId AND f.targetId=:targetId AND f.status <> 'DECLINED'");
        q.setParameter("sourceId", sourceId);
        q.setParameter("targetId", targetId);
        if ((Long) q.getSingleResult() > 0) {
            return false;
        }
        //this is not a mistake - cross requesting
        Query q2 = em.createQuery("SELECT COUNT(f) FROM FriendRequest f WHERE f.sourceId=:sourceId AND f.targetId=:targetId AND f.status <> 'DECLINED'");
        q2.setParameter("sourceId", targetId);
        q2.setParameter("targetId", sourceId);
        if ((Long) q2.getSingleResult() > 0) {
            Query q3 = em.createQuery("UPDATE FriendRequest f SET f.status='ACCEPTED' WHERE p.sourceId=:sourceId AND p.targetId=:targetId");
            q3.setParameter("sourceId", targetId);
            q3.setParameter("targetId", sourceId);
            q3.executeUpdate();
            return true;
        }
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setDate(new Date());
        friendRequest.setSourceId(sourceId);
        // friendRequest.setSource(personDao.findById(sourceId));
        friendRequest.setTargetId(targetId);
        friendRequest.setStatus("UNANSWERED");
        em.persist(friendRequest);
        return true;
    }
    
    public boolean acceptFriendRequest(Integer sourceId, Integer targetId) {
        Query q = em.createQuery("UPDATE FriendRequest f SET f.status='ACCEPTED' WHERE f.sourceId=:sourceId AND f.targetId=:targetId");
        q.setParameter("sourceId", sourceId);
        q.setParameter("targetId", targetId);
        q.executeUpdate();
        return true;
    }
    
    public boolean declineFriendRequest(Integer sourceId, Integer targetId) {
        Query q = em.createQuery("UPDATE FriendRequest f SET f.status='DECLINED' WHERE (f.sourceId=:sourceId AND f.targetId=:targetId) OR (f.sourceId=:targetId AND f.targetId=:sourceId)");
        q.setParameter("sourceId", sourceId);
        q.setParameter("targetId", targetId);
        q.executeUpdate();
        return true;
    }

    public boolean areFriends(Integer personId1, Integer personId2){
	    if(personId1 == null || personId2 == null) return false;
	    if(personId1.equals(personId2)) return false;
	    Query q = em.createQuery("SELECT COUNT(r) FROM FriendRequest r WHERE r.status='ACCEPTED' AND ((r.sourceId=:personId1 AND r.targetId=:personId2) OR (r.sourceId=:personId2 AND r.targetId=:personId1))");
	    q.setParameter("personId1", personId1);
	    q.setParameter("personId2", personId2);
	    if ((Long) q.getSingleResult() > 0) {
		    return true;
		    
	    } else {
		    return false;
	    }
    }

    public boolean areUnanswered(Integer personId1, Integer personId2){
	    if(personId1 == null || personId2 == null) return false;
	    if(personId1.equals(personId2)) return false;
	    Query q = em.createQuery("SELECT COUNT(r) FROM FriendRequest r WHERE r.status='UNANSWERED' AND ((r.sourceId=:personId1 AND r.targetId=:personId2) OR (r.sourceId=:personId2 AND r.targetId=:personId1))");
	    q.setParameter("personId1", personId1);
	    q.setParameter("personId2", personId2);
	    if ((Long) q.getSingleResult() > 0) {
		    return true;
		    
	    } else {
		    return false;
	    }
    }

    
//  public Admin login(String username, String password) {
//    Query q = em.createNamedQuery("loginAdmin");
//    q.setParameter("username", username);
//    q.setParameter("password", password);
//    Admin a = (Admin)q.getSingleResult();
//    return a;
//  }
//
//  public List<FriendRequest> friendRequestsSentForPerson(Person person) {
//    Query q = em.createQuery("SELECT p FROM people LEFT JOIN FETCH p.friendRequestsSent WHERE p.id = :id");
//    q.setParameter("id", person.id);
//    
//    Person p = q.getSingleResult();
//    //Post post = q.getSingleResult();
//    return p.friendRequestsSent;
//  }
//
//  public List<FriendRequest> friendRequestsReceivedForPerson(Person person) {
//    Query q = em.createQuery("SELECT p FROM people LEFT JOIN FETCH p.friendRequestsReceived WHERE p.id = :id");
//    q.setParameter("id", person.id);
//    
//    Person p = q.getSingleResult();
//    //Post post = q.getSingleResult();
//    return p.friendRequestsReceived;
//  }
//  
}


