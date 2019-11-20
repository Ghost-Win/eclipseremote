package cn.gucci.adminServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.gucci.pojo.Order;
import cn.gucci.service.AdminService;
import cn.gucci.service.impl.AdminServiceImpl;

/**
 * Servlet implementation class showOrderServlet
 */
@WebServlet("/showOrderServlet")
public class showOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public showOrderServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> resultMap = new HashMap<>();
		//取出前台传入的当前页码
		String pageIx = request.getParameter("pageIndex");
		String statu=request.getParameter("status");
		int status=-1;
		if(statu!=null && !"".equals(statu)) {
			status=new Integer(statu);
		}
		//初始化当前页码
		int pageIndex = 1;
		if(pageIx!=null && !"".equals(pageIx)) {
			//从前台取出的值为字符串，需转型
			pageIndex = new Integer(pageIx);
		}
		//初始化页容量
		int pageSize = 3;
		//获取数据库中的数据总数
		AdminService as = new AdminServiceImpl();
		int totalCount = as.showOrderCount(status);
		//根据页容量计算分几页
		int totalPage = 0;
		if(totalCount%pageSize==0) {
			totalPage = totalCount/pageSize;//刚好分够
		}else {
			totalPage = totalCount/pageSize +1;//有多余记录但未占满一页的，都加一页
		}
		if(pageIndex>totalPage) {
			pageIndex=totalPage;
		}
		//从数据库中调取订单对象放入集合
		List<Order> list = as.getOrderListByPage(pageIndex, pageSize,status);
		//将获得的集合存入响应结果集,并将当前页码和总页码一起返回前台
		resultMap.put("list", list);
		resultMap.put("pageIndex",pageIndex);
		resultMap.put("totalPage",totalPage);
		resultMap.put("status", status);
		response.getWriter().write(JSON.toJSONString(resultMap));
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
