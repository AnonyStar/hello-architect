package online.icode.eshop.cache.eshopcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EshopCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopCacheApplication.class, args);
    }

}
