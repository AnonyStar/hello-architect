package online.icode.eshop.inventory.controller;

import online.icode.eshop.inventory.model.DatabaseInfo;
import online.icode.eshop.inventory.service.DatabaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: zhoucx
 * @time: 2021/3/6 20:16
 */
@RestController
@RequestMapping("data")
public class DataBaseController {

    @Autowired
    private DatabaseInfoService databaseInfoService;

    @GetMapping("list")
    public List<DatabaseInfo> list(){
        return databaseInfoService.queryList();
    }
}
