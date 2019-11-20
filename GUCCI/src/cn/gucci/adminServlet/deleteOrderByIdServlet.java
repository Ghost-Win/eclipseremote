package cn.gucci.adminServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.gucci.service.AdminService;
import cn.gucci.service.impl.AdminServiceImpl;


/**
 * Servlet implementation class deleteOrderByIdServlet
 */
@WebServlet("/deleteOrderByIdServlet")
public class deleteOrderByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public deleteOrderByIdServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> resultMap = new HashMap<>();
		//获取前台传来的orderId
		try {
			int orderId = new Integer(request.getParameter("orderId"));
			int status= new Integer(request.getParameter("status"));
			AdminService as = new AdminServiceImpl();
			boolean flag = as.deleteOrderById(orderId,status);
			if(flag) {
				resultMap.put("result", "ok");
			}else {
				resultMap.put("result", "fail");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			resultMap.put("error", "error");
		}
		response.getWriter().write(JSON.toJSONString(resultMap));
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
