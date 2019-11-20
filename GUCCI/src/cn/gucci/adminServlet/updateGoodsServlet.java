package cn.gucci.adminServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.gucci.service.AdminService;
import cn.gucci.service.impl.AdminServiceImpl;


@WebServlet("/updateGoodsServlet")
public class updateGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public updateGoodsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> resultMap = new HashMap<>();
		int price = new Integer(request.getParameter("price")) ;
		String goodsName = request.getParameter("goodsName");
		int goodsCount = new Integer(request.getParameter("goodsCount")) ;
		int monthSale = new Integer(request.getParameter("monthSale")) ;
		int goodsId = new Integer(request.getParameter("goodsId"));
		AdminService as = new AdminServiceImpl();
		System.out.println(goodsName);
		boolean flag = as.updateGoods(price, goodsName, goodsCount, monthSale, goodsId);
		if(flag) {
			resultMap.put("result","ok");
		}else {
			resultMap.put("result","fail");
		}
		response.getWriter().write(JSON.toJSONString(resultMap));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
