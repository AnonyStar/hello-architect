package online.icode.eshop.inventory.model;

import lombok.Data;

/**
 * 库存数量model
 * @author
 *
 */
@Data
public class ProductInventory {

	/**
	 * 商品id
	 */
	private Integer productId;
	/**
	 * 库存数量
	 */
	private Long inventoryCnt;

}
