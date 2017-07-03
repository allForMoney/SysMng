package com.resourcemng.entitys;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017-6-23.
 */
@Entity
@Table(name = "budget_adjust")
public class BudgetAdjust implements Serializable {
  private String id;
  private Date importDate;
  private String fileName;
  private String attachment1;
  private String attachment2;
  private String importUserId;
  private String projectId;

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @Column(name = "Id", nullable = false, length = 50)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Basic
  @Column(name = "ImportDate", nullable = false)
  public Date getImportDate() {
    return importDate;
  }

  public void setImportDate(Date importDate) {
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
  @Column(name = "Attachment1", nullable = true, length = 500)
  public String getAttachment1() {
    return attachment1;
  }

  public void setAttachment1(String attachment1) {
    this.attachment1 = attachment1;
  }

  @Basic
  @Column(name = "Attachment2", nullable = true, length = 500)
  public String getAttachment2() {
    return attachment2;
  }

  public void setAttachment2(String attachment2) {
    this.attachment2 = attachment2;
  }

  @Basic
  @Column(name = "ImportUserId", nullable = true, length = 50)
  public String getImportUserId() {
    return importUserId;
  }

  public void setImportUserId(String importUserId) {
    this.importUserId = importUserId;
  }

  @Basic
  @Column(name = "ProjectId", nullable = true, length = 50)
  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BudgetAdjust that = (BudgetAdjust) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (importDate != null ? !importDate.equals(that.importDate) : that.importDate != null) return false;
    if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
    if (attachment1 != null ? !attachment1.equals(that.attachment1) : that.attachment1 != null) return false;
    if (attachment2 != null ? !attachment2.equals(that.attachment2) : that.attachment2 != null) return false;
    if (importUserId != null ? !importUserId.equals(that.importUserId) : that.importUserId != null) return false;
    if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (importDate != null ? importDate.hashCode() : 0);
    result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
    result = 31 * result + (attachment1 != null ? attachment1.hashCode() : 0);
    result = 31 * result + (attachment2 != null ? attachment2.hashCode() : 0);
    result = 31 * result + (importUserId != null ? importUserId.hashCode() : 0);
    result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
    return result;
  }
}
