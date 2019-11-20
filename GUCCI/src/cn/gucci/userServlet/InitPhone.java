package cn.gucci.userServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.gucci.pojo.User;

@WebServlet("/InitPhone")
public class InitPhone extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public InitPhone() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> resultMap = new HashMap<String, String>();
		
		try {
			User gucciDTO = (User)request.getSession().getAttribute("gucciDTO");
			
			if(null != gucciDTO) {
				String phone = gucciDTO.getPhone();
				resultMap.put("phone", phone);
				resultMap.put("result", "Y");
			}else {
				resultMap.put("result", "N");
			}
		} catch (Exception e) {
			resultMap.put("error", "出现未知错误");
;			e.getStackTrace();
		}
		response.getWriter().write(JSON.toJSONString(resultMap));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}