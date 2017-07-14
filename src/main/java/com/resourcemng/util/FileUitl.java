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
  @Value("${app.file.templete.root}")
  private String templetePath;
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

  /**
   * 获取文件的路径
   * @param fileName
   * @return
   */
  public  String getFPath(String fileName) {
    return path+ File.separator+fileName;
  }

  /**
   * 根据指定的文件名返回文件
   * @param fileName
   * @return
   */
  public  File getFile(String fileName) {
    String filePath =  path+ File.separator+fileName;
    return new File(filePath);
  }
  public  String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public File getTempleteFile(String fileName) {
    String path = this.getTempleteFilePath("downloadtemp/"+fileName);
    return new File(path);
  }
  public String getTempleteFilePath(String fileName) {
    return templetePath+File.separator+fileName;
  }
}
