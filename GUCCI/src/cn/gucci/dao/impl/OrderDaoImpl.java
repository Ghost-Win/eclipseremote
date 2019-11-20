package cn.gucci.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.gucci.dao.OrderDao;
import cn.gucci.pojo.Order;
import cn.gucci.pojo.ShiptoAddress;
import cn.gucci.util.Util;

public class OrderDaoImpl implements OrderDao {


	// 根据用户ID添加用户的收货地址
	public int insertAddress(String inputAddress, int userId) {
		int count = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "insert into shiptoaddress(address,userId) values(?,?)";
		conn = Util.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, inputAddress);
			ps.setInt(2, userId);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeResource(ps, rs, conn);
		}
		return count;
	}

	// 根据地址查询地址的ID
	public ShiptoAddress getAddressId(String inputAddress, int userId) {
		ShiptoAddress sa = new ShiptoAddress();
		String sql = "select * from shiptoaddress where address='" + inputAddress + "' and userId=" + userId;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = Util.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				sa.setAddress(rs.getString("address"));
				sa.setAddressId(rs.getInt("addressId"));
				sa.setUserId(rs.getInt("userId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeResource(ps, rs, conn);
		}
		return sa;
	}

	// 添加订单信息
	public void addOrder(Order order) {
		String sql = "insert into `order`(userId,goodsId,buyCount,totalPrices,addressId,status) values(?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = Util.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, order.getUserId());
			ps.setInt(2, order.getGoodsId());
			ps.setInt(3, order.getBuyCount());
			ps.setInt(4, order.getTotalPrices());
			ps.setInt(5, order.getAddressId());
			ps.setInt(6, 0);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeResource(ps, rs, conn);
		}
	}

	// 根据ID查看所有的订单信息
	public ResultSet getOrderLisrById(String sql) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = Util.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	// 根据ID修改订单状态信息
	public boolean updateOrderStatubyOrderId(int orderId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean falg=false;
		String sql="update `order` set status=1 where orderId="+orderId;
		conn=Util.getConnection();
		try {
			ps=conn.prepareStatement(sql);
			int count=ps.executeUpdate();
			if(count>0) {
				falg=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Util.closeResource(ps, rs, conn);
		}
		return falg;
	}
}
