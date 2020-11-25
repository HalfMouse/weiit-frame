package com.weiit.resource.common.utils;
 
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 * 公共excel导出xls格式
 * 参考http://www.cnblogs.com/zhuixun/p/6600331.html
 * @author hzy 2017-9-1
 * 
 */
public class XlsExcelUtil {

    public static File createXls(List results, String titleStr, String fields, String outPutPath, String filename)
            throws Exception
    {
    	//判断文件目录
    	File parent = new File(String.valueOf(outPutPath));
        if(parent != null && !parent.exists())
            parent.mkdirs();
        
    	//1.创建工作簿
    	HSSFWorkbook workbook = new HSSFWorkbook();
    	//1.2头标题样式
        HSSFCellStyle headStyle = createCellStyle(workbook,(short)13);
        //2.创建工作表
        HSSFSheet sheet = workbook.createSheet(filename);
        sheet.setDefaultColumnWidth(15);
        //3.创建行
        HSSFRow row = sheet.createRow(0);
        //3.1创建头标题行;并且设置头标题
        String[] titles = titleStr.split(",");
        for(int i=0;i<titles.length;i++)
        {
            HSSFCell cell = row.createCell(i);
            //加载单元格样式
            cell.setCellStyle(headStyle);
            cell.setCellValue(titles[i]);
        }
        //4创建内容行;并且设置值
        String fieldArr[] = fields.split(","); 
        for (int i = 0; i < results.size(); i++) {
        	Map info = (Map)results.get(i);
			HSSFRow row22 = sheet.createRow(i+1);
			int idxCell = 0;
			for(int j = 0; j < fieldArr.length; j++){
				createCell(row22,idxCell++, getFormatToStr(info,fieldArr[j]));
			}
		}
        //输出到磁盘中
        FileOutputStream fos = new FileOutputStream((new StringBuilder(String.valueOf(outPutPath))).append(filename).append(".xls").toString());
        workbook.write(fos);
        fos.close();
        File file = new File((new StringBuilder(String.valueOf(outPutPath))).append(filename).append(".xls").toString());
    	return file;
    }
    
    /**
     * 字符串取值
     * @param map
     * @param fileName
     * @return	
     * @throws Exception 
     */
	private static String getFormatToStr(Map map, String fileName ) throws Exception {
		String value = null;
		value = map.get(fileName) == null ? "" : map.get(fileName).toString();
		return value; 
	}
	
    /**
     * 
     * @param workbook
     * @param fontsize
     * @return 单元格样式
     */
    private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontsize) {
        // TODO Auto-generated method stub
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        //创建字体
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints(fontsize);
        //加载字体
        style.setFont(font);
        return style;
    }
    
    /**
     * 字符串取值
     */
	public static void createCell(HSSFRow row,int columIndex,String content){
		HSSFCell cell = row.createCell((short)columIndex);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(content);
	}
	
}
