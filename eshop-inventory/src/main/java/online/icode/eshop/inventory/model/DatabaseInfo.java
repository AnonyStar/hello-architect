package online.icode.eshop.inventory.model;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 数据库表
 *
 * @author zhoucx
 * @email zhoucx@newland.com.cn
 * @date 2021-02-26 15:21:23
 */
@Data
@TableName("t_database_info")
public class DatabaseInfo  implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "database_id", type = IdType.UUID)
	private String databaseId;
	/**
	 * 数据库/用户模式
	 */
	@TableField(value = "database_name")
	private String databaseName;
	/**
	 * 数据库/实体存放
	 */
	@TableField(value = "database_description")
	private String databaseDescription;
	/**
	 * 产品线ID
	 */
	@TableField(value = "product_id")
	private String productId;
	/**
	 * 数据库类型 1：Oracle 2： MySQL
	 */
	@TableField(value = "database_type")
	private Integer databaseType;
	/**
	 * 是否删除 0：不可用 1：可用
	 */
	@TableField(value = "is_delete")
	private Integer isDelete;

}
