package cn.gucci.orderServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gucci.service.OrderService;
import cn.gucci.service.impl.OrderServiceImpl;

@WebServlet("/UpdateOrderStatusServlet.do")
public class UpdateOrderStatusServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderid=request.getParameter("orderId");
		int orderId=new Integer(orderid);
		OrderService os=new OrderServiceImpl();
		boolean falg=os.updateOrderStatubyOrderId(orderId);
		String result="false";
		if(falg==true) {
			result="true";
		}
		response.getWriter().print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
