package com.resourcemng.view;

import com.resourcemng.entitys.LeaveMessage;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-6-22.
 */
public class LeaveMessageView extends LeaveMessage {

  String  projectNos;
  String  submitUserName;
  String  replyUserName;

  public String getProjectNos() {
    return projectNos;
  }

  public void setProjectNos(String projectNos) {
    this.projectNos = projectNos;
  }

  public String getSubmitUserName() {
    return submitUserName;
  }

  public void setSubmitUserName(String submitUserName) {
    this.submitUserName = submitUserName;
  }

  public String getReplyUserName() {
    return replyUserName;
  }

  public void setReplyUserName(String replyUserName) {
    this.replyUserName = replyUserName;
  }
}
