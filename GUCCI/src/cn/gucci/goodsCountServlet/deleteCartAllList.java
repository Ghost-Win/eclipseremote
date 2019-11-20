package cn.gucci.goodsCountServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gucci.pojo.Cart;
import cn.gucci.service.GoodsService;
import cn.gucci.service.impl.GoodsServiceImpl;

@WebServlet("/deleteCartAllList.do")
public class deleteCartAllList extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cart> cartList=(List<Cart>)request.getSession().getAttribute("cartList");
		//取出集合的每一个cart类，将对应的商品的剩余数量，和月销量改变
		GoodsService gs=new GoodsServiceImpl();
		for(int i=0;i<cartList.size();i++) {
			gs.AddGoodsCount(cartList.get(i).getGoods().getGoodsId(), cartList.get(i).getBuyCount());
		}
		//再将集合放入session中
		cartList=new ArrayList<>();
		request.getSession().setAttribute("cartList", cartList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
