package lab.togo.order.service.impl;

import lab.togo.order.dataobject.OrderMaster;
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

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
//       TODO 2. 查询商品信息(调用商品服务)
//       TODO 3. 计算总价
//       TODO 4. 扣库存(调用商品服务)
//       5. 订单入库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(BigDecimal.valueOf(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster = orderMasterRepository.save(orderMaster);

        return orderDTO;
    }
}
