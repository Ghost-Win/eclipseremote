package cn.gucci.service;

import java.util.List;

import cn.gucci.pojo.Goods;
import cn.gucci.pojo.GoodsClass;
import cn.gucci.pojo.ShiptoAddress;

/**
 * 服务层，处理数据逻辑
 * 
 * @author Administrator
 *
 */
public interface GoodsService {
	// 查询一共有多少条记录
	public int getGoodsCount(int type,int goodsClassId);

	// 分页查询商品
	public List<Goods> getGoodsListByPage(int pageIndex, int pageSize,int type,int goodsClassId) throws Exception;

	// 查询所有商品分类
	public List<GoodsClass> getGoodsClassList();

	// 根据商品ID找到商品对象
	public Goods getGoods(int goodsId);

	// 根据分类ID查询该商品的分类名称
	public GoodsClass getClassName(int goodsClassId);

	// 根据ID增加商品的数量
	public int updateAddGoodsCount(int goodsId);

	// 根据ID减少商品的数量
	public int updateReduceGoodsCount(int goodsId);

	// 根据ID增加商品的数量(不定量)
	public int AddGoodsCount(int goodsId, int count);

	// 根据ID查询该用户的所有的收货地址
	public List<ShiptoAddress> getShipAddressListByUserId(int userId);
}
