package cn.gucci.showServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.alibaba.fastjson.JSON;

import cn.gucci.pojo.Goods;
import cn.gucci.service.impl.GoodsServiceImpl;

@WebServlet("/ShowAllgoodsServlet.do")
public class ShowAllgoodsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, Object> map = new HashMap<>();
		try {
			GoodsServiceImpl gs = new GoodsServiceImpl();
			String pageIx = request.getParameter("pageIndex");
			String stype = request.getParameter("type");
			String goodsClassid = request.getParameter("goodsClassId");
			// 页容量
			int pageSize = 8;
			// 当前页码
			int pageIndex = 1;
			if (pageIx != null && !"".equals(pageIx)) {
				pageIndex = new Integer(pageIx);
			}
			int type = 0;
			if (stype != null && !"".equals(stype)) {
				type = new Integer(stype);
			}
			int goodsClassId = 0;
			if (goodsClassid != null && !"".equals(goodsClassid)) {
				goodsClassId = new Integer(goodsClassid);
			}
			// 计算总数,总页数
			int count = gs.getGoodsCount(type, goodsClassId);
			int pageCount = 0;
			if (count % pageSize == 0) {
				pageCount = count / pageSize;
			} else {
				pageCount = count / pageSize + 1;
			}
			if (pageIndex > pageCount) {
				pageIndex = pageCount;
			}
			// 查询信息集合
			List<Goods> list = gs.getGoodsListByPage(pageIndex, pageSize, type, goodsClassId);
			map.put("list", list);
			map.put("pageIndex", pageIndex);
			map.put("totalCount", count);
			map.put("pageCount", pageCount);
			map.put("type", type);
			map.put("goodsClassId", goodsClassId);
		} catch (Exception e) {
			map.put("error", "error");
			e.printStackTrace();
		}
		response.getWriter().println(JSON.toJSONString(map));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
