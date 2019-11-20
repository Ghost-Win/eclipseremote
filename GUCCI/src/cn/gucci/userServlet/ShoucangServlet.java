package cn.gucci.userServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gucci.dao.impl.GoodsDaoImpl;
import cn.gucci.pojo.Goods;
import cn.gucci.service.GoodsService;
import cn.gucci.service.impl.GoodsServiceImpl;
import cn.gucci.util.MemCached;
@WebServlet("/ShoucangServlet.do")
public class ShoucangServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int goodsId=new Integer(request.getParameter("goodsId"));
		MemCached cache = MemCached.getInstance();
		List<Goods> goodsList=(List<Goods>)cache.get("goodsList");
		//判断缓存里是否有集合，如果没有就添加一个
		if(goodsList==null) {
			goodsList=new ArrayList<>();
		}
		//判断集合里是否有该商品信息，如果有就不添加，如果没有就添加
		boolean falg=false;
		for(int i=0;i<goodsList.size();i++) {
			if(goodsList.get(i).getGoodsId()==goodsId) {
				falg=true;
			}
		}
		if(falg==false) {
			//根据ID查找对象
			GoodsService gs=new GoodsServiceImpl();
			Goods goods=gs.getGoods(goodsId);
			goodsList.add(goods);
		}
		if(goodsList.size()>4) {
			goodsList.remove(0);
		}
		System.out.println(goodsList.get(0).getGoodsName());
		cache.add("goodsList", goodsList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
