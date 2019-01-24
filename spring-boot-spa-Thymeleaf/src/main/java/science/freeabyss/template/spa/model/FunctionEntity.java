package science.freeabyss.template.spa.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单信息
 *
 * @author abyss
 * @date 2019-01-23 20:14
 */
@Data
public class FunctionEntity implements Serializable {
    private Long id;
    private String name;
    private Long parentId;
    private String url;
    private String icons;
    private Integer serialNum;
    private Integer status;
    private Integer type;
    private Date updateTime;
    private Date createTime;
}
