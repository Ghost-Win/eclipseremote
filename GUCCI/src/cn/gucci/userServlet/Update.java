package cn.gucci.userServlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.gucci.pojo.User;
import cn.gucci.service.UserService;
import cn.gucci.service.impl.UserServiceImpl;


@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Update() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> resultMap = new HashMap<String, String>();
		
		try {
			String sex = request.getParameter("sex");
			String name = request.getParameter("name");
			String country = request.getParameter("country");
			String birthday = request.getParameter("birthday");
			
			User gucciDTO = (User)request.getSession().getAttribute("gucciDTO");
			
			if(null != sex && null != name && null != country && null != birthday && null != gucciDTO) {
				
				UserService gucciServiceImpl = new UserServiceImpl();
				gucciDTO.setSex(sex);
				gucciDTO.setName(name);
				gucciDTO.setCountry(country);
				gucciDTO.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
				
				int count = gucciServiceImpl.updateUser(gucciDTO);
				
				if(count>0) {
					resultMap.put("result", "Y");
				}else {
					resultMap.put("result", "N");
				}
			}
		} catch (Exception e) {
			resultMap.put("error", "修改失败");
;			e.getStackTrace();
		}
		
		response.getWriter().write(JSON.toJSONString(resultMap));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}