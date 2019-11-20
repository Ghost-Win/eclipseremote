package cn.gucci.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import cn.gucci.dao.UserDao;
import cn.gucci.dao.impl.UserDaoimpl;
import cn.gucci.pojo.User;
import cn.gucci.service.UserService;

public class UserServiceImpl implements UserService{
	
	UserDao gucciDAO = new UserDaoimpl();
	
	//�û�ע��(��������)
	public int registe(User gucciDTO){
		return gucciDAO.registe(gucciDTO);
	}
	
	//��¼��֤(���˲�ѯ)
	public User loginVerify(String inputIpone,String inputPassword) {
		return gucciDAO.loginVerify(inputIpone, inputPassword);
	}
	
	//��ѯ��������
	public List<User> getAllUser() {
		return gucciDAO.getAllUser();
	}
	
	//��ҳ��ѯ�û���Ϣ
	public List<User> getUsrByPage(int currentPage, int pageSize) {
		return gucciDAO.getUserByPage(currentPage, pageSize);
	}
	
	//ɾ������
	public int deleteUser(User gucciDTO) {
		return gucciDAO.deleteUser(gucciDTO);
	}
	
	//�޸��û���Ϣ
	public int updateUser(User gucciDTO) {
		return gucciDAO.updateUser(gucciDTO);
	}
	
	//�޸�����
	public int updatePassword(User gucciDTO) {
		return gucciDAO.updatePassword(gucciDTO);
	}

	public void smsVerify(String phone, String VerifyCode){
		try {
			gucciDAO.smsVerify(phone, VerifyCode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//加密密码
	public String MD5(String key) {
		return gucciDAO.MD5(key);
	}
}