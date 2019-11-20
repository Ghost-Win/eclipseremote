package cn.gucci.service;

import java.util.List;

import cn.gucci.pojo.Order;
import cn.gucci.pojo.ShiptoAddress;

/**
 * 订单Service
 * 
 * @author Administrator
 *
 */
public interface OrderService {
	// 根据用户ID添加用户的收货地址
	public int insertAddress(String inputAddress, int userId);

	// 根据地址查询地址的ID
	public ShiptoAddress getAddressId(String inputAddress, int userId);

	// 添加订单信息
	public void addOrder(Order order);

	// 根据ID查询订单信息
	public List<Order> getOrderLisrById(int userId);

	// 根据ID修改订单状态信息
	public boolean updateOrderStatubyOrderId(int orderId);
}
