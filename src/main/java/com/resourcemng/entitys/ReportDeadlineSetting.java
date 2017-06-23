package com.resourcemng.entitys;

import javax.persistence.*;

/**
 * Created by Administrator on 2017-6-23.
 */
@Entity
@Table(name = "report_deadline_setting", schema = "budget_resource", catalog = "")
public class ReportDeadlineSetting {
  private String id;
  private String value;
  private String remark;

  @Id
  @Column(name = "ID", nullable = false, length = 50)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Basic
  @Column(name = "VALUE", nullable = false, length = 50)
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Basic
  @Column(name = "REMARK", nullable = true, length = 50)
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

    ReportDeadlineSetting that = (ReportDeadlineSetting) o;

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
