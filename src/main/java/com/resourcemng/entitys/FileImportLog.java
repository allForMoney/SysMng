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
@Table(name = "file_import_log", schema = "budget_resource", catalog = "")
public class FileImportLog implements Serializable {
  private String id;
  private Date importDate = new Date();;
  private String fileName;
  private String importUserId;
  private String projectId;
  private String importType;

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
  @Temporal(TemporalType.TIMESTAMP)
  @org.hibernate.annotations.CreationTimestamp
  @Column(name = "IMPORT_DATE", nullable = false)
  @OrderBy("importDate ASC")
  public Date getImportDate() {
    return importDate;
  }

  public void setImportDate(Date importDate) {
    this.importDate = importDate;
  }

  @Basic
  @Column(name = "FILE_NAME", nullable = false, length = 50)
  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  @Basic
  @Column(name = "IMPORT_USER_ID", nullable = true, length = 20)
  public String getImportUserId() {
    return importUserId;
  }

  public void setImportUserId(String importUserId) {
    this.importUserId = importUserId;
  }

  @Basic
  @Column(name = "PROJECT_ID", nullable = true, length = 20)
  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  @Basic
  @Column(name = "IMPORT_TYPE", nullable = true, length = 10)
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

    FileImportLog that = (FileImportLog) o;

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
