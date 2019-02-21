package lab.togo.product.service.impl;

import lab.togo.product.dataobject.ProductInfo;
import lab.togo.product.enums.ProductStatusEnum;
import lab.togo.product.repository.ProductInfoRepository;
import lab.togo.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
