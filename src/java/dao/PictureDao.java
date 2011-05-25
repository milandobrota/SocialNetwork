/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Picture;

/**
 *
 * @author milandobrota
 */
public interface PictureDao extends GenericDao<Picture, Integer> {

    public Picture createPicture(String image, Integer postId);
  
}
