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
public class Leavemessage {
  private String id;
  private String 留言类型;
  private String contents;
  private String projectId;
  private String submitUserId;
  private Timestamp submitDate;
  private String replyUserId;
  private Timestamp replyDate;
  private String replyContents;
  private String referFileName;

  @Id
  @Column(name = "ID", nullable = false, length = 20)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Basic
  @Column(name = "留言类型", nullable = true, length = 50)
  public String get留言类型() {
    return 留言类型;
  }

  public void set留言类型(String 留言类型) {
    this.留言类型 = 留言类型;
  }

  @Basic
  @Column(name = "Contents", nullable = true, length = 1000)
  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
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
  @Column(name = "SubmitUserId", nullable = true, length = 20)
  public String getSubmitUserId() {
    return submitUserId;
  }

  public void setSubmitUserId(String submitUserId) {
    this.submitUserId = submitUserId;
  }

  @Basic
  @Column(name = "SubmitDate", nullable = true)
  public Timestamp getSubmitDate() {
    return submitDate;
  }

  public void setSubmitDate(Timestamp submitDate) {
    this.submitDate = submitDate;
  }

  @Basic
  @Column(name = "ReplyUserId", nullable = true, length = 20)
  public String getReplyUserId() {
    return replyUserId;
  }

  public void setReplyUserId(String replyUserId) {
    this.replyUserId = replyUserId;
  }

  @Basic
  @Column(name = "ReplyDate", nullable = true)
  public Timestamp getReplyDate() {
    return replyDate;
  }

  public void setReplyDate(Timestamp replyDate) {
    this.replyDate = replyDate;
  }

  @Basic
  @Column(name = "ReplyContents", nullable = true, length = 500)
  public String getReplyContents() {
    return replyContents;
  }

  public void setReplyContents(String replyContents) {
    this.replyContents = replyContents;
  }

  @Basic
  @Column(name = "ReferFileName", nullable = true, length = 50)
  public String getReferFileName() {
    return referFileName;
  }

  public void setReferFileName(String referFileName) {
    this.referFileName = referFileName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Leavemessage that = (Leavemessage) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (留言类型 != null ? !留言类型.equals(that.留言类型) : that.留言类型 != null) return false;
    if (contents != null ? !contents.equals(that.contents) : that.contents != null) return false;
    if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;
    if (submitUserId != null ? !submitUserId.equals(that.submitUserId) : that.submitUserId != null) return false;
    if (submitDate != null ? !submitDate.equals(that.submitDate) : that.submitDate != null) return false;
    if (replyUserId != null ? !replyUserId.equals(that.replyUserId) : that.replyUserId != null) return false;
    if (replyDate != null ? !replyDate.equals(that.replyDate) : that.replyDate != null) return false;
    if (replyContents != null ? !replyContents.equals(that.replyContents) : that.replyContents != null) return false;
    if (referFileName != null ? !referFileName.equals(that.referFileName) : that.referFileName != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (留言类型 != null ? 留言类型.hashCode() : 0);
    result = 31 * result + (contents != null ? contents.hashCode() : 0);
    result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
    result = 31 * result + (submitUserId != null ? submitUserId.hashCode() : 0);
    result = 31 * result + (submitDate != null ? submitDate.hashCode() : 0);
    result = 31 * result + (replyUserId != null ? replyUserId.hashCode() : 0);
    result = 31 * result + (replyDate != null ? replyDate.hashCode() : 0);
    result = 31 * result + (replyContents != null ? replyContents.hashCode() : 0);
    result = 31 * result + (referFileName != null ? referFileName.hashCode() : 0);
    return result;
  }
}
