package lab.togo.product.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class ProductCategory {
    @Id
    @GeneratedValue
    private Integer categoryId;

    private Integer categoryType;

    private String categoryName;

    private Date createTime;

    private Date updateTime;
}
