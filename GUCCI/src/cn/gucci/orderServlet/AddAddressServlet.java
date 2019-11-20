package cn.gucci.orderServlet;

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

import cn.gucci.pojo.Cart;
import cn.gucci.pojo.Order;
import cn.gucci.pojo.ShiptoAddress;
import cn.gucci.service.OrderService;
import cn.gucci.service.impl.OrderServiceImpl;

@WebServlet("/AddAddressServlet.do")
public class AddAddressServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, Object> map = new HashMap<>();
		OrderService os = new OrderServiceImpl();
		String sAddress = request.getParameter("selectAddress");
		String inputAddress = request.getParameter("inputAddress");
		String userid = request.getParameter("userId");
		System.out.println(userid);
		List<Cart> cartList = (List<Cart>) request.getSession().getAttribute("cartList");
		int userId = new Integer(userid);
		int goodsId = 0;
		int buyCount = 0;
		int totalPrices = 0;
		if ((inputAddress == null || "".equals(inputAddress)) && (sAddress == null || "".equals(sAddress))) {
			map.put("result", "null");
		} else if (inputAddress == null || "".equals(inputAddress)) {
			//选择购物车中已有的地址
			int addressId=0;
			if(sAddress!=null && !"".equals(sAddress)) {
				addressId=new Integer(sAddress);
			}
			for (int i = 0; i < cartList.size(); i++) {
				if (cartList.get(i).getStatu() == 1) {
					Order order = new Order();
					goodsId = cartList.get(i).getGoods().getGoodsId();
					buyCount = cartList.get(i).getBuyCount();
					totalPrices = cartList.get(i).getPrices();
					order.setAddressId(addressId);
					order.setBuyCount(buyCount);
					order.setGoodsId(goodsId);
					order.setTotalPrices(totalPrices);
					order.setUserId(userId);
					os.addOrder(order);
					cartList.remove(i);
					i--;
				}
			}
			map.put("result", "OK");
		} else {
			// 添加新地址
			int a=os.insertAddress(inputAddress, userId);
			// 取出购物车中的已选择的订单信息
			for (int i = 0; i < cartList.size(); i++) {
				if (cartList.get(i).getStatu() == 1) {
					Order order = new Order();
					goodsId = cartList.get(i).getGoods().getGoodsId();
					buyCount = cartList.get(i).getBuyCount();
					totalPrices = cartList.get(i).getPrices();
					// 根据地址和userID查询地址id
					ShiptoAddress sa=os.getAddressId(inputAddress, userId);
					int addressId=sa.getAddressId();
					System.out.println(addressId);
					//生成订单对象，将信息添加到订单表中
					order.setAddressId(addressId);
					order.setBuyCount(buyCount);
					order.setGoodsId(goodsId);
					order.setTotalPrices(totalPrices);
					order.setUserId(userId);
					if(a>0) {
						os.addOrder(order);
					}
					
					cartList.remove(i);
				}
			}
			map.put("result", "OK");
		}
		request.getSession().setAttribute("cartList", cartList);
		response.getWriter().print(JSON.toJSONString(map));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
