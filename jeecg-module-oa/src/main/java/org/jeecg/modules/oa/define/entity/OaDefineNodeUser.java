package org.jeecg.modules.oa.define.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 流程节点执行人
 * @Author: jeecg-boot
 * @Date:   2022-11-23
 * @Version: V1.0
 */
@Data
@TableName("oa_define_node_user")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="oa_define_node_user对象", description="流程节点执行人")
public class OaDefineNodeUser implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**流程id*/
	@Excel(name = "流程id", width = 15)
    @ApiModelProperty(value = "流程id")
    private java.lang.String flowId;
	/**版本id*/
	@Excel(name = "版本id", width = 15)
    @ApiModelProperty(value = "版本id")
    private java.lang.String versionId;
	/**节点id*/
	@Excel(name = "节点id", width = 15)
    @ApiModelProperty(value = "节点id")
    private java.lang.String nodeId;
	/**执行人id*/
	@Excel(name = "执行人id", width = 15)
    @ApiModelProperty(value = "执行人id")
    private java.lang.String userDepartId;
	/**执行人名称*/
	@Excel(name = "执行人名称", width = 15)
    @ApiModelProperty(value = "执行人名称")
    private java.lang.String userName;
}
