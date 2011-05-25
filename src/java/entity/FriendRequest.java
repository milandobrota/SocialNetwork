/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "friendRequests")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FriendRequest.findAll", query = "SELECT f FROM FriendRequest f"),
    @NamedQuery(name = "FriendRequest.findById", query = "SELECT f FROM FriendRequest f WHERE f.id = :id"),
    //@NamedQuery(name = "FriendRequest.findByStatus", query = "SELECT f FROM FriendRequest f WHERE f.status = :status"),
    @NamedQuery(name = "FriendRequest.findByDate", query = "SELECT f FROM FriendRequest f WHERE f.date = :date"),
    @NamedQuery(name = "FriendRequest.findBySourceId", query = "SELECT f FROM FriendRequest f WHERE f.sourceId = :sourceId"),
    @NamedQuery(name = "FriendRequest.findByTargetId", query = "SELECT f FROM FriendRequest f WHERE f.targetId = :targetId")})
public class FriendRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "status")
    private String status;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sourceId")
    private int sourceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "targetId")
    private int targetId;

    public FriendRequest() {
    }

    public FriendRequest(Integer id) {
        this.id = id;
    }

    public FriendRequest(Integer id, int sourceId, int targetId) {
        this.id = id;
        this.sourceId = sourceId;
        this.targetId = targetId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSourceId() {
       return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
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
        if (!(object instanceof FriendRequest)) {
            return false;
        }
        FriendRequest other = (FriendRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FriendRequest[ id=" + id + " ]";
    }

    // @ManyToOne
    // @JoinColumn (name="sourceId")
    // private Person source;
    
    // public Person getSource(){
    //    return source;
    // }
    
    // public void setSource(Person source){
    //    this.source= source;
    //}
    
}
