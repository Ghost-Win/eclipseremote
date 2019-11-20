package cn.gucci.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import cn.gucci.pojo.User;

public interface UserService {
	
	//�û�ע��(��������)
	public int registe(User gucciDTO);
	
	//��¼��֤(���˲�ѯ)
	public User loginVerify(String inputIpone,String inputPassword) ;
	
	//��ѯ��������
	public List<User> getAllUser() ;
	
	//��ҳ��ѯ�ͻ�����
	public List<User> getUsrByPage(int currentPage,int pageSize) ;
	
	//ɾ������
	public int deleteUser(User gucciDTO) ;
	
	//�޸�����
	public int updateUser(User gucciDTO) ;
	
	//�޸�����
	public int updatePassword(User gucciDTO) ;
	
	//������֤
	public void smsVerify(String phone,String VerifyCode);
	
	//加密密码
	public String MD5(String key) ;
}