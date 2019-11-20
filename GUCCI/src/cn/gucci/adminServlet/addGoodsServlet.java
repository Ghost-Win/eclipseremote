package cn.gucci.adminServlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.gucci.pojo.Goods;
import cn.gucci.service.AdminService;
import cn.gucci.service.impl.AdminServiceImpl;

/**
 * Servlet implementation class addGoodsServlet
 */
@WebServlet("/addGoodsServlet")
public class addGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Goods goods=new Goods();
    public addGoodsServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		Map<String,String> resultMap = new HashMap<>();
		 
		//检查请求是否是multipart/form-data文件上传类型
        if(ServletFileUpload.isMultipartContent(request)){
        	//创建上传所需要的两个对象
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload sfu = new ServletFileUpload(factory); 

            //限制单个文件的大小
            sfu.setFileSizeMax(1024*10000);
            //限制上传的总文件大小
            sfu.setSizeMax(1024*20000);
            //创建容器来接受解析的内容
            List<FileItem> items = new ArrayList<FileItem>();
            //将上传的文件信息放入容器中
            try {
                items = sfu.parseRequest(request);
            }catch(FileUploadBase.FileSizeLimitExceededException e){
                response.getWriter().write("文件大小不能超过1Mb");
            }catch (FileUploadException e) {
                e.printStackTrace();
            }
            //遍历容器,处理解析的内容;封装两个方法，
            //一个处理普通表单域，一个处理文件的表单域
           
            for(FileItem item : items){
            	//处理普通表单域
                if(item.isFormField()){
                    handleFormField(item);
                }else{//处理文件的表单域
                    handleUploadField(item);
                }
            }
        }
        AdminService as=new AdminServiceImpl();
        as.addGoods(goods);
        response.sendRedirect(request.getContextPath()+"/admin/admin.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
//	  * 处理普通表单域
//	     * @param item
	    private void handleFormField(FileItem item) {
	        String name = item.getFieldName(); //得到表单域的name的值
	        String value = "";
	        try {
	            value = item.getString("utf-8");  //得到普通表单域的值
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        	try {
	        		 if(name.equals("goodsName")) {
	        			 goods.setGoodsName(item.getString("utf-8"));
	        		 }else if(name.equals("goodsClassId")) {
	        			 goods.setGoodsClassId(new Integer(item.getString()));
	        		 }else if(name.equals("goodsCount")) {
	        			 goods.setGoodsCount(new Integer(item.getString()));
	        		 }else if(name.equals("price")) {
	        			 goods.setPrice(new Integer(item.getString()));
	        		 }else if(name.equals("monthSale")) {
	        			 goods.setMonthSale(new Integer(item.getString()));
	        		 }else if(name.equals("goodsDesc")) {
	        			 goods.setGoodsDesc(item.getString("utf-8"));
	        		 }else if (name.equals("type")) {
	        			 goods.setType(new Integer(item.getString()));
	        		 }else if (name.equals("goodsClassId")) {
	        			 goods.setGoodsClassId(new Integer(item.getString()));
	        		 }
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        
	        //打印到控制台
	        System.out.println("fieldName:"+name+"--value:"+value);
	    }
	    /**
	     * 处理文件的表单域
	     * @param item
	     */
	    private void handleUploadField(FileItem item) {
	        String fileName = item.getName();  //得到上传文件的文件名
	        if(fileName!=null && !"".equals(fileName)){
	            //控制只能上传图片
	            if(!item.getContentType().startsWith("image")){
	                return;
	            }
	            //向控制台打印文件信息
	            System.out.println("fileName:"+fileName);
	            System.out.println("fileSize:"+item.getSize());
	            //上传文件存储路径
	            String path = this.getServletContext().getRealPath("/pic");
	            //创建子目录
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        String time = sdf.format(new Date());
	            File childDirectory = getChildDirectory(path,time);
	            //在控制台打印存放文件的路径
	            System.out.println("ha"+childDirectory.toString());
	            //保存上传的文件到服务器的指定目录
	            UUID uu=UUID.randomUUID();
	            try {
	                item.write(new File(childDirectory.toString(),uu+"_"+fileName));
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            goods.setPicPath("/pic/"+time+"/"+uu+"_"+fileName);
	        }
	    }
	    /**
	     * 按照时间创建子目录，防止一个目录中文件过多，不利于以后遍历查找
	     * @param path
	     * @return
	     */
	    private File getChildDirectory(String path,String time) {
	        //创建子目录
	        File file = new File(path,time);
	        if(!file.exists()){
	            file.mkdirs();
	        }
	        return file;
	    }
}
