package cn.gucci.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.gucci.dao.GoodsDao;
import cn.gucci.pojo.Goods;
import cn.gucci.pojo.GoodsClass;
import cn.gucci.pojo.ShiptoAddress;
import cn.gucci.util.Util;

/**
 * dao实现类
 * 
 * @author Administrator
 *
 */
public class GoodsDaoImpl implements GoodsDao {

	// 查询一共有多少条记录
	public int getGoodsCount(String sql) {
		int count = 0;
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeResource(ps,rs,conn);
		}
		return count;
	}

	// 查询
	public List<Goods> getGoodsListByPage(String sql) throws SQLException {
		List<Goods> list = new ArrayList<>();
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setGoodsId(rs.getInt("goodsId"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setPrice(rs.getInt("price"));
				goods.setPicPath(rs.getString("picPath"));
				goods.setMonthSale(rs.getInt("monthSale"));
				list.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			Util.closeResource(ps,rs,conn);
		}
		return list;
	}

	// 查询所有商品分类
	public List<GoodsClass> getGoodsClassList(String sql) {
		List<GoodsClass> list = new ArrayList<>();
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				GoodsClass gc = new GoodsClass();
				gc.setGoodsClassId(rs.getInt(1));
				gc.setClassName(rs.getString("className"));
				gc.setType(rs.getInt("type"));
				list.add(gc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeResource(ps,rs,conn);
		}
		return list;
	}

	// 根据商品ID找到商品对象
	public Goods getGoods(int goodsId) {
		Goods g = new Goods();
		String sql = "select * from goods where goodsId=" + goodsId;
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				g.setGoodsClassId(rs.getInt("goodsClassId"));
				g.setGoodsCount(rs.getInt("goodsCount"));
				g.setGoodsDesc(rs.getString("goodsDesc"));
				g.setGoodsId(rs.getInt("goodsId"));
				g.setGoodsName(rs.getString("goodsName"));
				g.setMonthSale(rs.getInt("monthSale"));
				g.setPicPath(rs.getString("picPath"));
				g.setPrice(rs.getInt("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeResource(ps,rs,conn);
		}
		return g;
	}

	// 根据分类ID查询该商品的分类名称
	public GoodsClass getClassName(int goodsClassId) {
		GoodsClass gc = new GoodsClass();
		String sql = "select * from goodsclass where goodsClassId=" + goodsClassId;
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				gc.setGoodsClassId(rs.getInt("goodsClassId"));
				gc.setClassName(rs.getString("className"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeResource(ps,rs,conn);
		}
		return gc;
	}

	// 根据ID改变商品的数量,月销量
	public int updateGoodsCount(String sql) {
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			ps = conn.prepareStatement(sql);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Util.closeResource(ps,rs,conn);
		}
		return count;
	}

	// 根据ID查询该用户的所有的收货地址
	public List<ShiptoAddress> getShipAddressListByUserId(int userId) {
		List<ShiptoAddress> list = new ArrayList<>();
		String sql = "select * from shiptoaddress where userId=" + userId;
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ShiptoAddress sa = new ShiptoAddress();
				sa.setAddressId(rs.getInt("addressId"));
				sa.setAddress(rs.getString("address"));
				sa.setUserId(rs.getInt("userId"));
				list.add(sa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Util.closeResource(ps,rs,conn);
		}
		return list;
	}

	// 根据分类ID查询该商品的分类名称
	public List<GoodsClass> getClassNamebyId(int goodsClassId) {
		List<GoodsClass> list=new ArrayList<>();
		String sql = "select * from goodsclass where type=" + goodsClassId;
		Connection conn = Util.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				GoodsClass gc = new GoodsClass();
				gc.setGoodsClassId(rs.getInt("goodsClassId"));
				gc.setClassName(rs.getString("className"));
				gc.setType(rs.getInt("type"));
				list.add(gc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeResource(ps,rs,conn);
		}
		return list;
	}
}
