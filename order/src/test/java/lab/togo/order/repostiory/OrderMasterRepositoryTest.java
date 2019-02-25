package lab.togo.order.repostiory;

import lab.togo.order.OrderApplicationTests;
import lab.togo.order.dataobject.OrderMaster;
import lab.togo.order.enums.OrderStatusEnum;
import lab.togo.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave() {
        OrderMaster orderMaster = orderMasterRepository.save(OrderMaster
                .builder()
                .buyerName("黄师兄")
                .buyerAddress("新天地")
                .buyerPhone("138888888")
                .buyerOpenid("123010111")
                .orderAmount(BigDecimal.valueOf(2.5))
                .orderStatus(OrderStatusEnum.NEW.getCode())
                .payStatus(PayStatusEnum.WAIT.getCode())
                .createTime(new Date())
                .build());
        assertNotNull(orderMaster);
    }
}
