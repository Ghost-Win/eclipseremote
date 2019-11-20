package cn.gucci.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.gucci.dao.GoodsDao;
import cn.gucci.dao.impl.GoodsDaoImpl;
import cn.gucci.pojo.Goods;
import cn.gucci.pojo.GoodsClass;
import cn.gucci.pojo.ShiptoAddress;
import cn.gucci.service.GoodsService;

/***
 * 服务层实现类
 * 
 * @author Administrator
 *
 */
public class GoodsServiceImpl implements GoodsService {
	GoodsDao gd = new GoodsDaoImpl();

	// 查询一共有多少条记录
	public int getGoodsCount(int type,int goodsClassId) {
		int count = 0;
		String sql = "select count(1) from goods  where  status=1";
		if(type!=0) {
			sql+=" and type="+type;
		}
		if(goodsClassId!=0) {
			sql+=" and goodsClassId="+goodsClassId;
		}
		count = gd.getGoodsCount(sql);
		return count;
	}

	// 分页查询
	public List<Goods> getGoodsListByPage(int pageIndex, int pageSize, int type,int goodsClassId) throws Exception {
		List<Goods> list = new ArrayList<>();
		String sql = "select * from goods where status=1 ";
		if (type != 0) {
			sql += " and type=" + type;
		}
		if(goodsClassId!=0) {
			sql+=" and goodsClassId="+goodsClassId;
		}
		sql += " limit " + (pageIndex - 1) * pageSize + "," + pageSize;
		try {
			list = gd.getGoodsListByPage(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	// 查询所有商品分类
	public List<GoodsClass> getGoodsClassList() {
		List<GoodsClass> list = new ArrayList<>();
		String sql = "select * from goodsclass where type=0";
		list = gd.getGoodsClassList(sql);
		return list;
	}

	// 根据商品ID找到商品对象
	public Goods getGoods(int goodsId) {
		return gd.getGoods(goodsId);
	}

	// 根据分类ID查询该商品的分类名称
	public GoodsClass getClassName(int goodsClassId) {
		return gd.getClassName(goodsClassId);
	}

	// 根据ID增加商品的数量
	public int updateAddGoodsCount(int goodsId) {
		String sql = "update goods set goodsCount=goodsCount+1,monthSale=monthSale-1 where goodsId=" + goodsId;
		int count = gd.updateGoodsCount(sql);
		return count;
	}

	// 根据ID减少商品的数量
	public int updateReduceGoodsCount(int goodsId) {
		String sql = "update goods set goodsCount=goodsCount-1,monthSale=monthSale+1 where goodsId=" + goodsId;
		int count = gd.updateGoodsCount(sql);
		return count;
	}

	// 根据ID增加商品的数量(不定量)
	public int AddGoodsCount(int goodsId, int count) {
		int c = 0;
		String sql = "update goods set goodsCount=goodsCount+" + count + ",monthSale=monthSale-1 where goodsId="
				+ goodsId;
		c = gd.updateGoodsCount(sql);
		return c;
	}

	// 根据ID查询该用户的所有的收货地址
	public List<ShiptoAddress> getShipAddressListByUserId(int userId){
		return gd.getShipAddressListByUserId(userId);
	}
}
