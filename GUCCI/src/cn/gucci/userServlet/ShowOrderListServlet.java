package cn.gucci.userServlet;

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

import cn.gucci.pojo.Order;
import cn.gucci.service.OrderService;
import cn.gucci.service.impl.OrderServiceImpl;

/**
 * Servlet implementation class ShowOrderListServlet
 */
@WebServlet("/ShowOrderListServlet.do")
public class ShowOrderListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid=request.getParameter("userId");
		Map<String, Object> map=new HashMap<>();
		int userId=new Integer(userid);
		OrderService os=new OrderServiceImpl();
		List<Order> list=os.getOrderLisrById(userId);
		
		map.put("list", list);
		response.getWriter().print(JSON.toJSONString(map));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
