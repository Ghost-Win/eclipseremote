
package cn.gucci.userServlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
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

@WebServlet("/LoginVerifyAndQueryPersonal")
public class LoginVerifyAndQueryPersonal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginVerifyAndQueryPersonal() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����ص���������
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			//��ȡ�ֻ������ʧȥ����ʱ��ֵ
			String phone1 = request.getParameter("phone1");
			
			if(null != phone1) {
				//�жϸ�ֵ�Ƿ�Ϊ�գ������Ϊ�������service������ȡ�������ݲ��������ƥ��
				UserService gucciServiceImpl = new UserServiceImpl();
				List<User> listGucciDTO = gucciServiceImpl.getAllUser();
				String userPhone = "";
				for(User gucciDTO:listGucciDTO) {
					userPhone = gucciDTO.getPhone();
					if(phone1.equals(userPhone)) {
						//���������ֻ����������ݿ��еĺ�����һ�µ�����ʾ�û����û��Ѵ���
						resultMap.put("YES", "YES");
						break;
					}else {
						//���������ֻ����������ݿ��еĺ���û��һ�µ�����û�����ע�Ტ��ʾ��ʾ���е�����
						resultMap.put("YES", "NO");
					}
				}
			}
			
			
			//��ȡajax����ʱ���͵�����
			String phone = request.getParameter("phone");
			String pwd = request.getParameter("pwd");
			
			//����service�ĵ�¼��֤����
			UserService gucciServiceImpl = new UserServiceImpl();
			User gucciDTO = gucciServiceImpl.loginVerify(phone, gucciServiceImpl.MD5(pwd));
			
			//�ж���֤��¼�������صĶ����Ƿ�Ϊ�գ�Ϊ�����û����벻ƥ����֤ʧ�ܣ���Ϊ������֤ͨ��
			if(null != gucciDTO) {
				//��֤ͨ�� ���ö��󷵻ص�ǰ���Ա����ʹ��
				resultMap.put("gucciDTO", gucciDTO);
				
				//****************************************************
				request.getSession().setAttribute("gucciDTO", gucciDTO);
				//****************************************************
				
				//����û�ѡ���˼�ס�ң���ô�Ͳ���һ��cookie
				String remember = request.getParameter("remember");
				if(null != remember) {
					Cookie cookiePhone = new Cookie("phone",URLEncoder.encode(phone,"utf-8"));
					Cookie cookiePwd = new Cookie("pwd",URLEncoder.encode(pwd,"utf-8"));
					
					cookiePhone.setMaxAge(60*60*24*30);
					cookiePwd.setMaxAge(60*60*24*30);
					
					response.addCookie(cookiePhone);
					response.addCookie(cookiePwd);
				}
				resultMap.put("result", "Y");
			}else{
				resultMap.put("result", "N");
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