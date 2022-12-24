package org.jeecg.modules.laboratory.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import com.aloneatwar.laboratory.entity.LaboratoryStudent;
import com.aloneatwar.laboratory.entity.Laboratory;
import org.jeecg.modules.laboratory.vo.LaboratoryPage;
import org.jeecg.modules.laboratory.service.ILaboratoryService;
import org.jeecg.modules.laboratory.service.ILaboratoryStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: 实验室表
 * @Author: jeecg-boot
 * @Date:   2022-12-23
 * @Version: V1.0
 */
@Api(tags="实验室表")
@RestController
@RequestMapping("/laboratory/laboratory")
@Slf4j
public class LaboratoryController {
	@Autowired
	private ILaboratoryService laboratoryService;
	@Autowired
	private ILaboratoryStudentService laboratoryStudentService;
	
	/**
	 * 分页列表查询
	 *
	 * @param laboratory
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "实验室表-分页列表查询")
	@ApiOperation(value="实验室表-分页列表查询", notes="实验室表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Laboratory>> queryPageList(Laboratory laboratory,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Laboratory> queryWrapper = QueryGenerator.initQueryWrapper(laboratory, req.getParameterMap());
		Page<Laboratory> page = new Page<Laboratory>(pageNo, pageSize);
		IPage<Laboratory> pageList = laboratoryService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param laboratoryPage
	 * @return
	 */
	@AutoLog(value = "实验室表-添加")
	@ApiOperation(value="实验室表-添加", notes="实验室表-添加")
    //@RequiresPermissions("laboratory:laboratory:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody LaboratoryPage laboratoryPage) {
		Laboratory laboratory = new Laboratory();
		BeanUtils.copyProperties(laboratoryPage, laboratory);
		laboratoryService.saveMain(laboratory, laboratoryPage.getLaboratoryStudentList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param laboratoryPage
	 * @return
	 */
	@AutoLog(value = "实验室表-编辑")
	@ApiOperation(value="实验室表-编辑", notes="实验室表-编辑")
    //@RequiresPermissions("laboratory:laboratory:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody LaboratoryPage laboratoryPage) {
		Laboratory laboratory = new Laboratory();
		BeanUtils.copyProperties(laboratoryPage, laboratory);
		Laboratory laboratoryEntity = laboratoryService.getById(laboratory.getId());
		if(laboratoryEntity==null) {
			return Result.error("未找到对应数据");
		}
		laboratoryService.updateMain(laboratory, laboratoryPage.getLaboratoryStudentList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "实验室表-通过id删除")
	@ApiOperation(value="实验室表-通过id删除", notes="实验室表-通过id删除")
    //@RequiresPermissions("laboratory:laboratory:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		laboratoryService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "实验室表-批量删除")
	@ApiOperation(value="实验室表-批量删除", notes="实验室表-批量删除")
    //@RequiresPermissions("laboratory:laboratory:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.laboratoryService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "实验室表-通过id查询")
	@ApiOperation(value="实验室表-通过id查询", notes="实验室表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Laboratory> queryById(@RequestParam(name="id",required=true) String id) {
		Laboratory laboratory = laboratoryService.getById(id);
		if(laboratory==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(laboratory);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "实验室学生表-通过主表ID查询")
	@ApiOperation(value="实验室学生表-通过主表ID查询", notes="实验室学生表-通过主表ID查询")
	@GetMapping(value = "/queryLaboratoryStudentByMainId")
	public Result<IPage<LaboratoryStudent>> queryLaboratoryStudentListByMainId(@RequestParam(name="id",required=true) String id) {
		List<LaboratoryStudent> laboratoryStudentList = laboratoryStudentService.selectByMainId(id);
		IPage <LaboratoryStudent> page = new Page<>();
		page.setRecords(laboratoryStudentList);
		page.setTotal(laboratoryStudentList.size());
		return Result.OK(page);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param laboratory
    */
    //@RequiresPermissions("laboratory:laboratory:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Laboratory laboratory) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<Laboratory> queryWrapper = QueryGenerator.initQueryWrapper(laboratory, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

     //配置选中数据查询条件
      String selections = request.getParameter("selections");
      if(oConvertUtils.isNotEmpty(selections)) {
           List<String> selectionList = Arrays.asList(selections.split(","));
           queryWrapper.in("id",selectionList);
      }
      //Step.2 获取导出数据
      List<Laboratory>  laboratoryList = laboratoryService.list(queryWrapper);

      // Step.3 组装pageList
      List<LaboratoryPage> pageList = new ArrayList<LaboratoryPage>();
      for (Laboratory main : laboratoryList) {
          LaboratoryPage vo = new LaboratoryPage();
          BeanUtils.copyProperties(main, vo);
          List<LaboratoryStudent> laboratoryStudentList = laboratoryStudentService.selectByMainId(main.getId());
          vo.setLaboratoryStudentList(laboratoryStudentList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "实验室表列表");
      mv.addObject(NormalExcelConstants.CLASS, LaboratoryPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("实验室表数据", "导出人:"+sysUser.getRealname(), "实验室表"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
    * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("laboratory:laboratory:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          // 获取上传文件对象
          MultipartFile file = entity.getValue();
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<LaboratoryPage> list = ExcelImportUtil.importExcel(file.getInputStream(), LaboratoryPage.class, params);
              for (LaboratoryPage page : list) {
                  Laboratory po = new Laboratory();
                  BeanUtils.copyProperties(page, po);
                  laboratoryService.saveMain(po, page.getLaboratoryStudentList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

}
