package lab.togo.order.form;

import com.alibaba.fastjson.JSON;
import lab.togo.order.dataobject.OrderDetail;
import lab.togo.order.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderForm {
    private Integer orderId;

    private String name;

    private String phone;

    private String address;

    private String openid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private String items;

    public OrderDTO converterToOrderDTO() {
        List<OrderDetail> details = null;
        if (this.items != null && this.items.length() > 0) details = JSON.parseArray(this.items, OrderDetail.class);
        return OrderDTO.builder()
                .orderId(orderId)
                .buyerPhone(phone)
                .buyerOpenid(openid)
                .buyerName(name)
                .buyerAddress(address)
                .orderAmount(orderAmount)
                .orderStatus(orderStatus)
                .orderDetailList(details)
                .build();
    }
}
