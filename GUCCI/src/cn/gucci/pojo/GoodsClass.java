package cn.gucci.pojo;
/**
 * 商品分类(分类编号,分类名称)
 * @author Administrator
 *
 */
public class GoodsClass {
	private int goodsClassId;
	private String className;
	private int type;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getGoodsClassId() {
		return goodsClassId;
	}
	public void setGoodsClassId(int goodsClassId) {
		this.goodsClassId = goodsClassId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
}
