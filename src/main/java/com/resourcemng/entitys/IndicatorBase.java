package com.resourcemng.entitys;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2017-6-23.
 */
@Entity
@Table(name = "indicator_base")
public class IndicatorBase implements Serializable {
  private String id;
  private String fileImportId;
  private String targetImplement;
  private String targetFirstYear;
  private String targetSecondYear;

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
  @Column(name = "FILE_IMPORT_ID", nullable = false, length = 50)
  public String getFileImportId() {
    return fileImportId;
  }

  public void setFileImportId(String fileImportId) {
    this.fileImportId = fileImportId;
  }

  @Basic
  @Column(name = "TARGET_IMPLEMENT", nullable = true, length = -1)
  public String getTargetImplement() {
    return targetImplement;
  }

  public void setTargetImplement(String targetImplement) {
    this.targetImplement = targetImplement;
  }

  @Basic
  @Column(name = "TARGET_FIRST_YEAR", nullable = true, length = -1)
  public String getTargetFirstYear() {
    return targetFirstYear;
  }

  public void setTargetFirstYear(String targetFirstYear) {
    this.targetFirstYear = targetFirstYear;
  }

  @Basic
  @Column(name = "TARGET_SECOND_YEAR", nullable = true, length = -1)
  public String getTargetSecondYear() {
    return targetSecondYear;
  }

  public void setTargetSecondYear(String targetSecondYear) {
    this.targetSecondYear = targetSecondYear;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    IndicatorBase that = (IndicatorBase) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (fileImportId != null ? !fileImportId.equals(that.fileImportId) : that.fileImportId != null) return false;
    if (targetImplement != null ? !targetImplement.equals(that.targetImplement) : that.targetImplement != null)
      return false;
    if (targetFirstYear != null ? !targetFirstYear.equals(that.targetFirstYear) : that.targetFirstYear != null)
      return false;
    if (targetSecondYear != null ? !targetSecondYear.equals(that.targetSecondYear) : that.targetSecondYear != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (fileImportId != null ? fileImportId.hashCode() : 0);
    result = 31 * result + (targetImplement != null ? targetImplement.hashCode() : 0);
    result = 31 * result + (targetFirstYear != null ? targetFirstYear.hashCode() : 0);
    result = 31 * result + (targetSecondYear != null ? targetSecondYear.hashCode() : 0);
    return result;
  }
}
