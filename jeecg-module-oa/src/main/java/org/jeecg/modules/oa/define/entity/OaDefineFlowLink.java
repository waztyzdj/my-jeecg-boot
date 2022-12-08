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
 * @Description: 流程节点连接
 * @Author: jeecg-boot
 * @Date:   2022-11-23
 * @Version: V1.0
 */
@Data
@TableName("oa_define_flow_link")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="oa_define_flow_link对象", description="流程节点连接")
public class OaDefineFlowLink implements Serializable {
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
    /**节点连接id*/
    @Excel(name = "节点连接id", width = 15)
    @ApiModelProperty(value = "节点连接id")
    private java.lang.String linkId;
	/**起始节点id*/
	@Excel(name = "起始节点id", width = 15)
    @ApiModelProperty(value = "起始节点id")
    private java.lang.String sourceId;
	/**起始节点名称*/
	@Excel(name = "起始节点名称", width = 15)
    @ApiModelProperty(value = "起始节点名称")
    private java.lang.String sourceName;
	/**到达节点id*/
	@Excel(name = "到达节点id", width = 15)
    @ApiModelProperty(value = "到达节点id")
    private java.lang.String targetId;
	/**到达节点名称*/
	@Excel(name = "到达节点名称", width = 15)
    @ApiModelProperty(value = "到达节点名称")
    private java.lang.String targetName;
	/**条件json*/
	@Excel(name = "条件json", width = 15)
    @ApiModelProperty(value = "条件json")
    private java.lang.String conditionJson;
}
