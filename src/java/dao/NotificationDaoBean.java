/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Notification;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

/**
 *
 * @author milandobrota
 */
@Stateless
@Local(NotificationDao.class)
public class NotificationDaoBean extends GenericDaoBean<Notification, Integer> implements NotificationDao {

    public boolean createNotification(String text, String url, Integer personId) {
        Notification notification = new Notification();
        notification.setText(text);
        notification.setDate(new Date());
        notification.setUrl(url);
        notification.setPersonId(personId);
        em.persist(notification);
        return true;
    }

    public List<Notification> notificationsFor(Integer personId) {
        Query q = em.createQuery("SELECT n FROM Notification n WHERE n.personId=:personId ORDER BY n.date DESC");
        q.setParameter("personId", personId);
        return q.getResultList();

    }
}
