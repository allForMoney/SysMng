package com.resourcemng.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by 燕子 on 2017/6/20.
 */
@Entity
public class Experts {
  private String id;
  private String 代码;
  private String eName;
  private String gender;
  private String cid;
  private String telNumber;
  private String zhiCheng;
  private String xueLi;
  private String unUnit;
  private String lingYu;

  @Id
  @Column(name = "Id", nullable = false, length = 20)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Basic
  @Column(name = "代码", nullable = true, length = 20)
  public String get代码() {
    return 代码;
  }

  public void set代码(String 代码) {
    this.代码 = 代码;
  }

  @Basic
  @Column(name = "EName", nullable = true, length = 20)
  public String geteName() {
    return eName;
  }

  public void seteName(String eName) {
    this.eName = eName;
  }

  @Basic
  @Column(name = "Gender", nullable = true, length = 10)
  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @Basic
  @Column(name = "Cid", nullable = true, length = 50)
  public String getCid() {
    return cid;
  }

  public void setCid(String cid) {
    this.cid = cid;
  }

  @Basic
  @Column(name = "TelNumber", nullable = true, length = 50)
  public String getTelNumber() {
    return telNumber;
  }

  public void setTelNumber(String telNumber) {
    this.telNumber = telNumber;
  }

  @Basic
  @Column(name = "ZhiCheng", nullable = true, length = 50)
  public String getZhiCheng() {
    return zhiCheng;
  }

  public void setZhiCheng(String zhiCheng) {
    this.zhiCheng = zhiCheng;
  }

  @Basic
  @Column(name = "XueLi", nullable = true, length = 20)
  public String getXueLi() {
    return xueLi;
  }

  public void setXueLi(String xueLi) {
    this.xueLi = xueLi;
  }

  @Basic
  @Column(name = "UnUnit", nullable = true, length = 200)
  public String getUnUnit() {
    return unUnit;
  }

  public void setUnUnit(String unUnit) {
    this.unUnit = unUnit;
  }

  @Basic
  @Column(name = "LingYu", nullable = true, length = 200)
  public String getLingYu() {
    return lingYu;
  }

  public void setLingYu(String lingYu) {
    this.lingYu = lingYu;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Experts experts = (Experts) o;

    if (id != null ? !id.equals(experts.id) : experts.id != null) return false;
    if (代码 != null ? !代码.equals(experts.代码) : experts.代码 != null) return false;
    if (eName != null ? !eName.equals(experts.eName) : experts.eName != null) return false;
    if (gender != null ? !gender.equals(experts.gender) : experts.gender != null) return false;
    if (cid != null ? !cid.equals(experts.cid) : experts.cid != null) return false;
    if (telNumber != null ? !telNumber.equals(experts.telNumber) : experts.telNumber != null) return false;
    if (zhiCheng != null ? !zhiCheng.equals(experts.zhiCheng) : experts.zhiCheng != null) return false;
    if (xueLi != null ? !xueLi.equals(experts.xueLi) : experts.xueLi != null) return false;
    if (unUnit != null ? !unUnit.equals(experts.unUnit) : experts.unUnit != null) return false;
    if (lingYu != null ? !lingYu.equals(experts.lingYu) : experts.lingYu != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (代码 != null ? 代码.hashCode() : 0);
    result = 31 * result + (eName != null ? eName.hashCode() : 0);
    result = 31 * result + (gender != null ? gender.hashCode() : 0);
    result = 31 * result + (cid != null ? cid.hashCode() : 0);
    result = 31 * result + (telNumber != null ? telNumber.hashCode() : 0);
    result = 31 * result + (zhiCheng != null ? zhiCheng.hashCode() : 0);
    result = 31 * result + (xueLi != null ? xueLi.hashCode() : 0);
    result = 31 * result + (unUnit != null ? unUnit.hashCode() : 0);
    result = 31 * result + (lingYu != null ? lingYu.hashCode() : 0);
    return result;
  }
}
