package cn.gucci.pojo;
/**
 * 购物车类(商品对象，购买数量，总价，状态（是否选中,0表示未选中，1表示选中）)
 * @author Administrator
 *
 */
public class Cart {
	private Goods goods;
	private int buyCount;
	private int prices;
	private int statu;
	private int userId;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStatu() {
		return statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public int getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(int buyCount) {
		if(buyCount>0) {
			this.buyCount = buyCount;
		}else {
			buyCount=1;
		}
		
	}
	public int getPrices() {
		return prices;
	}
	public void setPrices(int prices) {
		this.prices = prices;
	}
	
	
}
