package cn.gucci.dao;

import java.sql.ResultSet;
import java.util.List;

import cn.gucci.pojo.Order;
import cn.gucci.pojo.ShiptoAddress;

/**
 * 订单dao
 * 
 * @author Administrator
 *
 */
public interface OrderDao {
	// 根据用户ID添加用户的收货地址
	public int insertAddress(String inputAddress, int userId);

	// 根据地址查询地址的ID
	public ShiptoAddress getAddressId(String inputAddress, int userId);

	// 添加订单信息
	public void addOrder(Order order);
	
	//根据ID查看所有的订单信息
	public ResultSet getOrderLisrById(String sql);
	
	//根据ID修改订单状态信息
	public boolean updateOrderStatubyOrderId(int orderId);
}
