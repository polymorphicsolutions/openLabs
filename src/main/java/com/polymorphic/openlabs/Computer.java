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
@Table(name = "COMPUTERS")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Computer.findAll", query = "SELECT c FROM Computer c"),
  @NamedQuery(name = "Computer.findByCompid", query = "SELECT c FROM Computer c WHERE c.compid = :compid"),
  @NamedQuery(name = "Computer.findByLabid", query = "SELECT c FROM Computer c WHERE c.labid = :labid"),
  @NamedQuery(name = "Computer.findByLoggedon", query = "SELECT c FROM Computer c WHERE c.loggedon = :loggedon"),
  @NamedQuery(name = "Computer.findByMac", query = "SELECT c FROM Computer c WHERE c.mac = :mac"),
  @NamedQuery(name = "Computer.findByWin", query = "SELECT c FROM Computer c WHERE c.win = :win")})
public class Computer implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "COMPID")
  private Short compid;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 10)
  @Column(name = "LABID")
  private String labid;
  @Basic(optional = false)
  @NotNull
  @Column(name = "LOGGEDON")
  private Serializable loggedon;
  @Column(name = "MAC")
  private Serializable mac;
  @Column(name = "WIN")
  private Serializable win;

  public Computer() {
  }

  public Computer(Short compid) {
    this.compid = compid;
  }

  public Computer(Short compid, String labid, Serializable loggedon) {
    this.compid = compid;
    this.labid = labid;
    this.loggedon = loggedon;
  }

  public Short getCompid() {
    return compid;
  }

  public void setCompid(Short compid) {
    this.compid = compid;
  }

  public String getLabid() {
    return labid;
  }

  public void setLabid(String labid) {
    this.labid = labid;
  }

  public Serializable getLoggedon() {
    return loggedon;
  }

  public void setLoggedon(Serializable loggedon) {
    this.loggedon = loggedon;
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

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (compid != null ? compid.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Computer)) {
      return false;
    }
    Computer other = (Computer) object;
    if ((this.compid == null && other.compid != null) || (this.compid != null && !this.compid.equals(other.compid))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.polymorphic.openlabs.Computer[ compid=" + compid + " ]";
  }
  
}
