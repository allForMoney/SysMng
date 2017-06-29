package com.resourcemng.util;

import com.resourcemng.basic.MyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by 燕子 on 2017/6/20.
 */
@Component
public class FileUitl {
  //文件上传路径，在配置文件中配置
  @Value("${app.file.upload-path}")
  private  String path;

  /**
   *
   * @param uploadFile
   * @return
   * @throws MyException
   * @throws IOException
   */
 public  File saveUploadFile(MultipartFile uploadFile ) throws MyException, IOException {
    if(checkUploadFolderExit() == false){
      throw new MyException("创建上传目录失败，请联系管理员");
    }
    File file = new File(getPath()+File.separator+uploadFile.getOriginalFilename());
    uploadFile.transferTo(file);
    return file;
  }

  /**
   *
   * @return
   */
  public  boolean checkUploadFolderExit(){
    File file = new File(getPath());
    if(file.exists()){
      return true;
    }
    file.mkdirs();
    return true;

  }

  public  String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
