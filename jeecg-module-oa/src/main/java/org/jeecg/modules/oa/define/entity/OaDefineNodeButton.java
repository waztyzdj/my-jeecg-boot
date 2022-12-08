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
 * @Description: 流程节点按钮
 * @Author: jeecg-boot
 * @Date:   2022-11-23
 * @Version: V1.0
 */
@Data
@TableName("oa_define_node_button")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="oa_define_node_button对象", description="流程节点按钮")
public class OaDefineNodeButton implements Serializable {
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
	/**按钮id*/
	@Excel(name = "按钮id", width = 15)
    @ApiModelProperty(value = "按钮id")
    private java.lang.String buttonId;
	/**名称*/
	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
    private java.lang.String name;
	/**别名*/
	@Excel(name = "别名", width = 15)
    @ApiModelProperty(value = "别名")
    private java.lang.String aliasName;
	/**样式*/
	@Excel(name = "样式", width = 15)
    @ApiModelProperty(value = "样式")
    private java.lang.String style;
	/**触发事件*/
	@Excel(name = "触发事件", width = 15)
    @ApiModelProperty(value = "触发事件")
    private java.lang.String emitCode;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
}
