package lab.togo.order.service.impl;

import lab.togo.order.client.ProductClient;
import lab.togo.order.dataobject.OrderDetail;
import lab.togo.order.dataobject.OrderMaster;
import lab.togo.order.dataobject.ProductInfo;
import lab.togo.order.dto.CartDTO;
import lab.togo.order.dto.OrderDTO;
import lab.togo.order.enums.OrderStatusEnum;
import lab.togo.order.enums.PayStatusEnum;
import lab.togo.order.repostiory.OrderDetailRepository;
import lab.togo.order.repostiory.OrderMasterRepository;
import lab.togo.order.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
//       2. 查询商品信息(调用商品服务)
        List<ProductInfo> productInfoList = productClient.listForOrder(orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList()));
        // 3. 订单入库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
//       4. 计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfo p : productInfoList) {
                if (p.getProductId().equals(orderDetail.getProductId())) {
                    //   单价 * 数量
                    orderAmount = p.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
                    BeanUtils.copyProperties(p, orderDetail);
//                    订单详情入库
                    orderDetail.setOrderId(orderMaster.getOrderId());
                    orderDetail = orderDetailRepository.save(orderDetail);
                }
            }
        }
//       5. 扣库存(调用商品服务)
        productClient.decreaseStock(orderDTO.getOrderDetailList()
                .stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList()));
//        6. 更新total
        orderMaster.setOrderAmount(orderAmount);
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
