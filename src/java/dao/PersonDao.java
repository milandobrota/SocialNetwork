/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Person;
import java.util.Date;
import java.util.List;

/**
 *
 * @author milandobrota
 */
public interface PersonDao extends GenericDao<Person, Integer> {
    
    public Person login(String email, String password);
    
    public boolean register(String firstName, String lastName, Date dateOfBirth, boolean sex, String email, String password, String place, String website, String occupation, String employment, String picture);

    public boolean updatePassword(Integer personId, String oldPassword, String newPassword, String passwordConfirmation);

    public boolean updatePersonalInformation(Integer personId, String firstName, String lastName, String dayOfBirth, String monthOfBirth, String yearOfBirth, boolean sex, String email, String place, String website, String education, String occupation, String employment, String picture);
  
    public List<Person> unansweredFriendRequestsFor(Integer personId);

    public List<Person> friendsFor(Integer personId);

    public Person findById(Integer personId);

    public List<Person> nonFriendsFor(Integer personId);

    public void emailConfirmation(Person p);

    public Person findByPassword(String password);

    public List<Person> findAllSortedByName(boolean isAscending);

    public List<Person> findAllSortedByDateOfBirth(boolean isAscending);

    public List<Person> findAllSortedByPlace(boolean isAscending);

    public List<entity.Person> friendsSortedByName(Integer personId, boolean isAscending);

    public List<entity.Person> friendsSortedByDateOfBirth(Integer personId, boolean isAscending);
    
    public List<entity.Person> friendsSortedByPlace(Integer personId, boolean isAscending);
  
}
