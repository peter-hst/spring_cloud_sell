package lab.togo.order.client;

import lab.togo.order.dataobject.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// name=product 表示order项目想访问product项目的内容
@FeignClient(name="product")
public interface ProductClient {

    @GetMapping("/msg") //表示对应product项目的 get方法得 msg接口
    String productClient();

    @GetMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<Integer> productIdList);
}
