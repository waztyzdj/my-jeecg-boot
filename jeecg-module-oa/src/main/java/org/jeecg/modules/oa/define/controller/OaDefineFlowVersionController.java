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
import org.jeecg.modules.oa.define.entity.OaDefineFlowVersion;
import org.jeecg.modules.oa.define.service.IOaDefineFlowVersionService;

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
 * @Description: 流程版本
 * @Author: jeecg-boot
 * @Date:   2022-11-23
 * @Version: V1.0
 */
@Api(tags="流程版本")
@RestController
@RequestMapping("/oa/define/oaDefineFlowVersion")
@Slf4j
public class OaDefineFlowVersionController extends JeecgController<OaDefineFlowVersion, IOaDefineFlowVersionService> {
	@Autowired
	private IOaDefineFlowVersionService oaDefineFlowVersionService;
	
	/**
	 * 分页列表查询
	 *
	 * @param oaDefineFlowVersion
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "流程版本-分页列表查询")
	@ApiOperation(value="流程版本-分页列表查询", notes="流程版本-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<OaDefineFlowVersion>> queryPageList(OaDefineFlowVersion oaDefineFlowVersion,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<OaDefineFlowVersion> queryWrapper = QueryGenerator.initQueryWrapper(oaDefineFlowVersion, req.getParameterMap());
		Page<OaDefineFlowVersion> page = new Page<OaDefineFlowVersion>(pageNo, pageSize);
		IPage<OaDefineFlowVersion> pageList = oaDefineFlowVersionService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param oaDefineFlowVersion
	 * @return
	 */
	@AutoLog(value = "流程版本-添加")
	@ApiOperation(value="流程版本-添加", notes="流程版本-添加")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_flow_version:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody OaDefineFlowVersion oaDefineFlowVersion) {
		oaDefineFlowVersionService.save(oaDefineFlowVersion);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param oaDefineFlowVersion
	 * @return
	 */
	@AutoLog(value = "流程版本-编辑")
	@ApiOperation(value="流程版本-编辑", notes="流程版本-编辑")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_flow_version:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody OaDefineFlowVersion oaDefineFlowVersion) {
		oaDefineFlowVersionService.updateById(oaDefineFlowVersion);
		return Result.OK("配置成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "流程版本-通过id删除")
	@ApiOperation(value="流程版本-通过id删除", notes="流程版本-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_flow_version:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		oaDefineFlowVersionService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "流程版本-批量删除")
	@ApiOperation(value="流程版本-批量删除", notes="流程版本-批量删除")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_flow_version:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.oaDefineFlowVersionService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "流程版本-通过id查询")
	@ApiOperation(value="流程版本-通过id查询", notes="流程版本-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<OaDefineFlowVersion> queryById(@RequestParam(name="id",required=true) String id) {
		OaDefineFlowVersion oaDefineFlowVersion = oaDefineFlowVersionService.getById(id);
		if(oaDefineFlowVersion==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(oaDefineFlowVersion);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param oaDefineFlowVersion
    */
    //@RequiresPermissions("org.jeecg.modules.oa:oa_define_flow_version:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, OaDefineFlowVersion oaDefineFlowVersion) {
        return super.exportXls(request, oaDefineFlowVersion, OaDefineFlowVersion.class, "流程版本");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("oa_define_flow_version:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, OaDefineFlowVersion.class);
    }

}
