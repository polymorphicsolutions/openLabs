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
@Table(name = "LABS")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Lab.findAll", query = "SELECT l FROM Lab l"),
  @NamedQuery(name = "Lab.findByLabid", query = "SELECT l FROM Lab l WHERE l.labid = :labid"),
  @NamedQuery(name = "Lab.findByHallid", query = "SELECT l FROM Lab l WHERE l.hallid = :hallid"),
  @NamedQuery(name = "Lab.findByMac", query = "SELECT l FROM Lab l WHERE l.mac = :mac"),
  @NamedQuery(name = "Lab.findByWin", query = "SELECT l FROM Lab l WHERE l.win = :win"),
  @NamedQuery(name = "Lab.findByNummachines", query = "SELECT l FROM Lab l WHERE l.nummachines = :nummachines"),
  @NamedQuery(name = "Lab.findBySoftware", query = "SELECT l FROM Lab l WHERE l.software = :software")})
public class Lab implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 10)
  @Column(name = "LABID")
  private String labid;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 25)
  @Column(name = "HALLID")
  private String hallid;
  @Basic(optional = false)
  @NotNull
  @Column(name = "MAC")
  private Serializable mac;
  @Basic(optional = false)
  @NotNull
  @Column(name = "WIN")
  private Serializable win;
  @Basic(optional = false)
  @NotNull
  @Column(name = "NUMMACHINES")
  private short nummachines;
  @Size(max = 1000)
  @Column(name = "SOFTWARE")
  private String software;

  public Lab() {
  }

  public Lab(String labid) {
    this.labid = labid;
  }

  public Lab(String labid, String hallid, Serializable mac, Serializable win, short nummachines) {
    this.labid = labid;
    this.hallid = hallid;
    this.mac = mac;
    this.win = win;
    this.nummachines = nummachines;
  }

  public String getLabid() {
    return labid;
  }

  public void setLabid(String labid) {
    this.labid = labid;
  }

  public String getHallid() {
    return hallid;
  }

  public void setHallid(String hallid) {
    this.hallid = hallid;
  }

  public Serializable getMac() {
    return mac;
  }

  public void setMac(Serializable mac) {
    this.mac = mac;
  }

  public Serializable getWin() {
    return win;
  }

  public void setWin(Serializable win) {
    this.win = win;
  }

  public short getNummachines() {
    return nummachines;
  }

  public void setNummachines(short nummachines) {
    this.nummachines = nummachines;
  }

  public String getSoftware() {
    return software;
  }

  public void setSoftware(String software) {
    this.software = software;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (labid != null ? labid.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Lab)) {
      return false;
    }
    Lab other = (Lab) object;
    if ((this.labid == null && other.labid != null) || (this.labid != null && !this.labid.equals(other.labid))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.polymorphic.openlabs.Lab[ labid=" + labid + " ]";
  }
  
}
