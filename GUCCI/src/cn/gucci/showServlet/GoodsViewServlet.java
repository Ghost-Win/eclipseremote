package cn.gucci.showServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.gucci.pojo.Goods;
import cn.gucci.pojo.GoodsClass;
import cn.gucci.service.GoodsService;
import cn.gucci.service.impl.GoodsServiceImpl;

@WebServlet("/GoodsViewServlet.do")
public class GoodsViewServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> map =new HashMap<>();
		String goodsId=request.getParameter("goodsId");
		GoodsService gs=new GoodsServiceImpl();
		Goods goods=gs.getGoods(new Integer(goodsId));
		//获得该商品的分类编号，查找对应的分类名称
		int goodsClassId=goods.getGoodsClassId();
		GoodsClass gc=gs.getClassName(goodsClassId);
		map.put("goods", goods);
		map.put("gc", gc);
		response.getWriter().print(JSON.toJSONString(map));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
