package cn.gucci.userServlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.gucci.pojo.User;
import cn.gucci.service.UserService;
import cn.gucci.service.impl.UserServiceImpl;

@WebServlet("/Inti")
public class Inti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Inti() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			//��ȡ�ֻ������ʧȥ����ʱ��ֵ
			String inti = request.getParameter("inti");
				
			if(null != inti) {
				//ȡ��cookie
				Cookie[] cookies = request.getCookies(); 
				String outPhone = "";
				String outPwd = "";
				if(null != cookies && cookies.length>0) {
					for(int i = 0;i<cookies.length;i++) {
						if(cookies[i].getName().equals("phone")) {
							outPhone = URLDecoder.decode(cookies[i].getValue(), "utf-8");
						}
						if(cookies[i].getName().equals("pwd")) {
							outPwd = URLDecoder.decode(cookies[i].getValue(), "utf-8");
						}
					}
					resultMap.put("outPhone", outPhone);
					resultMap.put("outPwd", outPwd);
				}
				resultMap.put("YES", "Y");
			}else{
				resultMap.put("YES", "N");
			}
		} catch (Exception e) {
			//������쳣���������쳣���ص�ǰ��
			resultMap.put("error", "��¼ʧ��");
			e.printStackTrace();
		}
		//���ص��������Ϸ��ص�ǰ��
		response.getWriter().write(JSON.toJSONString(resultMap));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}