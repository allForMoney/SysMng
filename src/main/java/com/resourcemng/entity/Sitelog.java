package com.resourcemng.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by 燕子 on 2017/6/20.
 */
@Entity
public class Sitelog {
  private String id;
  private String url;
  private String action;
  private String controller;
  private String ipAddress;
  private Timestamp theTime;
  private String userId;

  @Id
  @Column(name = "Id", nullable = false, length = 20)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Basic
  @Column(name = "Url", nullable = false, length = 500)
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Basic
  @Column(name = "Action", nullable = true, length = 50)
  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  @Basic
  @Column(name = "Controller", nullable = true, length = 50)
  public String getController() {
    return controller;
  }

  public void setController(String controller) {
    this.controller = controller;
  }

  @Basic
  @Column(name = "IPAddress", nullable = true, length = 50)
  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  @Basic
  @Column(name = "TheTime", nullable = false)
  public Timestamp getTheTime() {
    return theTime;
  }

  public void setTheTime(Timestamp theTime) {
    this.theTime = theTime;
  }

  @Basic
  @Column(name = "UserId", nullable = true, length = 20)
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Sitelog sitelog = (Sitelog) o;

    if (id != null ? !id.equals(sitelog.id) : sitelog.id != null) return false;
    if (url != null ? !url.equals(sitelog.url) : sitelog.url != null) return false;
    if (action != null ? !action.equals(sitelog.action) : sitelog.action != null) return false;
    if (controller != null ? !controller.equals(sitelog.controller) : sitelog.controller != null) return false;
    if (ipAddress != null ? !ipAddress.equals(sitelog.ipAddress) : sitelog.ipAddress != null) return false;
    if (theTime != null ? !theTime.equals(sitelog.theTime) : sitelog.theTime != null) return false;
    if (userId != null ? !userId.equals(sitelog.userId) : sitelog.userId != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (url != null ? url.hashCode() : 0);
    result = 31 * result + (action != null ? action.hashCode() : 0);
    result = 31 * result + (controller != null ? controller.hashCode() : 0);
    result = 31 * result + (ipAddress != null ? ipAddress.hashCode() : 0);
    result = 31 * result + (theTime != null ? theTime.hashCode() : 0);
    result = 31 * result + (userId != null ? userId.hashCode() : 0);
    return result;
  }
}
