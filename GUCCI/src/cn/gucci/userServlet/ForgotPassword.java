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

import cn.gucci.pojo.User;
import cn.gucci.service.UserService;
import cn.gucci.service.impl.UserServiceImpl;

@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ForgotPassword() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����ص���������
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			//��ȡ�ֻ������ʧȥ����ʱ��ֵ
			String phone = request.getParameter("phone");
			
			if(null != phone) {
				//�жϸ�ֵ�Ƿ�Ϊ�գ������Ϊ�������service������ȡ�������ݲ��������ƥ��
				UserService gucciServiceImpl = new UserServiceImpl();
				List<User> listGucciDTO = gucciServiceImpl.getAllUser();
				String userPhone = "";
				for(User gucciDTO:listGucciDTO) {
					userPhone = gucciDTO.getPhone();
					if(phone.equals(userPhone)) {
						//���������ֻ����������ݿ��еĺ�����һ�µ�����ʾ�û����û��Ѵ���
						resultMap.put("result", "Y");
						break;
					}else {
						//���������ֻ����������ݿ��еĺ���û��һ�µ�����û�����ע�Ტ��ʾ��ʾ���е�����
						resultMap.put("result", "N");
					}
				}
			}
			
			//��ȡ�û��������ȷ����
			String phone1 = request.getParameter("phone1");
			String pwd1 = request.getParameter("pwd1");
			
			if(null != phone1 && null != pwd1) {
				//��ȫ����֤ͨ�����ȡ�û��������ȷ���ݲ�����service��ע�᷽������ע�ᣬ�����ݼ������ݿ���
				UserService gucciServiceImpl1 = new UserServiceImpl();
				User gucciDTO1 = new User();
				
				//���û���������ݲ�Ϊ�������ע�᷽�����ע��
				gucciDTO1.setPhone(phone1);
				gucciDTO1.setPassword(gucciServiceImpl1.MD5(pwd1));
				int count = gucciServiceImpl1.updatePassword(gucciDTO1);
				if(count>0) {
					resultMap.put("forgotPassword", "OK");
				}else {
					resultMap.put("forgotPassword", "NO");
				}
			}
			
			String verification = request.getParameter("verification");
			if(null != verification) {
				int number = (int)(Math.random()*100000000);
				String number1 = number + "";
				request.getSession().setMaxInactiveInterval(60*5);
				request.getSession().setAttribute("number", number);
				
				UserService gucciServiceImpl1 = new UserServiceImpl();
				gucciServiceImpl1.smsVerify(verification, number1);
				resultMap.put("verification", "YES");
			}else {
				resultMap.put("verification", "NO");
			}
			
			String inputNumber = request.getParameter("inputNumber");
			if(null != inputNumber) {
				int number = (int)request.getSession().getAttribute("number");
				int inputNumber1 = Integer.parseInt(inputNumber);
				if(inputNumber1 == number) {
					resultMap.put("Verify", "YES");
				}else {
					resultMap.put("Verify", "NO");
				}
			}
		} catch (Exception e) {
			//�����̨�����쳣�򷵻�ǰ�˴�ӡ
			resultMap.put("error", "ע��ʧ��");
			e.printStackTrace();
		}
		//���ص��������Ϸ��ص�ǰ��
		response.getWriter().write(JSON.toJSONString(resultMap));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}