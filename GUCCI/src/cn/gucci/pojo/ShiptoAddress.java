package cn.gucci.pojo;
/**
 * 收货地址(收货地址编号,收货地址,用户编号(FK))
 * @author Administrator
 *
 */
public class ShiptoAddress {
	private int addressId;
	private String address;
	private int userId;
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
