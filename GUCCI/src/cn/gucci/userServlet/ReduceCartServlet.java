package cn.gucci.userServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gucci.pojo.Cart;
import cn.gucci.pojo.Goods;
import cn.gucci.service.GoodsService;
import cn.gucci.service.impl.GoodsServiceImpl;

@WebServlet("/ReduceCartServlet.do")
public class ReduceCartServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String gId = request.getParameter("goodsId");
		List<Cart> cartList = (List<Cart>) request.getSession().getAttribute("cartList");
		if (cartList == null) {
			cartList = new ArrayList<>();
		}
		// 根据ID查询处对象，放入session 中
		int goodsId = new Integer(gId);
		// 查找集合中是否存在该ID的商品，如果没有，增加商品信息，如果有，就增加数量
		boolean falg = false;
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getGoods().getGoodsId() == goodsId) {
				cartList.get(i).setBuyCount(cartList.get(i).getBuyCount() - 1);
				cartList.get(i).setPrices(cartList.get(i).getBuyCount() * cartList.get(i).getGoods().getPrice());
				request.getSession().setAttribute("cartList", cartList);
				falg = true;
			}
		}
		if (falg == false) {
			GoodsService gs = new GoodsServiceImpl();
			Goods goods = gs.getGoods(goodsId);
			Cart cart = new Cart();
			cart.setBuyCount(1);
			cart.setGoods(goods);
			cart.setPrices(cart.getBuyCount() * goods.getPrice());
			cartList.add(cart);
			request.getSession().setAttribute("cartList", cartList);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
