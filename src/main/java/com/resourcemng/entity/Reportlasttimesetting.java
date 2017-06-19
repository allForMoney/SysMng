package com.resourcemng.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by 燕子 on 2017/6/20.
 */
@Entity
public class Reportlasttimesetting {
  private String id;
  private String value;
  private String remark;

  @Id
  @Column(name = "Id", nullable = false, length = 20)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Basic
  @Column(name = "Value", nullable = false, length = 50)
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Basic
  @Column(name = "Remark", nullable = true, length = 50)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Reportlasttimesetting that = (Reportlasttimesetting) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (value != null ? !value.equals(that.value) : that.value != null) return false;
    if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (value != null ? value.hashCode() : 0);
    result = 31 * result + (remark != null ? remark.hashCode() : 0);
    return result;
  }
}
