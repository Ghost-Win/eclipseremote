package cn.gucci.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.gucci.dao.AdminDao;
import cn.gucci.pojo.Goods;
import cn.gucci.pojo.GoodsClass;
import cn.gucci.pojo.Order;
import cn.gucci.util.Util;

public class AdminDaoImpl implements AdminDao{
	
	//从数据库中查询所有商品
	public List<Goods> getGoodsListByPage(String sql) {
		List<Goods> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = Util.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setGoodsId(rs.getInt("goodsId"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setPrice(rs.getInt("price"));
				goods.setPicPath(rs.getString("picPath"));
				goods.setMonthSale(rs.getInt("monthSale"));
				goods.setGoodsCount(rs.getInt("goodsCount"));
				goods.setStatus(rs.getInt("status"));
				list.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeResource(ps,rs,conn);
		}
		return list;
	}
	//从数据库中改变商品状态
	@Override
	public int deleteGoodsById(int status,int goodsId) throws Exception{
		int count = 0;
		String sql = "update goods set status=? where goodsId = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = Util.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1,status);
			ps.setInt(2,goodsId);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			Util.closeResource(ps,rs,conn);
		}
		return count;
	}
	//添加商品
	@Override
	public int addGoods(Goods goods) {
		int count = 0;
		String sql = "insert into goods"
				+ "(goodsName,goodsCount,price,picPath,monthSale,goodsDesc,goodsClassId,type,status)"
				+ "values(?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = Util.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, goods.getGoodsName());
			ps.setInt(2, goods.getGoodsCount());
			ps.setInt(3, goods.getPrice());
			ps.setString(4, goods.getPicPath());
			ps.setInt(5, goods.getMonthSale());
			ps.setString(6, goods.getGoodsDesc());
			ps.setInt(7, goods.getGoodsClassId());
			ps.setInt(8, goods.getType());
			ps.setInt(9, goods.getStatus());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Util.closeResource(ps,rs,conn);
		}
		return count;
	}
	//查询商品数据的总数
	public int showGoodsCount(int type) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count=0;
		String sql="select count(1) count from goods where 1=1";
		if(type!=-1) {
			sql+=" and type="+type;
		}
		conn=Util.getConnection();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) {
				count=rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	//修改商品（sql语句拼接字符串换行时，注意添加空格，修改多个字段用逗号隔开）
	public int updateGoods(int price,String goodsName,int goodsCount,int monthSale,int goodsId) {
			int count = 0;
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "update goods set "
					+ " price="+price
					+ ", goodsName='"+goodsName+"'"
					+ ", goodsCount="+goodsCount
					+ ", monthSale="+monthSale
					+ " where goodsId="+goodsId;
			
			try {
				conn = Util.getConnection();
				ps = conn.prepareStatement(sql);
				count = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return count;
		}
	//按分类查询商品
	@Override
	public List<Goods> getGoodsListByGoodsClass(String sql) {
		List<Goods> list = new ArrayList<Goods>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = Util.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Goods goods = new Goods();
				goods.setGoodsClassId(rs.getInt("goodsId"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setPrice(rs.getInt("price"));
				goods.setPicPath(rs.getString("picPath"));
				goods.setMonthSale(rs.getInt("monthSale"));
				list.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Util.closeResource(ps,rs,conn);
		}
		return list;
	}
//------------------------------//
	//查询所有订单
	public List<Order> getOrderListByPage(String sql){
		List<Order> list = new ArrayList<Order>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = Util.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getInt("orderId"));
				Goods g=new Goods();
				g.setPicPath(rs.getString("picPath"));
				g.setGoodsName(rs.getString("goodsName"));
				order.setGoods(g);
				order.setBuyCount(rs.getInt("buyCount"));
				order.setTotalPrices(rs.getInt("totalPrices"));
				order.setAddress(rs.getString("address"));
				order.setName(rs.getString("name"));
				order.setStatus(rs.getInt("status"));
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Util.closeResource(ps,rs,conn);
		}
		return list;
	}
	//查询订单数据的总数
		public int showOrderCount(int status){
			int count=0;
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql="select count(1) count from `order` where 1=1";
			if(status!=-1) {
				sql+=" and status="+status;
			}
			conn=Util.getConnection();
			try {
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				if(rs.next()) {
					count=rs.getInt("count");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return count;
		}
	//删除订单根据id
	public int deleteOrderById(int orderId,int status) throws Exception {	
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		if(status==0) {
			return count;
		}
		String sql = "delete from `order` where orderId = ? and status = ?";
		try {
			conn = Util.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, orderId);
			ps.setInt(2, status);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			Util.closeResource(ps,rs,conn);
		}
		return count;
	}
	
	//显示一级菜单
	public List<GoodsClass> getOneClassName(){
		List<GoodsClass> list=new ArrayList<GoodsClass>();
		String sql="select * from goodsclass where type=0";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn=Util.getConnection();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				GoodsClass gc=new GoodsClass();
				gc.setClassName(rs.getString("className"));
				gc.setType(rs.getInt("type"));
				gc.setGoodsClassId(rs.getInt("goodsClassId"));
				list.add(gc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Util.closeResource(ps,rs,conn);
		}
		
		return list;
	}
	//显示二级菜单
	public List<GoodsClass> getTwoClassName(){
		List<GoodsClass> list=new ArrayList<GoodsClass>();
		String sql="select * from goodsclass where type!=0";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn=Util.getConnection();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				GoodsClass gc=new GoodsClass();
				gc.setClassName(rs.getString("className"));
				gc.setType(rs.getInt("type"));
				gc.setGoodsClassId(rs.getInt("goodsClassId"));
				list.add(gc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Util.closeResource(ps,rs,conn);
		}
		
		return list;
	}
}
