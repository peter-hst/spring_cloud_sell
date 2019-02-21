package lab.togo.product.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lab.togo.product.dataobject.ProductInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductVO implements Serializable {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
