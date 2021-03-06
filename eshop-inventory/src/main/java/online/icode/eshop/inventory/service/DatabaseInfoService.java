package online.icode.eshop.inventory.service;

import online.icode.eshop.inventory.model.DatabaseInfo;

import java.util.List;

/**
 * @author: zhoucx
 * @time: 2021/3/6 12:19
 */

public interface DatabaseInfoService {


    List<DatabaseInfo> queryList();
}
