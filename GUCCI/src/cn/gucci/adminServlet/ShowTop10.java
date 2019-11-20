package cn.gucci.adminServlet;

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
import cn.gucci.service.AdminService;
import cn.gucci.service.impl.AdminServiceImpl;

/**
 * Servlet implementation class ShowTop10
 */
@WebServlet("/ShowTop10")
public class ShowTop10 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowTop10() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> resultMap = new HashMap<>();
		//从数据库中调取销量前十的商品放入集合
		AdminService as = new AdminServiceImpl();
		List<Goods> list = as.getGoodsListByTop10();
		//将数据返回给前台
		resultMap.put("list", list);
		response.getWriter().write(JSON.toJSONString(resultMap));
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
