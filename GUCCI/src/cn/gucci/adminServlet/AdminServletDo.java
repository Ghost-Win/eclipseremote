package cn.gucci.adminServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.gucci.pojo.Goods;
import cn.gucci.service.AdminService;
import cn.gucci.service.GoodsService;
import cn.gucci.service.impl.AdminServiceImpl;
import cn.gucci.service.impl.GoodsServiceImpl;



/**
 * Servlet implementation class adminServletDo
 */
@WebServlet("/AdminServletDo")
public class AdminServletDo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminServletDo() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> resultMap = new HashMap<>();
		String pageIx = request.getParameter("pageIndex");
		String stype=request.getParameter("type");
		int type=-1;
		if(stype!=null && stype.length()>0) {
			type=new Integer(stype);
		}
		//当前页面,初始化
		int pageIndex=1;
		if(pageIx!=null && !"".equals(pageIx)) {
			pageIndex=new Integer(pageIx);
		}
		//页容量
		int pageSize=8;
		//获得数据库中的数据总数
		AdminService as=new AdminServiceImpl();
		int totalCount=as.showGoodsCount(type);
		
		//根据页容量计算分几页
		int totalPage=0;
		if(totalCount%pageSize==0) {
			totalPage=totalCount/pageSize;
		}else {
			totalPage=totalCount/pageSize+1;
		}
		if(pageIndex>totalPage) {
			pageIndex=totalPage;
		}
		//从数据库中调取商品对象放入集合
		List<Goods> list=as.getGoodsListByPage(pageIndex, pageSize,type);
		//将获得的集合存入响应结果集,并将当前页码和总页码一起返回前台
		resultMap.put("list", list);
		resultMap.put("pageIndex",pageIndex);
		resultMap.put("totalPage",totalPage);
		resultMap.put("type",type);
		response.getWriter().write(JSON.toJSONString(resultMap));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
