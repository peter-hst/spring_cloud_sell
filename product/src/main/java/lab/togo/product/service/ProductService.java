package lab.togo.product.service;

import lab.togo.product.dataobject.ProductInfo;
import lab.togo.product.dto.CartDTO;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有在架商品列表
     *
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 根据ID list查询商品信息列表
     *
     * @param list
     * @return
     */
    List<ProductInfo> findListByProductIdIn(List<Integer> list);

    /**
     * 扣库存
     * @param list
     */
    void decreaseStock(List<CartDTO> list);
}
