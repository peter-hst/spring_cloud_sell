package lab.togo.product.service.impl;

import lab.togo.product.ProductApplicationTests;
import lab.togo.product.dataobject.ProductInfo;
import lab.togo.product.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Component
public class ProductServiceImplTest extends ProductApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() {
        List<ProductInfo> list = productService.findUpAll();
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void findListByProductIdIn() {
        List<ProductInfo> list = productService.findListByProductIdIn(Arrays.asList(1,2,3));
        Assert.assertTrue(list.size() > 0);
    }
}
