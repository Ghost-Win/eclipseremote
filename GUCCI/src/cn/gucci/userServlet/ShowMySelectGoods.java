package cn.gucci.userServlet;

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

import cn.gucci.pojo.Goods;
import cn.gucci.util.MemCached;

@WebServlet("/ShowMySelectGoods.do")
public class ShowMySelectGoods extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, Object> map=new HashMap<>();
		MemCached cache = MemCached.getInstance();
		List<Goods> list=(List<Goods>)cache.get("goodsList");
		System.out.println(list);
		map.put("list", list);
		response.getWriter().print(JSON.toJSONString(map));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
