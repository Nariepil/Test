package com.cy.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.cy.modules.g_user.model.GUser;

/**
 * base controller类，所有controller继承该类
 * 
 * @date 2013-10-24 下午05:21:07
 * @author dongao
 * @version 1
 */
public class BaseController extends MultiActionController {
    
    private Logger logger = LoggerFactory.getLogger(BaseController.class);
    
 
	/**
	 * 当前页
	 */
	private int currentPage = 1;  
	/**
	 * 页面显示条数
	 */
	private int pageSize = 10;  

	
	/**
	 * 初始化资源文件用于显示页面的url
	 * @param model
	 */
	@ModelAttribute
	public void loadSource(Model model){
	    try{
            ResourceBundle bundle = ResourceBundle.getBundle("config/init/init",Locale.getDefault(),this.getClass().getClassLoader());
            if(bundle!=null){
                Enumeration<String> keys = bundle.getKeys();
                while(keys.hasMoreElements()){
                    String key = keys.nextElement();
                    String value = bundle.getObject(key)!=null ? bundle.getString(key):"";
                    HttpServletRequest request = getRequest();
                    if(request!=null){
                    	 request.setAttribute(key,value);
                    }else{
                    	 model.addAttribute(key, value);
                    }
                }
            }
        }catch(Exception e){
            logger.error("加载config/init.properties失败", e);
        }
	}
	
	/**
	 * 获得Request请求
	 * @return HttpServletRequest
	 */
	public HttpServletRequest getRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}


	/**
	 * 用于分页，获取当前页数
	 * @return int
	 */
	public int getCurrentPage() {
		if(getRequest().getParameter("iDisplayStart") == null || Integer.parseInt(getRequest().getParameter("iDisplayStart")) == 0){
			return currentPage;
		}else{
			return Integer.parseInt(getRequest().getParameter("iDisplayStart"))/getPageSize()+1;
		}
	}

	/**
	 * 用于分页，每页显示条数
	 * @return int
	 */
	public int getPageSize() {
		if(getRequest().getParameter("pageSize") == null){
			return pageSize;
		}else{
			return Integer.parseInt(getRequest().getParameter("pageSize"));
		}
	}
	
	/**
	 * 获取分页参数类，已初始化当前页，每页显示条数
	 * @return PageParameter
	 */
	public PageParameter getpagePageParameter(){
		PageParameter pageParameter = new PageParameter();
		pageParameter.setCurrentPage(getCurrentPage());
		pageParameter.setPageSize(getPageSize());
		
		return pageParameter;
	}

	/**
	 * 获取表中blob字段的方法
	 * @param args
	 * @throws SQLException
	 */
    public static Blob getBlob(String url,String data) throws SQLException {
    	Connection connection = null;
		String connectionURL = getConnectionURL();
		ResultSet rs = null;
		PreparedStatement psmnt = null;
		FileOutputStream fos;
		Blob image_blob = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "accpersonmanager",
					"1qaz@dongao.com");
			psmnt = connection
					.prepareStatement(url);
			psmnt.setString(1, data);
			rs=psmnt.executeQuery();
			while(rs.next()){
				image_blob=rs.getBlob("photo");
			}
			
		}
		catch (Exception ex) {
			System.out.println("Found some error : " + ex);
		} finally {
			try {
				connection.close();
				psmnt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return image_blob;
	}
    
    /**
	 * 图片从数据库中获取传入页面回显
	 * @param args
	 * @throws SQLException
	 */
    public static void showphoto(String path,Long personinfoId,String url) throws SQLException {
		Connection connection = null;
		String connectionURL = getConnectionURL();
		ResultSet rs = null;
		PreparedStatement psmnt = null;
		FileOutputStream fos;
		try {
			File imageout = new File(path);
			fos=new FileOutputStream(imageout);
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "accpersonmanager",
					"1qaz@dongao.com");
			psmnt = connection
					.prepareStatement(url);
			psmnt.setLong(1, personinfoId);
			rs=psmnt.executeQuery();
			Blob image_blob= null;
			while(rs.next()){
				image_blob=rs.getBlob("photo");
			}
			if (null != image_blob) {
				try {     
					fos.write(image_blob.getBytes(1,(int)image_blob.length()));     
				} catch (IOException e) {     
					e.printStackTrace();     
				} finally{ 
					
					fos.close();  
				}
			}else {
				fos.close();  
			}
		}
		// catch if found any exception during rum time.
		catch (Exception ex) {
			System.out.println("Found some error : " + ex);
		} finally {
			// close all the connections.
			System.out.println("Found success!");
			connection.close();
			psmnt.close();
		}
	}
    
    /**
	 * 图片接受存入数据库
	 * @param file
	 * @param personinfoId
	 * @throws SQLException
	 */
    public static void savephoto(MultipartFile mfile, Long personinfoId, File file) throws SQLException {
    	
    	Connection connection = null;
    	String connectionURL = getConnectionURL();
    	ResultSet rs = null;
    	PreparedStatement psmnt = null;
    	FileInputStream fis = null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    		
    		connection = DriverManager.getConnection(connectionURL, "accpersonmanager",
    				"1qaz@dongao.com");
    		psmnt = connection
    				.prepareStatement("insert into m_image(personinfo_id, photo) "
    						+ "values(?,?)");
    		psmnt.setLong(1, personinfoId);
//			psmnt.setString(3, null);
//			psmnt.setInt(4, 0);
	    	if (null != mfile && mfile.getSize() > 0) {
	    		CommonsMultipartFile cf= (CommonsMultipartFile)mfile; 
	    		DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
	    		File img = fi.getStoreLocation();
	    		fis = new FileInputStream(img);
	    		psmnt.setBinaryStream(2, (InputStream) fis, (int) (img.length()));
	    		int s = psmnt.executeUpdate();
	    		if (s > 0) {
	    			System.out.println("Uploaded successfully !");
	    		} else {
	    			System.out.println("unsucessfull to upload image.");
	    		}
			}
    	
	    	if (null != file && mfile.getSize() == 0) {//说明在合格证表中有图片，加载到了项目目录，获取后传入
	    		
	    		fis = new FileInputStream(file);
	    		psmnt.setBinaryStream(2, (InputStream) fis, (int) (file.length()));
	    		int s = psmnt.executeUpdate();
	    		if (s > 0) {
	    			System.out.println("Uploaded successfully !");
	    		} else {
	    			System.out.println("unsucessfull to upload image.");
	    		}
			}
		}

		// catch if found any exception during rum time.
		catch (Exception ex) {
			System.out.println("Found some error : " + ex);
		} finally {
			// close all the connections.
			connection.close();
			psmnt.close();
		}
	}

    /**
	 * 图片接受存入数据库
	 * @param file
	 * @param personinfoId
	 * @throws SQLException
	 */
    public static void savephotoCommon(MultipartFile mfile, Long personinfoId, File file,String tableName) throws SQLException {
    	
    	Connection connection = null;
    	String connectionURL = getConnectionURL();
    	ResultSet rs = null;
    	PreparedStatement psmnt = null;
    	FileInputStream fis = null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    		
    		connection = DriverManager.getConnection(connectionURL, "accpersonmanager",
    				"1qaz@dongao.com");
    		psmnt = connection
    				.prepareStatement("insert into "+tableName+"(personinfo_id, photo) "
    						+ "values(?,?)");
    		psmnt.setLong(1, personinfoId);
	    	if (null != mfile && mfile.getSize() > 0) {
	    		CommonsMultipartFile cf= (CommonsMultipartFile)mfile; 
	    		DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
	    		File img = fi.getStoreLocation();
	    		fis = new FileInputStream(img);
	    		psmnt.setBinaryStream(2, (InputStream) fis, (int) (img.length()));
	    		int s = psmnt.executeUpdate();
	    		if (s > 0) {
	    			System.out.println("Uploaded successfully !");
	    		} else {
	    			System.out.println("unsucessfull to upload image.");
	    		}
			}
    	
	    	if (null != file && mfile.getSize() == 0) {//说明在合格证表中有图片，加载到了项目目录，获取后传入
	    		
	    		fis = new FileInputStream(file);
	    		psmnt.setBinaryStream(2, (InputStream) fis, (int) (file.length()));
	    		int s = psmnt.executeUpdate();
	    		if (s > 0) {
	    			System.out.println("Uploaded successfully !");
	    		} else {
	    			System.out.println("unsucessfull to upload image.");
	    		}
			}
		}

		// catch if found any exception during rum time.
		catch (Exception ex) {
			System.out.println("Found some error : " + ex);
		} finally {
			// close all the connections.
			connection.close();
			psmnt.close();
		}
	}
	
    public static void saveAndShow(HttpServletRequest request,String tableName,Long PId){
    	try {
        	MultipartHttpServletRequest multipartRequest =(MultipartHttpServletRequest) request;
        	MultipartFile mfile = multipartRequest.getFile("photo");
        	if(mfile.getSize()!=0){
        	String murl = "select photo from "+tableName+" where personinfo_id=?";
			Blob photoBlob = getBlob(murl, String.valueOf(PId));
			String showPath = "/upload/showImg.png";
			String path = request.getRealPath("/") + showPath;//项目中暂时存放回显图片的路径
			String url = "select photo from "+tableName+" where personinfo_id=? ";
			if (null!=photoBlob && mfile.getSize() == 0) {//说明该学员证件表中有图片,并且mimage表中也存入此照片
				File file2 = new File(path);
				if (null != PId) {
					savephotoCommon(mfile,PId,file2,tableName);//将对应学员的数据存入到mImage表中
					showphoto(path,PId,url);//将存入的取出回显
					request.setAttribute("showPath", showPath);//图片存放路径传回前台回显
				}
			}else {
				File file = null;
				if (null != mfile && mfile.getSize() > 0) {//说明该学员证件表中没有图片,页面传入的mfile有值，不是从upload文件夹下取的
					
					if (null != PId) {
						try {
							savephotoCommon(mfile,PId,file,tableName);//将对应学员的数据存入到mImage表中
							showphoto(path,PId,url);
							request.setAttribute("showPath", showPath);//图片存放路径传回前台回显
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
        	}else{
        		GUser guser = RequestUtils.getUser(request);
        		String filePath = FileUploadConstants
        				.getPropValue("MPI_PHOTO_PATH");
        		String fileName = FileUploadConstants
        				.getPropValue("MPI_PHOTO_NAME");
        		String path = request.getRealPath("/") + filePath + guser.getId()
        				+ fileName;// 项目中暂时存放回显图片的路径

        		File file = new File(path);
        		savephoto(PId, file,tableName);
        		String url = "select photo from "+tableName+" where personinfo_id=? ";
        		String showPath = "/upload/showImg.png";
        		showphoto(path,PId,url);
				request.setAttribute("showPath", showPath);
        	}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    }
    
    /**
	 * 图片接受存入数据库(新增和修改)
	 * @param personinfoId
	 * @param file
	 * @throws SQLException
	 */
	public static void savephoto(Long personinfoId, File file,String Name) {
		if(file == null || !file.exists()){
			return ;
		}
		Connection connection = null;
		String connectionURL = "jdbc:mysql://"
				+ ConfigHelper.getInstance().getValue("ip_mysql_master") + ":"
				+ ConfigHelper.getInstance().getValue("port_mysql_master")
				+ "/"
				+ ConfigHelper.getInstance().getValue("name_mysql_master");
		PreparedStatement psmnt = null;
		FileInputStream fis = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			connection = DriverManager.getConnection(connectionURL,
					ConfigHelper.getInstance().getValue("username_mysql"),
					"1qaz@dongao.com");
			fis = new FileInputStream(file);
			psmnt = connection
					.prepareStatement("insert into "+Name+"(personinfo_id, photo) values(?,?)");
			psmnt.setLong(1, personinfoId);
			psmnt.setBinaryStream(2, (InputStream) fis,	(int) (file.length()));
			int s = psmnt.executeUpdate();
			if (s > 0) {
				System.out.println("Uploaded successfully !");
			} else {
				System.out.println("failed to upload image.");
			}
			
		}

		// catch if found any exception during rum time.
		catch (Exception ex) {
			System.out.println("Found some error : " + ex);
		} finally {
			try {
				fis.close();
				psmnt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    //************************************************************************************************************
    /**
      * 读取配置文件
      * @author daliu_it
      */
        public static String getConnectionURL() {
        	String connectionURL = "jdbc:mysql://"
    				+ ConfigHelper.getInstance().getValue("ip_mysql_master") + ":"
    				+ ConfigHelper.getInstance().getValue("port_mysql_master")
    				+ "/"
    				+ ConfigHelper.getInstance().getValue("name_mysql_master");
			return connectionURL;
        }
    

}
