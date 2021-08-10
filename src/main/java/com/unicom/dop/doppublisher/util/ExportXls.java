package com.unicom.dop.doppublisher.util;

import com.unicom.dop.doppublisher.entity.OdsBacknetEquipPlanTest;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class ExportXls {

    /**
     * <p>
     * 导出excel
     * </P>
     */
    public static ModelAndView exportXls(List list, Class clazz, String title) {
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, title);
        mv.addObject(NormalExcelConstants.CLASS, clazz);
        //todo 可加上导出人
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + "admin", title));
        mv.addObject(NormalExcelConstants.DATA_LIST, list);
        return mv;
    }

    /**
     * <p>
     * 从请求里读取数据
     * </p>
     */
    public static List<OdsBacknetEquipPlanTest> importExcel(HttpServletRequest request, Class clazz) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取上传文件对象
            MultipartFile file = entity.getValue();
            ImportParams params = new ImportParams();
            //从第几行开始读
            params.setTitleRows(0);
            //第几列
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                return ExcelImportUtil.importExcel(file.getInputStream(), clazz, params);
            } catch (Exception e) {
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
