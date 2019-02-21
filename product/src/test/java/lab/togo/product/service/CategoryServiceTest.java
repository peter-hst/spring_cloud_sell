package lab.togo.product.service;

import lab.togo.product.ProductApplicationTests;
import lab.togo.product.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static org.junit.Assert.*;

@Component
public class CategoryServiceTest extends ProductApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findByCategoryTypeIn() {
        Assert.assertTrue(categoryService.findByCategoryTypeIn(Arrays.asList(11, 22)).size() > 0);
    }
}
