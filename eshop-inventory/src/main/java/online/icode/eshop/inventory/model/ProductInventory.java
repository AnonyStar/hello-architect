package online.icode.eshop.inventory.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 库存数量model
 * @author
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_product")
public class ProductInventory {

	/**
	 * 商品id
	 */
	@TableId(value = "product_id")
	private Integer productId;
	/**
	 * 库存数量
	 */
	@TableField(value = "inventory_cnt")
	private Long inventoryCnt;


}
