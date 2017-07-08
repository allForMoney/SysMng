package com.resourcemng.view;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-6-22.
 */
public class BatchDeleteView implements Serializable {

  String[]  ids;

  public String[] getIds() {
    return ids;
  }

  public void setIds(String[] ids) {
    this.ids = ids;
  }
}
