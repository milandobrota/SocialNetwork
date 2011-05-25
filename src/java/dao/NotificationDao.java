/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Notification;
import java.util.List;

/**
 *
 * @author milandobrota
 */
public interface NotificationDao extends GenericDao<Notification, Integer> {

    public boolean createNotification(String text, String url, Integer personId);

    public List<Notification> notificationsFor(Integer personId);
      
}
