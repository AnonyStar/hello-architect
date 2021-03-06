package online.icode.eshop.inventory.service.impl;

import online.icode.eshop.inventory.mapper.DatabaseInfoMapper;
import online.icode.eshop.inventory.model.DatabaseInfo;
import online.icode.eshop.inventory.service.DatabaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhoucx
 * @time: 2021/3/6 20:15
 */
@Service
public class DatabaseInfoServiceImpl implements DatabaseInfoService {

    @Autowired
    private DatabaseInfoMapper databaseInfoMapper;

    @Override
    public List<DatabaseInfo> queryList() {
        return databaseInfoMapper.selectList(null);
    }
}
