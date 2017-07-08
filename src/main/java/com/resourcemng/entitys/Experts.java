package com.resourcemng.entitys;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2017-6-23.
 */
@Entity
@Table(name = "experts")
public class Experts implements Serializable {
  private String id;
  private String code;
  private String name;
  private String gender;
  private String cid;
  private String telephoneNumber;
  private String professionalTitle;
  private String eduLevel;
  private String avoidUnit;
  private String researchField;

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
  @Column(name = "CODE", nullable = true, length = 20)
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Basic
  @Column(name = "NAME", nullable = true, length = 20)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "GENDER", nullable = true, length = 10)
  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @Basic
  @Column(name = "CID", nullable = true, length = 50)
  public String getCid() {
    return cid;
  }

  public void setCid(String cid) {
    this.cid = cid;
  }

  @Basic
  @Column(name = "TELEPHONE_NUMBER", nullable = true, length = 50)
  public String getTelephoneNumber() {
    return telephoneNumber;
  }

  public void setTelephoneNumber(String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  @Basic
  @Column(name = "PROFESSIONAL_TITLE", nullable = true, length = 50)
  public String getProfessionalTitle() {
    return professionalTitle;
  }

  public void setProfessionalTitle(String professionalTitle) {
    this.professionalTitle = professionalTitle;
  }

  @Basic
  @Column(name = "EDU_LEVEL", nullable = true, length = 20)
  public String getEduLevel() {
    return eduLevel;
  }

  public void setEduLevel(String eduLevel) {
    this.eduLevel = eduLevel;
  }

  @Basic
  @Column(name = "AVOID_UNIT", nullable = true, length = 200)
  public String getAvoidUnit() {
    return avoidUnit;
  }

  public void setAvoidUnit(String avoidUnit) {
    this.avoidUnit = avoidUnit;
  }

  @Basic
  @Column(name = "RESEARCH_FIELD", nullable = true, length = 200)
  public String getResearchField() {
    return researchField;
  }

  public void setResearchField(String researchField) {
    this.researchField = researchField;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Experts experts = (Experts) o;

    if (id != null ? !id.equals(experts.id) : experts.id != null) return false;
    if (code != null ? !code.equals(experts.code) : experts.code != null) return false;
    if (name != null ? !name.equals(experts.name) : experts.name != null) return false;
    if (gender != null ? !gender.equals(experts.gender) : experts.gender != null) return false;
    if (cid != null ? !cid.equals(experts.cid) : experts.cid != null) return false;
    if (telephoneNumber != null ? !telephoneNumber.equals(experts.telephoneNumber) : experts.telephoneNumber != null)
      return false;
    if (professionalTitle != null ? !professionalTitle.equals(experts.professionalTitle) : experts.professionalTitle != null)
      return false;
    if (eduLevel != null ? !eduLevel.equals(experts.eduLevel) : experts.eduLevel != null) return false;
    if (avoidUnit != null ? !avoidUnit.equals(experts.avoidUnit) : experts.avoidUnit != null) return false;
    if (researchField != null ? !researchField.equals(experts.researchField) : experts.researchField != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (code != null ? code.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (gender != null ? gender.hashCode() : 0);
    result = 31 * result + (cid != null ? cid.hashCode() : 0);
    result = 31 * result + (telephoneNumber != null ? telephoneNumber.hashCode() : 0);
    result = 31 * result + (professionalTitle != null ? professionalTitle.hashCode() : 0);
    result = 31 * result + (eduLevel != null ? eduLevel.hashCode() : 0);
    result = 31 * result + (avoidUnit != null ? avoidUnit.hashCode() : 0);
    result = 31 * result + (researchField != null ? researchField.hashCode() : 0);
    return result;
  }
}
