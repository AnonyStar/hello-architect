package online.icode.eshop.inventory.mapper;

import online.icode.eshop.inventory.model.DatabaseInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author: zhoucx
 * @time: 2021/3/6 12:10
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class DatabaseInfoMapperTest {

    @Autowired
    private DatabaseInfoMapper databaseInfoMapper;

    @Test
    public void test1() {
        final List<DatabaseInfo> databaseInfos = databaseInfoMapper.selectList(null);
        System.out.println(databaseInfos);
    }

}