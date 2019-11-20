package cn.gucci.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.gucci.dao.UserDao;
import cn.gucci.pojo.User;
import cn.gucci.util.MD5Util;
import cn.gucci.util.SendMsgUtil;
import cn.gucci.util.Util;


public class UserDaoimpl implements UserDao{
	
	//�û�ע��(��������)
	public int registe(User gucciDTO){
		
		int i=0;//�����������ڷ��ظò���Ӱ�������
		
		//��ȡ����
		Connection connection = Util.getConnection();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet=null;
		//Ҫִ�е�sql���
		String sql="insert into user (phone,password) values (?,?);";
		try {
			prepareStatement = connection.prepareStatement(sql);
			
			//����ָ������
			prepareStatement.setString(1, gucciDTO.getPhone());
			prepareStatement.setString(2, gucciDTO.getPassword());
			
			//ִ�������ݿ��в������ݵĲ���
			i=prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			//�رոò����õ�����Ӧ��Դ
			Util.closeResource(prepareStatement, resultSet, connection);
		}
		//���ظò���Ӱ��������������ɹ��򷵻�ʵ������������ʧ�ܷ���0
		return i;
	}
	
	//��¼��֤(���˲�ѯ)
	public User loginVerify(String inputIpone,String inputPassword) {
		
		//��ȡ����
		Connection connection = Util.getConnection();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		//����һ��GucciDTO�������ڴ��ݸò����õ�������
		User gucciDTO = null;
		
		//�ò�����Ҫִ�е�sql���
		String sql="select userId,name,country,phone,createDate,sex,birthday,password from user "
				+ " where phone=? and password=?";
		try {
			prepareStatement = connection.prepareStatement(sql);
			
			//����ָ������
			prepareStatement.setString(1, inputIpone);
			prepareStatement.setString (2, inputPassword);
			
			//ִ�������ݿ��в������ݵĲ���
			resultSet=prepareStatement.executeQuery();
			
			if(resultSet.next()) {
				//�����ѯ�������� ���õ���Щ����
				int userId = resultSet.getInt("userId");
				String name = resultSet.getString("name");
				String country = resultSet.getString("country");
				String phone = resultSet.getString("phone");
				String createDate = resultSet.getString("createDate");
				String sex = resultSet.getString("sex");
				String birthday = resultSet.getString("birthday");
				String password = resultSet.getString("password");
				
				//ʵ����һ��GucciDTO����
				gucciDTO = new User();
				
				//����ѯ��������װ��ö�����
				gucciDTO.setUserId(userId);
				gucciDTO.setName(name);
				gucciDTO.setCountry(country);
				gucciDTO.setPhone(phone);
				if(null != createDate) {
					//����ѯ�����Ĵ���ʱ�䲻Ϊ�����ʽ���ô���ʱ�䣬Ϊ���򲻹�
					gucciDTO.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createDate));
				}
				gucciDTO.setSex(sex);
				if(null != birthday) {
					//����ѯ���������ղ�Ϊ�����ʽ�������գ�Ϊ���򲻹�
					gucciDTO.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
				}
				gucciDTO.setPassword(password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}finally {
			//�رոò����õ�����Ӧ��Դ
			Util.closeResource(prepareStatement, resultSet, connection);
		}
		//����������������֤ͨ���򷵻ظ��û�����Ϣ�������֤ʧ���򷵻�һ���ն���
		return gucciDTO;
	}
	
	//��ѯ�����û�����
	public List<User> getAllUser() {
		
		//����һ��װGucciDTO����ļ���
		List<User> listGucciDTO = new ArrayList<User>();
		
		//����һ��GucciDTO�������ڴ��ݸò����õ�������
		User gucciDTO = null;
		
		//��ȡ����
		Connection connection = Util.getConnection();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		//�ò�����Ҫִ�е�sql���
		String sql="select userId,name,country,phone,createDate,sex,birthday,pwd from user";
		try {
			prepareStatement = connection.prepareStatement(sql);
			
			//ִ�������ݿ��в������ݵĲ���
			resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {
				//�����ѯ�������� ���õ���Щ����
				int userId = resultSet.getInt("userId");
				String name = resultSet.getString("name");
				String country = resultSet.getString("country");
				String phone = resultSet.getString("phone");
				String createDate = resultSet.getString("createDate");
				String sex = resultSet.getString("sex");
				String birthday = resultSet.getString("birthday");
				String password = resultSet.getString("pwd");
				
				//ʵ����һ��GucciDTO����
				gucciDTO = new User();
				
				//����ѯ��������װ��ö�����
				gucciDTO.setUserId(userId);
				gucciDTO.setName(name);
				gucciDTO.setCountry(country);
				gucciDTO.setPhone(phone);
				if(null != createDate) {
					//����ѯ�����Ĵ���ʱ�䲻Ϊ�����ʽ���ô���ʱ�䣬Ϊ���򲻹�
					gucciDTO.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createDate));
				}
				gucciDTO.setSex(sex);
				if(null != birthday) {
					//����ѯ���������ղ�Ϊ�����ʽ�������գ�Ϊ���򲻹�
					gucciDTO.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
				}
				gucciDTO.setPassword(password);
				
				//***��ÿ��װ�˸�����Ϣ�Ķ���װ�뼯���У�һ�������Ӧһ���˵���Ϣ������֮��***
				listGucciDTO.add(gucciDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}finally {
			//�رոò����õ�����Ӧ��Դ
			Util.closeResource(prepareStatement, resultSet, connection);
		}
		//���ظü��ϣ�������ݿ����и�����Ϣ�򷵻���Щ�˵���Ϣ��������ݿ�ʱ�յģ���ü���ҲΪ��
		return listGucciDTO;
	}
	
	//��ҳ��ѯ�ͻ�����
	public List<User> getUserByPage(int currentPage,int pageSize) {
		
		//����һ��װGucciDTO����ļ���
		List<User> listGucciDTO=new ArrayList<User>();
		
		//��ȡ����
		Connection connection = Util.getConnection();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		//����һ��GucciDTO�������ڴ��ݸò����õ�������
		User gucciDTO = null;
		
		//�ò�����Ҫִ�е�sql���
		String sql="select userId,name,country,phone,createDate,orderId,sex,birthday,password from user where 1=1 limit ?,?";
		
		try {
			prepareStatement = connection.prepareStatement(sql);
			
			//����ָ������
			prepareStatement.setInt(1, (currentPage-1)*pageSize);
			prepareStatement.setInt(2, pageSize);
			
			//ִ�������ݿ��в������ݵĲ���
			resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {
				//�����ѯ�������� ���õ���Щ����
				int userId = resultSet.getInt("userId");
				String name = resultSet.getString("name");
				String country = resultSet.getString("country");
				String phone = resultSet.getString("phone");
				String createDate = resultSet.getString("createDate");
				int orderId = resultSet.getInt("orderId");
				String sex = resultSet.getString("sex");
				String birthday = resultSet.getString("birthday");
				String password = resultSet.getString("password");
				
				//ʵ����һ��GucciDTO����
				gucciDTO = new User();
				
				//����ѯ��������װ��ö�����
				gucciDTO.setUserId(userId);
				gucciDTO.setName(name);
				gucciDTO.setCountry(country);
				gucciDTO.setPhone(phone);
				if(null != createDate) {
					//����ѯ�����Ĵ���ʱ�䲻Ϊ�����ʽ���ô���ʱ�䣬Ϊ���򲻹�
					gucciDTO.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createDate));
				}
				gucciDTO.setOrderId(orderId);
				gucciDTO.setSex(sex);
				if(null != birthday) {
					//����ѯ���������ղ�Ϊ�����ʽ�������գ�Ϊ���򲻹�
					gucciDTO.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
				}
				gucciDTO.setPassword(password);
				
				//***��ÿ��װ�˸�����Ϣ�Ķ���װ�뼯���У�һ�������Ӧһ���˵���Ϣ������֮��***
				listGucciDTO.add(gucciDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}finally {
			//�رոò����õ�����Ӧ��Դ
			Util.closeResource(prepareStatement, resultSet, connection);
		}
		//���ظü��ϣ�������ݿ����и�����Ϣ�򷵻���Щ�˵���Ϣ��������ݿ�ʱ�յģ���ü���ҲΪ��
		return listGucciDTO;
	}
		
	//ɾ������
	public int deleteUser(User gucciDTO) {
		
		int count = 0;//�����������ڷ��ظò���Ӱ�������
		
		//��ȡ����
		Connection connection = Util.getConnection();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet=null;
		//�ò�����Ҫִ�е����
		String sql="delete from user where userId = ?";
		
		try {
			prepareStatement = connection.prepareStatement(sql);
			
			//��������
			prepareStatement.setInt(1, gucciDTO.getUserId());
			
			//ִ�д����ݿ���ɾ��ָ��������
			count = prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//�رոò����õ�����Ӧ��Դ
			Util.closeResource(prepareStatement, resultSet, connection);
		}
		//���ظò���Ӱ��������������ɹ��򷵻�ʵ������������ʧ�ܷ���0
		return count;
	}
	
	//�޸�����
	public int updateUser(User gucciDTO) {
		
		int count = 0;//�����������ڷ��ظò���Ӱ�������
		
		//��ȡ����
		Connection connection =Util.getConnection();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet=null;
		//�ò�����Ҫִ�е����
		String sql="update user set name=?,country=?,sex=?,birthday=? where userId=?";
		
		try {
			prepareStatement = connection.prepareStatement(sql);
			
			//��������
			prepareStatement.setString(1, gucciDTO.getName());
			prepareStatement.setString(2, gucciDTO.getCountry());
			prepareStatement.setString(3, gucciDTO.getSex());
			if(null != gucciDTO.getBirthday()) {
				//���û���������ղ�Ϊ�����ʽ�������գ�Ϊ���򲻹�
				prepareStatement.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(gucciDTO.getBirthday()));
			}
			prepareStatement.setInt(5, gucciDTO.getUserId());
			
			//ִ���޸����ݿ���ָ��������
			count = prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//�رոò����õ�����Ӧ��Դ
			Util.closeResource(prepareStatement, resultSet, connection);
		}
		//���ظò���Ӱ��������������ɹ��򷵻�ʵ������������ʧ�ܷ���0
		return count;
	}
	
	//�޸�����
	public int updatePassword(User gucciDTO) {
		
		int count = 0;//�����������ڷ��ظò���Ӱ�������
		
		//��ȡ����
		Connection connection = Util.getConnection();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet=null;
		//�ò�����Ҫִ�е����
		String sql="update user set password=? where phone=?";
		
		try {
			prepareStatement = connection.prepareStatement(sql);
			
			//��������
			prepareStatement.setString(1, gucciDTO.getPassword());
			prepareStatement.setString(2, gucciDTO.getPhone());
			
			//ִ���޸����ݿ���ָ��������
			count = prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//�رոò����õ�����Ӧ��Դ
			Util.closeResource(prepareStatement, resultSet, connection);
		}
		//���ظò���Ӱ��������������ɹ��򷵻�ʵ������������ʧ�ܷ���0
		return count;
	}
	
	//������֤
	public void smsVerify(String phone,String VerifyCode){
		SendMsgUtil.smsVerify(phone, VerifyCode);
	}
	
	//加密密码
	public String MD5(String key) {
		return MD5Util.MD5(key);
	}
}