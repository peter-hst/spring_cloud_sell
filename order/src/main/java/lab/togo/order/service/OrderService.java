package lab.togo.order.service;

import lab.togo.order.dto.OrderDTO;

public interface OrderService {
    /**
     * 创建订单
     * 2. 查询商品信息(调用商品服务)
     * 3. 计算总价
     * 4. 扣库存(调用商品服务)
     * 5. 订单入库
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);
}
