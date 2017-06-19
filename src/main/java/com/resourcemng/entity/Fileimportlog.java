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
public class Fileimportlog {
  private String id;
  private Timestamp importDate;
  private String fileName;
  private String importUserId;
  private String projectId;
  private String importType;

  @Id
  @Column(name = "Id", nullable = false, length = 20)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Basic
  @Column(name = "ImportDate", nullable = false)
  public Timestamp getImportDate() {
    return importDate;
  }

  public void setImportDate(Timestamp importDate) {
    this.importDate = importDate;
  }

  @Basic
  @Column(name = "FileName", nullable = false, length = 50)
  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  @Basic
  @Column(name = "ImportUserId", nullable = true, length = 20)
  public String getImportUserId() {
    return importUserId;
  }

  public void setImportUserId(String importUserId) {
    this.importUserId = importUserId;
  }

  @Basic
  @Column(name = "ProjectId", nullable = true, length = 20)
  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  @Basic
  @Column(name = "ImportType", nullable = true, length = 10)
  public String getImportType() {
    return importType;
  }

  public void setImportType(String importType) {
    this.importType = importType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Fileimportlog that = (Fileimportlog) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (importDate != null ? !importDate.equals(that.importDate) : that.importDate != null) return false;
    if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
    if (importUserId != null ? !importUserId.equals(that.importUserId) : that.importUserId != null) return false;
    if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
    if (importType != null ? !importType.equals(that.importType) : that.importType != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (importDate != null ? importDate.hashCode() : 0);
    result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
    result = 31 * result + (importUserId != null ? importUserId.hashCode() : 0);
    result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
    result = 31 * result + (importType != null ? importType.hashCode() : 0);
    return result;
  }
}
