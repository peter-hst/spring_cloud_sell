package lab.togo.product.service.impl;

import lab.togo.product.dataobject.ProductInfo;
import lab.togo.product.dto.CartDTO;
import lab.togo.product.enums.ProductStatusEnum;
import lab.togo.product.enums.ResultEnum;
import lab.togo.product.exception.ProductException;
import lab.togo.product.repository.ProductInfoRepository;
import lab.togo.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<ProductInfo> findListByProductIdIn(List<Integer> list) {
        return productInfoRepository.findByProductIdIsIn(list);
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> list) {
        for (CartDTO c : list) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(c.getProductId());

//            判断商品是否存在
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            ProductInfo productInfo = productInfoOptional.get();

//            判断库存是否充足
            Integer result = productInfo.getProductStock() - c.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
