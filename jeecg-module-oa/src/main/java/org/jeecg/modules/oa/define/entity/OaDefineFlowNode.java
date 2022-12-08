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
 * @Description: 流程节点
 * @Author: jeecg-boot
 * @Date:   2022-11-23
 * @Version: V1.0
 */
@Data
@TableName("oa_define_flow_node")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="oa_define_flow_node对象", description="流程节点")
public class OaDefineFlowNode implements Serializable {
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
	/**节点类型*/
	@Excel(name = "节点类型", width = 15)
    @ApiModelProperty(value = "节点类型")
    private java.lang.String type;
	/**节点名称*/
	@Excel(name = "节点名称", width = 15)
    @ApiModelProperty(value = "节点名称")
    private java.lang.String nodeName;
	/**流转类型*/
	@Excel(name = "流转类型", width = 15)
    @ApiModelProperty(value = "流转类型")
    private java.lang.String flowType;
    /**是否允许回退*/
    @Excel(name = "是否允许回退", width = 15)
    @ApiModelProperty(value = "是否允许回退")
    private java.lang.Integer isBack;
    /**是否自我循环*/
    @Excel(name = "是否自我循环", width = 15)
    @ApiModelProperty(value = "是否自我循环")
    private java.lang.Integer isLoop;
}
