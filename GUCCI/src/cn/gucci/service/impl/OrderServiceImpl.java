package cn.gucci.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import cn.gucci.dao.OrderDao;
import cn.gucci.dao.impl.OrderDaoImpl;
import cn.gucci.pojo.Order;
import cn.gucci.pojo.ShiptoAddress;
import cn.gucci.service.OrderService;
import cn.gucci.util.Util;


public class OrderServiceImpl implements OrderService {
	OrderDao od = new OrderDaoImpl();

	// 根据用户ID添加用户的收货地址
	public int insertAddress(String inputAddress, int userId) {
		return od.insertAddress(inputAddress, userId);
	}

	// 根据地址查询地址的ID
	public ShiptoAddress getAddressId(String inputAddress, int userId) {
		return od.getAddressId(inputAddress, userId);
	}

	// 添加订单信息
	public void addOrder(Order order) {
		od.addOrder(order);
	}

	// 根据ID查询订单信息
	public List<Order> getOrderLisrById(int userId) {
		String sql = "select o.userId,o.orderId,picPath,goodsName,buyCount,totalPrices ,`status` from `order` o,goods g"
				+  "  where o.goodsId=g.goodsId and userId="+userId;
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs = od.getOrderLisrById(sql);
		List<Order> list=new ArrayList<>();
		try {
			while(rs.next()) {
				Order o=new Order();
				o.setOrderId(rs.getInt("orderId"));
				o.setPicPath(rs.getString("picPath"));
				o.setGoodsName(rs.getString("goodsName"));
				o.setBuyCount(rs.getInt("buyCount"));
				o.setTotalPrices(rs.getInt("totalPrices"));
				o.setStatus(rs.getInt("status"));
				o.setUserId(rs.getInt("userId"));
				list.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Util.closeResource(ps,rs,conn);
		}
		return list;
	}

	// 根据ID修改订单状态信息
	public boolean updateOrderStatubyOrderId(int orderId) {
		return od.updateOrderStatubyOrderId(orderId);
	}
}
