import com.itheima.demo.pojo.Brand;
import com.itheima.demo.service.BrandService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by crowndint on 2019/1/19.
 */
public class AppClient {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BrandService brandService = applicationContext.getBean(BrandService.class);
        System.out.println(brandService);

        List<Brand> brandList = brandService.findAll();
        for (Brand brand : brandList) {
            System.out.println(brand);
        }
    }

}
