package online.icode.eshop.inventory.service;


import online.icode.eshop.inventory.model.ProductInventory;

/**
 * 商品库存Service接口
 * @author zhoucx
 *
 */
public interface ProductInventoryService {

	/**
	 * 更新商品库存
	 * @param productInventory 商品库存
	 */
	void updateProductInventory(ProductInventory productInventory);
	
	/**
	 * 删除Redis中的商品库存的缓存
	 * @param productInventory 商品库存
	 */
	void removeProductInventoryCache(ProductInventory productInventory);
	
	/**
	 * 根据商品id查询商品库存
	 * @param productId 商品id 
	 * @return 商品库存
	 */
	ProductInventory findProductInventory(Integer productId);
	
	/**
	 * 设置商品库存的缓存
	 * @param productInventory 商品库存
	 */
	void setProductInventoryCache(ProductInventory productInventory);

	/**
	 * 根据商品ID从缓存中获取商品库存.
	 * @param productId 商品id
	 * @return
	 */
	ProductInventory getProductInventoryCache(Integer productId);
}
