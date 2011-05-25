/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.FriendRequest;

/**
 *
 * @author milandobrota
 */
public interface FriendRequestDao extends GenericDao<FriendRequest, Integer> {

    public boolean createFriendRequest(Integer sourceId, Integer targetId);

    public boolean acceptFriendRequest(Integer sourceId, Integer targetId);

    public boolean declineFriendRequest(Integer sourceId, Integer targetId);

    public boolean areFriends(Integer personId1, Integer personId2);

    public boolean areUnanswered(Integer personId1, Integer personId2);
  
}