package cn.gucci.service;

import java.util.List;

import cn.gucci.pojo.Goods;
import cn.gucci.pojo.GoodsClass;
import cn.gucci.pojo.Order;

public interface AdminService {
	// 分页查询商品
	public List<Goods> getGoodsListByPage(int pageIndex, int pageSize,int type);

	// 根据id删除商品
	public boolean deleteGoodsById(int status,int goodsId) throws Exception;

	// 添加商品
	public boolean addGoods(Goods goods);

	// 查询商品数据的总数
	public int showGoodsCount(int type);

	// 修改商品
	public boolean updateGoods(int price, String goodsName, int goodsCount, int monthSale, int goodsId);

	// 按分类查询商品
	public List<Goods> getGoodsListByGoodsClass(int pageIndex, int pageSize, String className);

	// 分页查询所有订单
	public List<Order> getOrderListByPage(int pageIndex, int pageSize, int status);

	// 查询订单数据的总数
	public int showOrderCount(int status);

	// 根据id删除订单
	public boolean deleteOrderById(int orderId, int status) throws Exception;

	// 显示一级菜单
	public List<GoodsClass> getOneClassName();

	// 显示二级菜单
	public List<GoodsClass> getTwoClassName();

	// 统计销量TOP10查询商品
	public List<Goods> getGoodsListByTop10();
}
