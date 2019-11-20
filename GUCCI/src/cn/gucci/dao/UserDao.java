package cn.gucci.dao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import cn.gucci.pojo.User;

public interface UserDao {

	// �û�ע��(��������)
	public int registe(User user);

	// ��¼��֤(���˲�ѯ)
	public User loginVerify(String inputIpone, String inputPassword);

	// ��ѯ��������
	public List<User> getAllUser();

	// ��ҳ��ѯ�ͻ�����
	public List<User> getUserByPage(int currentPage, int pageSize);

	// ɾ������
	public int deleteUser(User user);

	// �޸�����
	public int updateUser(User user);

	// �޸�����
	public int updatePassword(User user);

	// ������֤
	public void smsVerify(String phone, String VerifyCode) throws UnsupportedEncodingException, IOException;

	// 加密密码
	public String MD5(String key);
}