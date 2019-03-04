package lab.togo.product.controller;

import lab.togo.product.dataobject.ProductCategory;
import lab.togo.product.dataobject.ProductInfo;
import lab.togo.product.service.CategoryService;
import lab.togo.product.service.ProductService;
import lab.togo.product.vo.ProductInfoVO;
import lab.togo.product.vo.ProductVO;
import lab.togo.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    /**
     * 1. 查询所有在架的商品
     * 2. 获取类目type列表
     * 3. 查询类目
     * 4. 构造数据
     */
    @GetMapping("/list")
    public ResultVO<ProductVO> list() {
//        1. 查询所有在架的商品
        List<ProductInfo> list = productService.findUpAll();
//        2. 获取类目type列表
        List<Integer> categoryTypeList = list.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
//        3. 从数据库查询类目
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
//        4. 构造数据
        List<ProductVO> productVOS = new ArrayList<>();

        for (ProductCategory productCategory : categoryList) {
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(productCategory, productVO);
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : list) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOS.add(productVO);
        }

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(productVOS);
        return resultVO;
    }

    @PostMapping("/listForOrder") //只要用了@RequestBody注解,必须用@PostMapping
    public List<ProductInfo> listForOrder(@RequestBody List<Integer> productIdList) {
        return productService.findListByProductIdIn(productIdList);
    }
}
