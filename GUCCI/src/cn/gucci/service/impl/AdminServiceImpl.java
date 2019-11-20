package cn.gucci.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.gucci.dao.AdminDao;
import cn.gucci.dao.impl.AdminDaoImpl;
import cn.gucci.pojo.Goods;
import cn.gucci.pojo.GoodsClass;
import cn.gucci.pojo.Order;
import cn.gucci.service.AdminService;

public class AdminServiceImpl implements AdminService {
	AdminDao adminDao = new AdminDaoImpl();

	@Override
	// 分页显示所有商品
	public List<Goods> getGoodsListByPage(int pageIndex, int pageSize,int type) {
		List<Goods> list = new ArrayList<>();
		String sql = "select * from goods where 1=1 ";
		if(type!=-1) {
			sql+=" and type="+type;
		}
		sql+=" order by goodsId desc";
		sql += " limit " + (pageIndex - 1) * pageSize + "," + pageSize;
		list = adminDao.getGoodsListByPage(sql);
		return list;
	}

	// 删除商品
	@Override
	public boolean deleteGoodsById(int status, int goodsId) throws Exception {
		boolean flag = false;
		AdminDao adminDao = new AdminDaoImpl();
		int count = 0;
		try {
			count = adminDao.deleteGoodsById(status,goodsId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		if (count > 0) {
			flag = true;
		}
		return flag;
	}

	// 添加商品
	@Override
	public boolean addGoods(Goods goods) {
		boolean flag = false;
		goods.setStatus(0);
		int count = adminDao.addGoods(goods);
		if (count > 0) {
			return true;
		}
		return flag;
	}

	// 查询商品数据的总数
	public int showGoodsCount(int type) {
		return adminDao.showGoodsCount(type);
	}

	// 修改商品
	public boolean updateGoods(int price, String goodsName, int goodsCount, int monthSale, int goodsId) {
		boolean flag = false;
		int count = adminDao.updateGoods(price, goodsName, goodsCount, monthSale, goodsId);
		if (count > 0) {
			return true;
		}
		return flag;
	}

	// 按分类查询商品
	public List<Goods> getGoodsListByGoodsClass(int pageIndex, int pageSize, String className) {
		List<Goods> list = new ArrayList<>();
		String sql = "select * from goods G inner join goodsclass GC " + " on G.goodsClassId = GC.goodsClassId "
				+ " where G.goodsClassId =" + " (select goodsClassId form GC where GC.className = \"+className+\") ";

		return list;
	}

	// ------------------------------------------//
	// 查询订单数据的总数
	public int showOrderCount(int status) {
		return adminDao.showOrderCount(status);
	}

	// 分页查询所有订单
	@Override
	public List<Order> getOrderListByPage(int pageIndex, int pageSize, int status) {
		List<Order> list = new ArrayList<Order>();
		String sql = "select O.orderId ," + " G.goodsName ," + " G.picPath ," + " G.goodsId , " + " O.buyCount ,"
				+ " O.totalPrices ," + " S.address ," + " U.name ," + " O.status "
				+ " from `order` O,goods G,shiptoaddress S,`user` U" + " where O.userId=U.userId"
				+ " and O.goodsId=G.goodsId" + " and O.addressId=S.addressId";
		if (status != -1) {
			sql += " and O.status=" + status;
		}
		sql += " limit " + (pageIndex - 1) * pageSize + "," + pageSize;
		// service层调用dao层数据库操作方法
		list = adminDao.getOrderListByPage(sql);
		return list;
	}

	// 根据id删除订单
	public boolean deleteOrderById(int orderId, int status) throws Exception {
		boolean flag = false;
		int count = 0;
		AdminDao ad = new AdminDaoImpl();
		try {
			count = ad.deleteOrderById(orderId, status);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		if (count > 0) {
			flag = true;
		}
		return flag;
	}

	// 显示一级菜单
	public List<GoodsClass> getOneClassName() {
		AdminDao ad = new AdminDaoImpl();
		return ad.getOneClassName();
	}

	// 显示二级菜单
	public List<GoodsClass> getTwoClassName() {
		AdminDao ad = new AdminDaoImpl();
		return ad.getTwoClassName();
	}

	// 统计销量TOP10查询商品
	public List<Goods> getGoodsListByTop10() {
		List<Goods> list = new ArrayList<>();
		String sql = " select * from goods order by monthSale desc limit 10";
		list = adminDao.getGoodsListByPage(sql);
		return list;
	}
}
