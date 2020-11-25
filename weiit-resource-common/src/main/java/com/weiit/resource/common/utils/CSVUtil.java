package com.weiit.resource.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class CSVUtil {
	
	/**
	 * 生成csv文件到指定目录
	 */
	public static File createListCSV(List results,String titles ,String fields,String outPutPath, String filename) throws Exception {
    	File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {
            csvFile = new File(outPutPath + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GBK"), 1024);
            StringBuilder csvSbr = new StringBuilder();
            csvSbr.append(titles);
    		csvSbr.append("\r\n");
    		String fieldArr[]=fields.split(",");
    		List fieldList = Arrays.asList(fieldArr);
    		for(int i = 0,len = results.size();i<len;i++){
    			Map map1 = (Map)results.get(i);
    			for (int j = 0; j < fieldArr.length; j++) {
    				csvSbr.append(getFormatToStr(map1,fieldArr[j])).append(", ");
    			}
            	csvSbr.append("\r\n");
    		}
    		csvFileOutputStream.write(csvSbr.toString());
    		csvFileOutputStream.flush();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                csvFileOutputStream.close();  
            } catch (IOException e) {  
                e.printStackTrace();
            }  
        }
		return csvFile;
	}

	private static String getFormatToStr(Map map, String fileName) throws Exception {
		String value = null;
		value = map.get(fileName) == null ? "" : map.get(fileName).toString();
		return value; 
	}
	
    public static File createCSVFile(List exportData, Map rowMapper,String outPutPath, String filename) {
        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {
            csvFile = new File(outPutPath + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GBK"), 1024);
            // 写入文件头部
            for (Iterator propertyIterator = rowMapper.entrySet().iterator(); propertyIterator.hasNext();) {
                java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
                csvFileOutputStream.write("\""+ propertyEntry.getValue().toString() + "\"");
                if (propertyIterator.hasNext()) {
                    csvFileOutputStream.write(",");
                }
            }
            csvFileOutputStream.newLine();
            // 写入文件内容
            for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
            	LinkedHashMap  row = (LinkedHashMap) iterator.next();
            	for (Iterator propertyIterator = row.entrySet().iterator(); propertyIterator.hasNext();) {
	            	java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
	            	csvFileOutputStream.write("\"" + propertyEntry.getValue().toString() + "\"");
	            	if (propertyIterator.hasNext()){
	            		csvFileOutputStream.write(",");
	            	}
	            }
            	if (iterator.hasNext()) {  
	                csvFileOutputStream.newLine();  
	            }  
            }
            csvFileOutputStream.flush();  
        } catch (Exception e) {  
           e.printStackTrace();  
        } finally {  
           try {  
                csvFileOutputStream.close();  
            } catch (IOException e) {  
               e.printStackTrace();
           }  
       }  
        return csvFile;
    }
    
}
