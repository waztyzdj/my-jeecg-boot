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
 * @Description: 表单定义
 * @Author: jeecg-boot
 * @Date:   2022-12-14
 * @Version: V1.0
 */
@Data
@TableName("oa_define_form")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="oa_define_form对象", description="表单定义")
public class OaDefineForm implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**单位id*/
	@Excel(name = "单位id", width = 15)
    @ApiModelProperty(value = "单位id")
    private java.lang.String departId;
	/**单位*/
	@Excel(name = "单位", width = 15)
    @ApiModelProperty(value = "单位")
    private java.lang.String departName;
	/**名称*/
	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
    private java.lang.String name;
	/**编辑版本*/
	@Excel(name = "编辑版本", width = 15)
    @ApiModelProperty(value = "编辑版本")
    private java.lang.String editVersionNo;
	/**编辑版本id*/
	@Excel(name = "编辑版本id", width = 15)
    @ApiModelProperty(value = "编辑版本id")
    private java.lang.String editVersionId;
	/**发布版本*/
	@Excel(name = "发布版本", width = 15)
    @ApiModelProperty(value = "发布版本")
    private java.lang.String publishVersionNo;
	/**发布版本id*/
	@Excel(name = "发布版本id", width = 15)
    @ApiModelProperty(value = "发布版本id")
    private java.lang.String publishVersionId;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private java.lang.String status;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
}
