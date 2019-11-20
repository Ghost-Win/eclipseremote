package cn.gucci.cartServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gucci.pojo.Cart;

@WebServlet("/UpdateCartStatuServlet.do")
public class UpdateCartStatuServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status=request.getParameter("statu");
		String goodsid=request.getParameter("goodsId");
		int statu=new Integer(status);
		int goodsId=new Integer(goodsid);
		//取出session中的购物车集合信息
		List<Cart> cartList=(List<Cart>)request.getSession().getAttribute("cartList");
		//遍历找到商品的集合,改变状态信息
		for(int i=0;i<cartList.size();i++) {
			if(cartList.get(i).getGoods().getGoodsId()==goodsId) {
				cartList.get(i).setStatu(statu);
			}
		}
		request.getSession().setAttribute("cartList", cartList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
