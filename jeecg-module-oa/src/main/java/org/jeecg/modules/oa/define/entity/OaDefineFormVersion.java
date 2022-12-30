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
 * @Description: 表单版本
 * @Author: jeecg-boot
 * @Date:   2022-12-14
 * @Version: V1.0
 */
@Data
@TableName("oa_define_form_version")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="oa_define_form_version对象", description="表单版本")
public class OaDefineFormVersion implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**表单id*/
	@Excel(name = "表单id", width = 15)
    @ApiModelProperty(value = "表单id")
    private java.lang.String formId;
	/**表单名称*/
	@Excel(name = "表单名称", width = 15)
    @ApiModelProperty(value = "表单名称")
    private java.lang.String formName;
	/**版本号*/
	@Excel(name = "版本号", width = 15)
    @ApiModelProperty(value = "版本号")
    private java.lang.String versionNo;
	/**配置json*/
	@Excel(name = "配置json", width = 15)
    @ApiModelProperty(value = "配置json")
    private java.lang.String configJson;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private java.lang.String status;
}
