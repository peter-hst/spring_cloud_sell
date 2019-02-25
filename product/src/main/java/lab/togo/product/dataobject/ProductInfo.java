package lab.togo.product.dataobject;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name="PRODUCT_INFO")
@Entity
@Data
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    //    状态, 0正常 1下架
    private Integer productStatus;

    private Date createTime;

    private Date updateTime;

    private Integer categoryType;

}
