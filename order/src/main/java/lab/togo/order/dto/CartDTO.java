package lab.togo.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;
}
