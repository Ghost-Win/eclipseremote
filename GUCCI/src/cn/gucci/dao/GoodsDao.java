package cn.gucci.dao;

import java.util.List;

import cn.gucci.pojo.Goods;
import cn.gucci.pojo.GoodsClass;
import cn.gucci.pojo.ShiptoAddress;

/**
 * dao接口
 * 
 * @author Administrator
 *
 */
public interface GoodsDao {
	// 查询一共有多少条记录
	public int getGoodsCount(String sql);

	// 查询商品
	public List<Goods> getGoodsListByPage(String sql) throws Exception;

	// 查询所有商品分类
	public List<GoodsClass> getGoodsClassList(String sql);

	// 根据商品ID找到商品对象
	public Goods getGoods(int goodsId);

	// 根据分类ID查询该商品的分类名称
	public GoodsClass getClassName(int goodsClassId);

	// 根据ID改变商品的数量
	public int updateGoodsCount(String sql);

	// 根据ID查询该用户的所有的收货地址
	public List<ShiptoAddress> getShipAddressListByUserId(int userId);
	
}
