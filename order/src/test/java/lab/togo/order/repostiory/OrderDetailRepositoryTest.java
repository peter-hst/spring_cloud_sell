package lab.togo.order.repostiory;

import lab.togo.order.OrderApplicationTests;
import lab.togo.order.dataobject.OrderDetail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

@Component
public class OrderDetailRepositoryTest extends OrderApplicationTests {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void testSave(){
        assertNotNull(orderDetailRepository.save(OrderDetail.builder()
                .createTime(new Date())
                .orderId(1)
                .productId(1)
                .productName("麻辣鸡丁")
                .productPrice(BigDecimal.valueOf(32.56))
                .productQuantity(2)
                .productIcon("http://ddid.com")
                .build()));
    }
}
