package com.resourcemng.entitys;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Administrator on 2017-6-23.
 */
@Entity
public class Sitelog implements Serializable {
  private String id;
  private String url;
  private String action;
  private String controller;
  private String ip;
  private Date operateTime;
  private String userId;

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @Column(name = "ID", nullable = false, length = 50)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Basic
  @Column(name = "URL", nullable = false, length = 500)
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Basic
  @Column(name = "ACTION", nullable = true, length = 50)
  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  @Basic
  @Column(name = "CONTROLLER", nullable = true, length = 50)
  public String getController() {
    return controller;
  }

  public void setController(String controller) {
    this.controller = controller;
  }

  @Basic
  @Column(name = "IP", nullable = true, length = 50)
  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  @Basic
  @Column(name = "OPERATE_TIME", nullable = false)
  public Date getOperateTime() {
    return operateTime;
  }

  public void setOperateTime(Date operateTime) {
    this.operateTime = operateTime;
  }

  @Basic
  @Column(name = "USER_ID", nullable = true, length = 50)
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
    if (ip != null ? !ip.equals(sitelog.ip) : sitelog.ip != null) return false;
    if (operateTime != null ? !operateTime.equals(sitelog.operateTime) : sitelog.operateTime != null) return false;
    if (userId != null ? !userId.equals(sitelog.userId) : sitelog.userId != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (url != null ? url.hashCode() : 0);
    result = 31 * result + (action != null ? action.hashCode() : 0);
    result = 31 * result + (controller != null ? controller.hashCode() : 0);
    result = 31 * result + (ip != null ? ip.hashCode() : 0);
    result = 31 * result + (operateTime != null ? operateTime.hashCode() : 0);
    result = 31 * result + (userId != null ? userId.hashCode() : 0);
    return result;
  }
}
