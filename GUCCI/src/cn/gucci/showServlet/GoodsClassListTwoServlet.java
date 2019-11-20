package cn.gucci.showServlet;

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

import cn.gucci.dao.impl.GoodsDaoImpl;
import cn.gucci.pojo.GoodsClass;
import cn.gucci.service.impl.GoodsServiceImpl;

@WebServlet("/GoodsClassListTwoServlet.do")
public class GoodsClassListTwoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, Object> map = new HashMap<>();
		String goodsClassid = request.getParameter("goodsClassId");
		int goodsClassId = new Integer(goodsClassid);
		// 根据ID查询其他的子分类
		List<GoodsClass> list = new GoodsDaoImpl().getClassNamebyId(goodsClassId);

		map.put("list", list);
		response.getWriter().print(JSON.toJSONString(map));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
