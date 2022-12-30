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
import org.jeecg.modules.oa.define.entity.OaDefineFormVersion;
import org.jeecg.modules.oa.define.service.IOaDefineFormVersionService;

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
 * @Description: 表单版本
 * @Author: jeecg-boot
 * @Date:   2022-12-14
 * @Version: V1.0
 */
@Api(tags="表单版本")
@RestController
@RequestMapping("/oa/define/oaDefineFormVersion")
@Slf4j
public class OaDefineFormVersionController extends JeecgController<OaDefineFormVersion, IOaDefineFormVersionService> {
	@Autowired
	private IOaDefineFormVersionService oaDefineFormVersionService;
	
	/**
	 * 分页列表查询
	 *
	 * @param oaDefineFormVersion
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "表单版本-分页列表查询")
	@ApiOperation(value="表单版本-分页列表查询", notes="表单版本-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<OaDefineFormVersion>> queryPageList(OaDefineFormVersion oaDefineFormVersion,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<OaDefineFormVersion> queryWrapper = QueryGenerator.initQueryWrapper(oaDefineFormVersion, req.getParameterMap());
		Page<OaDefineFormVersion> page = new Page<OaDefineFormVersion>(pageNo, pageSize);
		IPage<OaDefineFormVersion> pageList = oaDefineFormVersionService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param oaDefineFormVersion
	 * @return
	 */
	@AutoLog(value = "表单版本-添加")
	@ApiOperation(value="表单版本-添加", notes="表单版本-添加")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_form_version:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody OaDefineFormVersion oaDefineFormVersion) {
		oaDefineFormVersionService.save(oaDefineFormVersion);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param oaDefineFormVersion
	 * @return
	 */
	@AutoLog(value = "表单版本-编辑")
	@ApiOperation(value="表单版本-编辑", notes="表单版本-编辑")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_form_version:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody OaDefineFormVersion oaDefineFormVersion) {
		oaDefineFormVersionService.updateById(oaDefineFormVersion);
		return Result.OK("保存成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "表单版本-通过id删除")
	@ApiOperation(value="表单版本-通过id删除", notes="表单版本-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_form_version:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		oaDefineFormVersionService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "表单版本-批量删除")
	@ApiOperation(value="表单版本-批量删除", notes="表单版本-批量删除")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_form_version:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.oaDefineFormVersionService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "表单版本-通过id查询")
	@ApiOperation(value="表单版本-通过id查询", notes="表单版本-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<OaDefineFormVersion> queryById(@RequestParam(name="id",required=true) String id) {
		OaDefineFormVersion oaDefineFormVersion = oaDefineFormVersionService.getById(id);
		if(oaDefineFormVersion==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(oaDefineFormVersion);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param oaDefineFormVersion
    */
    //@RequiresPermissions("org.jeecg.modules.oa:oa_define_form_version:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, OaDefineFormVersion oaDefineFormVersion) {
        return super.exportXls(request, oaDefineFormVersion, OaDefineFormVersion.class, "表单版本");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("oa_define_form_version:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, OaDefineFormVersion.class);
    }

}
