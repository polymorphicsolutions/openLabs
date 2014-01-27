/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.polymorphic.openlabs;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wrights
 */
@Entity
@Table(name = "HALLS")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Hall.findAll", query = "SELECT h FROM Hall h"),
  @NamedQuery(name = "Hall.findByHallid", query = "SELECT h FROM Hall h WHERE h.hallid = :hallid"),
  @NamedQuery(name = "Hall.findByNumlabs", query = "SELECT h FROM Hall h WHERE h.numlabs = :numlabs")})
public class Hall implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 25)
  @Column(name = "HALLID")
  private String hallid;
  @Column(name = "NUMLABS")
  private Short numlabs;

  public Hall() {
  }

  public Hall(String hallid) {
    this.hallid = hallid;
  }

  public String getHallid() {
    return hallid;
  }

  public void setHallid(String hallid) {
    this.hallid = hallid;
  }

  public Short getNumlabs() {
    return numlabs;
  }

  public void setNumlabs(Short numlabs) {
    this.numlabs = numlabs;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (hallid != null ? hallid.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Hall)) {
      return false;
    }
    Hall other = (Hall) object;
    if ((this.hallid == null && other.hallid != null) || (this.hallid != null && !this.hallid.equals(other.hallid))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.polymorphic.openlabs.Hall[ hallid=" + hallid + " ]";
  }
  
}
