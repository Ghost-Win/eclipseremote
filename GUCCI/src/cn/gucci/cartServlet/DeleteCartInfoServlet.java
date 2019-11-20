package cn.gucci.cartServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gucci.pojo.Cart;

@WebServlet("/DeleteCartInfoServlet.do")
public class DeleteCartInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String goodsid=request.getParameter("goodsId");
		int goodsId=0;
		if(goodsid!=null && !"".equals(goodsid)) {
			goodsId=new Integer(goodsid);
		}
		List<Cart> cartList=(List<Cart>)request.getSession().getAttribute("cartList");
		for(int i=0;i<cartList.size();i++) {
			if(cartList.get(i).getGoods().getGoodsId()==goodsId) {
				cartList.remove(i);
				break;
			}
		}
		request.getSession().setAttribute("cartList", cartList);
		response.sendRedirect(request.getContextPath()+"/user/cart.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
