package org.jeecg.modules.oa.define.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.oa.define.entity.OaDefineForm;
import org.jeecg.modules.oa.define.entity.OaDefineForm;
import org.jeecg.modules.oa.define.service.IOaDefineFormService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 表单定义
 * @Author: jeecg-boot
 * @Date:   2022-12-14
 * @Version: V1.0
 */
@Api(tags="表单定义")
@RestController
@RequestMapping("/oa/define/oaDefineForm")
@Slf4j
public class OaDefineFormController extends JeecgController<OaDefineForm, IOaDefineFormService> {
	@Autowired
	private IOaDefineFormService oaDefineFormService;
	
	/**
	 * 分页列表查询
	 *
	 * @param oaDefineForm
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "表单定义-分页列表查询")
	@ApiOperation(value="表单定义-分页列表查询", notes="表单定义-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<OaDefineForm>> queryPageList(OaDefineForm oaDefineForm,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<OaDefineForm> queryWrapper = QueryGenerator.initQueryWrapper(oaDefineForm, req.getParameterMap());
		Page<OaDefineForm> page = new Page<OaDefineForm>(pageNo, pageSize);
		IPage<OaDefineForm> pageList = oaDefineFormService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param oaDefineForm
	 * @return
	 */
	@AutoLog(value = "表单定义-添加")
	@ApiOperation(value="表单定义-添加", notes="表单定义-添加")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_form:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody OaDefineForm oaDefineForm) {
		oaDefineFormService.save(oaDefineForm);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param oaDefineForm
	 * @return
	 */
	@AutoLog(value = "表单定义-编辑")
	@ApiOperation(value="表单定义-编辑", notes="表单定义-编辑")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_form:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody OaDefineForm oaDefineForm) {
		oaDefineFormService.updateById(oaDefineForm);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "表单定义-通过id删除")
	@ApiOperation(value="表单定义-通过id删除", notes="表单定义-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_form:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		oaDefineFormService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "表单定义-批量删除")
	@ApiOperation(value="表单定义-批量删除", notes="表单定义-批量删除")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_form:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.oaDefineFormService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "表单定义-通过id查询")
	@ApiOperation(value="表单定义-通过id查询", notes="表单定义-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<OaDefineForm> queryById(@RequestParam(name="id",required=true) String id) {
		OaDefineForm oaDefineForm = oaDefineFormService.getById(id);
		if(oaDefineForm==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(oaDefineForm);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param oaDefineForm
    */
    //@RequiresPermissions("org.jeecg.modules.oa:oa_define_form:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, OaDefineForm oaDefineForm) {
        return super.exportXls(request, oaDefineForm, OaDefineForm.class, "表单定义");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("oa_define_form:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, OaDefineForm.class);
    }

	 /**
	  *  发布
	  *
	  * @param oaDefineForm
	  * @return
	  */
	 @AutoLog(value = "表单定义-发布")
	 @ApiOperation(value="表单定义-发布", notes="表单定义-发布")
	 //@RequiresPermissions("org.jeecg.modules.oa:oa_define_form:audit")
	 @RequestMapping(value = "/audit", method = {RequestMethod.PUT,RequestMethod.POST})
	 public Result<String> audit(@RequestBody OaDefineForm oaDefineForm) {
		 oaDefineFormService.audit(oaDefineForm);
		 return Result.OK("发布成功!");
	 }

	 /**
	  *  配置新版本
	  *
	  * @param oaDefineForm
	  * @return
	  */
	 @AutoLog(value = "表单定义-配置新版本")
	 @ApiOperation(value="表单定义-配置新版本", notes="表单定义-配置新版本")
	 //@RequiresPermissions("org.jeecg.modules.oa:oa_define_form:addConfig")
	 @RequestMapping(value = "/addConfig", method = {RequestMethod.PUT,RequestMethod.POST})
	 public Result<OaDefineForm> addConfig(@RequestBody OaDefineForm oaDefineForm) {
		 OaDefineForm form = oaDefineFormService.addConfig(oaDefineForm.getId());
		 return Result.OK(form);
	 }

}
