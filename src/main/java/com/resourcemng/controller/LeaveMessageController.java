package com.resourcemng.controller;

import com.resourcemng.Enum.LeaveMessageType;
import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.resourcemng.entitys.BudgetImportDetailNew;
import com.resourcemng.entitys.LeaveMessage;
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
   * 添加预算留言
   * @param leaveMessage
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/budget/create" ,method = RequestMethod.POST)
  @ResponseBody
  public Object budgetComment(@RequestBody LeaveMessage leaveMessage) throws Exception {
    leaveMessage.setMesType(LeaveMessageType.BUDGET);
    return leaveMessageService.comment(leaveMessage);
  }
  /**
   * 添加项目留言
   * @param leaveMessage
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/project/create" ,method = RequestMethod.POST)
  @ResponseBody
  public Object projectComment(@RequestBody LeaveMessage leaveMessage) throws Exception {
    leaveMessage.setMesType(LeaveMessageType.PROJECT);
    return leaveMessageService.comment(leaveMessage);
  }
  /**
   * 添加项目留言
   * @param leaveMessage
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/indicator/create" ,method = RequestMethod.POST)
  @ResponseBody
  public Object indicatorComment(@RequestBody LeaveMessage leaveMessage) throws Exception {
    leaveMessage.setMesType(LeaveMessageType.INDICATOR);
    return leaveMessageService.comment(leaveMessage);
  }
  /**
   * 添加留言,前台输入类型
   * @param leaveMessage
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/create" ,method = RequestMethod.POST)
  @ResponseBody
  public Object create(@RequestBody LeaveMessage leaveMessage) throws Exception {
    return leaveMessageService.comment(leaveMessage);
  }



  /**
   * 所有预算/项目留言
   * @return
   * @throws Exception
   */
    @RequestMapping(value = "/budget/all" ,method = RequestMethod.POST)
    @ResponseBody
    public Object budgetAll() throws Exception {
      return new RequestResult(ResultCode.SUCCESS, "获取成功",   this.leaveMessageService.findByType(LeaveMessageType.BUDGET));
    }


    /**
     * 所有绩效目标留言
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/indicator/all" ,method = RequestMethod.POST)
    @ResponseBody
    public Object indicatorAll() throws Exception {
      return new RequestResult(ResultCode.SUCCESS, "获取成功",   this.leaveMessageService.findByType(LeaveMessageType.INDICATOR));
    }
  /**
   * 留言回复
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/reply" ,method = RequestMethod.POST)
  @ResponseBody
  public Object replyCommont(@RequestParam String id,@RequestParam String content,@RequestParam String userId) throws Exception {
    return new RequestResult(ResultCode.SUCCESS, "获取成功",  this.leaveMessageService.replyCommont(id,content,userId));

  }
}
