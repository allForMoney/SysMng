package com.resourcemng.controller;

import com.resourcemng.Enum.LeaveMessageType;
import com.resourcemng.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Benjamin Winterberg
 */
@Controller
@RequestMapping("/leavemessage")
public class LeaveMessageController {
  @Autowired
  LeaveMessageService leaveMessageService;
  //文件上传保存路径

  /**
   * 添加项目
   * @return
   * @throws Exception
   */
    @RequestMapping(value = "/budget/all" ,method = RequestMethod.POST)
    @ResponseBody
    public Object create() throws Exception {
      return this.leaveMessageService.findByType(LeaveMessageType.BUDGET);
    }

  /**
   * 添加项目
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/reply/{id}" ,method = RequestMethod.POST)
  @ResponseBody
  public Object replyCommont(@PathVariable String id,@RequestParam String content,@RequestParam String userId) throws Exception {
    return this.leaveMessageService.replyCommont(id,content,userId);
  }
}
