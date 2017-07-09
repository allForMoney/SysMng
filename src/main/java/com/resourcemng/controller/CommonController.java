package com.resourcemng.controller;

import com.resourcemng.basic.MyException;
import com.resourcemng.basic.RequestResult;
import com.resourcemng.basic.ResultCode;
import com.resourcemng.entitys.Project;
import com.resourcemng.entitys.Tuser;
import com.resourcemng.service.CommonService;
import com.resourcemng.service.ProjectService;
import com.resourcemng.util.ApplicationUitl;
import com.resourcemng.util.FileUitl;
import com.resourcemng.view.DeleteProjectView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Arrays;

/**
 * @author Benjamin Winterberg
 */
@Controller
@RequestMapping("/common")
public class CommonController {
  @Autowired
  CommonService service;
  @Autowired
  FileUitl fileUitl;
  /**
   * @return
   * @throws Exception
   */
    @RequestMapping(value = "/provences" ,method = RequestMethod.POST)
    @ResponseBody
    public Object getProvences() throws Exception {
      return new RequestResult(ResultCode.SUCCESS, "创建项目成功.",   service.getPorvences());
    }

  /**
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/templete/download" ,method = RequestMethod.GET)
  @ResponseBody
  public void downloadTemplete(@RequestParam  String type, HttpServletRequest request, HttpServletResponse response) throws Exception {
  String fileName = ApplicationUitl.getTempFileNameByType(type);
    File file = fileUitl.getTempleteFile(fileName);

    if(!file.exists()){
      String errorMessage = "对不起，你下载的文件不存在";
      throw new MyException(errorMessage);
    }
    String mimeType= URLConnection.guessContentTypeFromName(file.getName());
    if(mimeType==null){
      System.out.println("mimetype is not detectable, will take default");
      mimeType = "application/octet-stream";
    }
    response.setContentType(mimeType);
    response.setContentLength((int)file.length());
    InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
    if (StringUtils.isNoneBlank(fileName)) {
      fileName = fileName;
    }
    if (ApplicationUitl.isIE(request)) {
      fileName = java.net.URLEncoder.encode(fileName, "UTF8");
    } else {
      fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
    }

    response.setHeader("content-disposition", "attachment;filename=" + fileName);
    FileCopyUtils.copy(inputStream, response.getOutputStream());

  }
}
