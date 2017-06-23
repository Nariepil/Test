package com.cy.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created by lipeiran on 2017/3/23.
 */
public class FileUploadUtil {
    /**
     * @param request
     * @param response
     * @param htmlId
     * @return
     */
    public static String uploadFile(HttpServletRequest request, HttpServletResponse response,String htmlId){

        //文件保存目录路径
        String savePath = FileUploadConstants.getPropValue("path.upload");

        //文件保存目录URL
        String saveUrl  = "/SHOW";

        //定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        if(!extMap.containsKey(dirName)){
            return getError("目录名不正确。");
        }
        //创建文件夹
        savePath += "/toolImg/"+dirName + "/";
        saveUrl += "/toolImg/"+dirName + "/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        savePath += ymd + "/";
        saveUrl += ymd + "/";
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        //最大文件大小
        long maxSize = 3000000;

        response.setContentType("text/html; charset=UTF-8");

        if(!ServletFileUpload.isMultipartContent(request)){
            return getError("请选择文件。");
        }
        //检查目录
        File uploadDir = new File(savePath);
        if(!uploadDir.isDirectory()){
            return getError("上传目录不存在。");
        }
        //检查目录写权限
        if(!uploadDir.canWrite()){
            return getError("上传目录没有写权限。");
        }

        MultipartHttpServletRequest multipartRequest =(MultipartHttpServletRequest) request;
        MultipartFile mfile = multipartRequest.getFile(htmlId);
        CommonsMultipartFile cf= (CommonsMultipartFile)mfile;
        DiskFileItem fItem = (DiskFileItem)cf.getFileItem();
        try {
            InputStream inputFile = mfile.getInputStream();

            if (null != mfile && mfile.getSize() > 0){
                String fileName = fItem.getName();
                //检查文件大小
                if(mfile.getSize() > maxSize){
                    return getError("上传文件大小超过限制。");
                }
                //检查扩展名
                String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
                    return getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
                }

                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;

                try{
                    FileUtil.upFile(inputFile, newFileName,savePath);
                }catch(Exception e){
                    return getError("上传文件失败。");
                }

                Map obj = new HashMap();
                obj.put("error", "0");
                obj.put("url", saveUrl + newFileName);
                return JsonMapper.toJson(obj);
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return null;
    }
    private static String getError(String message) {
        Map obj = new HashMap();
        obj.put("error", "1");
        obj.put("message", message);
        return JsonMapper.toJson(obj);
    }


}
