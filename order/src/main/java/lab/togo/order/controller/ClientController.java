package lab.togo.order.controller;

import lab.togo.order.client.ProductClient;
import lab.togo.order.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        RestTemplate template = new RestTemplate();

//        1. 第一种方式(直接使用RestTemplate，写死url)
        String response = template.getForObject("http://localhost:8080/msg", String.class);
        log.info("response={}", response);

//        2. 第2种方式(利用loadBalancerClient通过应用名称获取url，然后再使用RestTemplate)
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT"); //在eureka中注册的服务名字
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/msg";
        response = template.getForObject(url, String.class);
        log.info("response2={}", response);
//        3. 第3种方式(利用@LoadBalanced注解,可在RestTemplate里直接使用euerka注册的应用名称)
        response = restTemplate.getForObject("http://PRODUCT/msg", String.class);
        log.info("response3={}", response);
        return response;

    }

    //    5-5 使用Feign通讯
    @GetMapping("/getProductClientMsg")
    public String getProductClientMsg() {
        String response = productClient.productClient();
        log.info("getProductClientMsg: {}", response);
        return response;
    }

    @GetMapping("/getProductList")
    public List<ProductInfo> getProductList() {
        List<ProductInfo> productInfoList = productClient.listForOrder(Arrays.asList(1, 3));
        log.info("response={}", productInfoList);
        return productInfoList;
    }
}
