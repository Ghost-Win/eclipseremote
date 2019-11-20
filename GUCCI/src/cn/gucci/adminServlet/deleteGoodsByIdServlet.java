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


/**
 * Servlet implementation class deleteGoodsByIdServlet
 */
@WebServlet("/deleteGoodsByIdServlet")
public class deleteGoodsByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public deleteGoodsByIdServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> resultMap = new HashMap<>();
		try {
			String goodsId = request.getParameter("goodsId");
			String status=request.getParameter("status");
			int statu=0;
			if(status.equals("0")) {
				statu=1;
			}
			AdminService adminService = new AdminServiceImpl();
			boolean flag = adminService.deleteGoodsById(statu,new Integer(goodsId));
			if(flag) {
				resultMap.put("result", "ok");
			}else {
				resultMap.put("result", "fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("error", "error");
		}
		response.getWriter().write(JSON.toJSONString(resultMap));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
