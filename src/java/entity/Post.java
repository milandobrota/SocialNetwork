/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author milandobrota
 */
@Entity
@Table(name = "posts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p"),
    @NamedQuery(name = "Post.findById", query = "SELECT p FROM Post p WHERE p.id = :id"),
    @NamedQuery(name = "Post.findByTitle", query = "SELECT p FROM Post p WHERE p.title = :title"),
    @NamedQuery(name = "Post.findByDate", query = "SELECT p FROM Post p WHERE p.date = :date"),
    @NamedQuery(name = "Post.findByText", query = "SELECT p FROM Post p WHERE p.text = :text"),
    //@NamedQuery(name = "Post.findByPosterId", query = "SELECT p FROM Post p WHERE p.posterId = :posterId"),
    @NamedQuery(name = "Post.findByOwnerId", query = "SELECT p FROM Post p WHERE p.ownerId = :ownerId"),
    @NamedQuery(name = "Post.findByPopularity", query = "SELECT p FROM Post p WHERE p.popularity = :popularity")})
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Size(max = 255)
    @Column(name = "text")
    private String text;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "posterId")
//    private int posterId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ownerId")
    private int ownerId;
    @Column(name = "popularity")
    private Integer popularity;

    public Post() {
    }

    public Post(Integer id) {
        this.id = id;
    }

//    public Post(Integer id, int posterId, int ownerId) {
//        this.id = id;
//        this.posterId = posterId;
//        this.ownerId = ownerId;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

//    public int getPosterId() {
//        return posterId;
//    }
//
//    public void setPosterId(int posterId) {
//        this.posterId = posterId;
//    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Post[ id=" + id + " ]";
    }
    
    @ManyToOne
    @JoinColumn (name="posterId")
    private Person poster;
    
    public Person getPoster(){
        return poster;
    }
    
    public void setPoster(Person poster){
        this.poster = poster;
    }

    @OneToMany (mappedBy="post", cascade=CascadeType.PERSIST)
    private Set<Picture> pictures = new HashSet<Picture>();

    public Set<Picture> getPictures() {
	  return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
	   this.pictures = pictures;
    }


    @OneToMany (mappedBy="post", cascade=CascadeType.PERSIST)
    private Set<Video> videos = new HashSet<Video>();

    public Set<Video> getVideos() {
	  return videos;
    }

    public void setVideos(Set<Video> videos) {
	   this.videos = videos;
    }


    @OneToMany (mappedBy="post", cascade=CascadeType.PERSIST)
    private Set<Link> links = new HashSet<Link>();

    public Set<Link> getLinks() {
	  return links;
    }

    public void setLinks(Set<Link> links) {
	   this.links = links;
    }


    public String getFormattedDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
	return formatter.format(date);
    }
    
}
