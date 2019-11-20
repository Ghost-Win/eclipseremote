package cn.gucci.dao;

import java.util.List;

import cn.gucci.pojo.Goods;
import cn.gucci.pojo.GoodsClass;
import cn.gucci.pojo.Order;

public interface AdminDao {
	//查询所有商品
	public List<Goods> getGoodsListByPage(String sql);
	//删除商品
	public int deleteGoodsById(int status,int goodsId) throws Exception;
	//添加商品
	public int addGoods(Goods goods);
	//查询商品数据的总数
	public int showGoodsCount(int type);
	//修改商品
	public int updateGoods(int price,String goodsName,int goodsCount,int monthSale,int goodsId);
	//按分类查询商品
	public List<Goods> getGoodsListByGoodsClass(String sql);
	//按销量查询商品
//------------------------------//
	//分页查询所有订单
	public List<Order> getOrderListByPage(String sql);
	//查询订单数据的总数
	public int showOrderCount(int status);
	//删除订单
	public int deleteOrderById(int orderId,int status) throws Exception;
	//显示一级菜单
	public List<GoodsClass> getOneClassName();
	//显示二级菜单
	public List<GoodsClass> getTwoClassName();
}
