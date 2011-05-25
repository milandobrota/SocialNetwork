/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Comment;
import java.util.List;

/**
 *
 * @author milandobrota
 */

public interface CommentDao extends GenericDao<Comment, Integer> {

    public boolean createComment(String text, Integer personId, Integer postId);

    public List<Comment> commentsFor(Integer postId);

}