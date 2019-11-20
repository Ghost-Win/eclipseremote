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

@WebServlet("/ReduceGoodsCountServlet.do")
public class ReduceGoodsCountServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String goodsid=request.getParameter("goodsId");
		int goodsId=new Integer(goodsid);
		GoodsService gs=new GoodsServiceImpl();
		int count=gs.updateReduceGoodsCount(goodsId);
		String result="false";
		if(count>0) {
			result="true";
		}
		response.getWriter().print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
