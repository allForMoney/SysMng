package com.resourcemng.entitys;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017-6-23.
 */
@Entity
@Table(name = "leave_message")
public class LeaveMessage implements Serializable {
  private String id;
  private String mesType;
  private String contents;
  private String projectId;
  private String submitUserId;
  private Date submitDate;
  private String replyUserId;
  private Date replyDate;
  private String replyContents;
  private String refFile;

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
  @Column(name = "MES_TYPE", nullable = true, length = 50)
  public String getMesType() {
    return mesType;
  }

  public void setMesType(String mesType) {
    this.mesType = mesType;
  }

  @Basic
  @Column(name = "CONTENTS", nullable = true, length = 1000)
  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  @Basic
  @Column(name = "PROJECT_ID", nullable = true, length = 50)
  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  @Basic
  @Column(name = "SUBMIT_USER_ID", nullable = true, length = 50)
  public String getSubmitUserId() {
    return submitUserId;
  }

  public void setSubmitUserId(String submitUserId) {
    this.submitUserId = submitUserId;
  }

  @Basic
  @Column(name = "SUBMIT_DATE", nullable = true)
  public Date getSubmitDate() {
    return submitDate;
  }

  public void setSubmitDate(Date submitDate) {
    this.submitDate = submitDate;
  }

  @Basic
  @Column(name = "REPLY_USER_ID", nullable = true, length = 50)
  public String getReplyUserId() {
    return replyUserId;
  }

  public void setReplyUserId(String replyUserId) {
    this.replyUserId = replyUserId;
  }

  @Basic
  @Column(name = "REPLY_DATE", nullable = true)
  public Date getReplyDate() {
    return replyDate;
  }

  public void setReplyDate(Date replyDate) {
    this.replyDate = replyDate;
  }

  @Basic
  @Column(name = "REPLY_CONTENTS", nullable = true, length = 500)
  public String getReplyContents() {
    return replyContents;
  }

  public void setReplyContents(String replyContents) {
    this.replyContents = replyContents;
  }

  @Basic
  @Column(name = "REF_FILE", nullable = true, length = 50)
  public String getRefFile() {
    return refFile;
  }

  public void setRefFile(String refFile) {
    this.refFile = refFile;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    LeaveMessage that = (LeaveMessage) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (mesType != null ? !mesType.equals(that.mesType) : that.mesType != null) return false;
    if (contents != null ? !contents.equals(that.contents) : that.contents != null) return false;
    if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
    if (submitUserId != null ? !submitUserId.equals(that.submitUserId) : that.submitUserId != null) return false;
    if (submitDate != null ? !submitDate.equals(that.submitDate) : that.submitDate != null) return false;
    if (replyUserId != null ? !replyUserId.equals(that.replyUserId) : that.replyUserId != null) return false;
    if (replyDate != null ? !replyDate.equals(that.replyDate) : that.replyDate != null) return false;
    if (replyContents != null ? !replyContents.equals(that.replyContents) : that.replyContents != null) return false;
    if (refFile != null ? !refFile.equals(that.refFile) : that.refFile != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (mesType != null ? mesType.hashCode() : 0);
    result = 31 * result + (contents != null ? contents.hashCode() : 0);
    result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
    result = 31 * result + (submitUserId != null ? submitUserId.hashCode() : 0);
    result = 31 * result + (submitDate != null ? submitDate.hashCode() : 0);
    result = 31 * result + (replyUserId != null ? replyUserId.hashCode() : 0);
    result = 31 * result + (replyDate != null ? replyDate.hashCode() : 0);
    result = 31 * result + (replyContents != null ? replyContents.hashCode() : 0);
    result = 31 * result + (refFile != null ? refFile.hashCode() : 0);
    return result;
  }
}
