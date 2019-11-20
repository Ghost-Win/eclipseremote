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

import cn.gucci.pojo.ShiptoAddress;
import cn.gucci.service.GoodsService;
import cn.gucci.service.impl.GoodsServiceImpl;

@WebServlet("/AddressManageServlet.do")
public class AddressManageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, Object> map=new HashMap<>();
		String userid=request.getParameter("userId");
		int userId=new Integer(userid);
		GoodsService gs=new GoodsServiceImpl();
		List<ShiptoAddress> list=gs.getShipAddressListByUserId(userId);
		map.put("list", list);
		response.getWriter().print(JSON.toJSONString(map));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
