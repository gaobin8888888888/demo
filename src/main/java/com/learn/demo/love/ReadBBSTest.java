package com.learn.demo.love;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author gaobin
 * @date 2021/9/29 9:59 上午
 * @desc
 */
public class ReadBBSTest {

    public static void main(String[] args) {

        /**
         * 8月bss 订单号---部门名称
         */
        Map<String, String> totalMap = readBbs("9-BSS");
        Map<String, String> total7Map = readBbs("8-BSS");
        totalMap.putAll(total7Map);

        /**
         * 订单号--money
         */
        String expressName = "嘉瑞德顺丰";
        Map<String, BbsModel> expressMap = readExpress(expressName);

        /**
         * 部门--money
         */
        Map<String, BbsModel> moneyMap = new HashMap<>();
        for (String no : expressMap.keySet()) {
            if (Objects.isNull(no) || no.trim().length() <= 0) {
                continue;
            }
            String shopName = totalMap.get(no);
            if (Objects.isNull(shopName) || shopName.length() <= 0) {
                System.out.println("no find: " + no);
                continue;
            }
            if (moneyMap.containsKey(shopName)) {
                BbsModel newModel = moneyMap.get(shopName);
                BigDecimal d = newModel.getMoney();

                BbsModel model = expressMap.get(no);
                d = d.add(model.getMoney());

                newModel.setMoney(d);
                newModel.setShopNum(newModel.getShopNum() + model.getShopNum());
                moneyMap.put(shopName, newModel);
            } else {
                BbsModel model = expressMap.get(no);
                moneyMap.put(shopName, model);
            }
        }
        for (String name : moneyMap.keySet()) {
            System.out.println("" + name + " : " + moneyMap.get(name));
        }

        createResultFile(moneyMap, expressName);
        System.out.println(111);

    }

    /**
     * 创建导出文件
     * @param moneyMap
     * @param expressName
     */
    public static void createResultFile(Map<String, BbsModel> moneyMap, String expressName) {
        // 第一步，一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("部门");
        cell = row.createCell((short) 1);
        cell.setCellValue("件数");
        cell = row.createCell((short) 2);
        cell.setCellValue("金额");

        // 第五步，写入实体数据
        int i = 1;
        for (String name : moneyMap.keySet()) {
            row = sheet.createRow((int)i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(name);
            BbsModel model = moneyMap.get(name);
            row.createCell((short) 1).setCellValue(model.getShopNum());
            row.createCell((short) 2).setCellValue(model.getMoney().toString());
            i++;
        }
        // 第六步，将文件存到指定位置
        try {
            FileOutputStream fout = new FileOutputStream("/Users/gaobin/work/github/demo/src/main/resources/result/" + expressName + "result.xlsx");
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取快递文件
     * @param expressName
     * @return
     */
    public static Map<String, BbsModel> readExpress(String expressName) {
        String filePath = "/Users/gaobin/work/github/demo/src/main/resources/express/" + expressName + ".xlsx";
        Workbook wb =null;
        Sheet sheet = null;
        Map<String, BbsModel> map = new HashMap<>();
        int sheetNo = 1;
        int noRow = 0;
        int monetRow = 1;

        if (expressName.contains("邮政")){
            sheetNo = 1;
        } else if (expressName.contains("京东")){
            sheetNo = 2;
        } else if (expressName.contains("顺丰")){
            sheetNo = 1;
        } else if (expressName.contains("韵达")){
            sheetNo = 0;
            noRow = 0;
            monetRow = 7;
        } else if (expressName.contains("圆通")){
            sheetNo = 1;
        }
        wb = readExcel(filePath);
        if(wb != null){
            //获取第一个sheet
            sheet = wb.getSheetAt(sheetNo);

            int i = 0;
            for (Row row : sheet) {
                if(row !=null){
                    if (i == 0){
                        i++;
                        continue;
                    }
                    Cell nocell = row.getCell(noRow);
                    Cell moneycell = row.getCell(monetRow);
                    if (nocell == null || moneycell == null || nocell.getStringCellValue().isEmpty() || moneycell.getStringCellValue().isEmpty()){
                        continue;
                    }
                    String no = nocell.getStringCellValue();

                    if (map.containsKey(no)){
                        BbsModel model = map.get(no);
                        BigDecimal bigDecimal = model.getMoney();
                        bigDecimal = bigDecimal.add(BigDecimal.valueOf(moneycell.getNumericCellValue()));
                        model.setShopNum(model.getShopNum() + 1);
                        model.setMoney(bigDecimal);
                        map.put(no, model);
                    } else {
                        BbsModel model = new BbsModel();
                        model.setShopNum(1);
                        model.setMoney(BigDecimal.valueOf(Double.valueOf(moneycell.getStringCellValue())));
//                        System.out.println("no:" + no + "       " + model.getMoney() + "        " + Double.valueOf(moneycell.getStringCellValue()));
                        map.put(no, model);
                    }
                }else{
                    break;
                }
            }
        }

        if (wb != null) {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 读取bss文件
     * @param bssName
     * @return
     */
    public static Map<String, String> readBbs(String bssName) {
        String filePath = "/Users/gaobin/work/github/demo/src/main/resources/file/" + bssName +  ".xlsx";
        Workbook wb =null;
        Sheet sheet = null;
        Map<String, String> map = new HashMap<>();

        wb = readExcel(filePath);
        if(wb != null){
            //获取第一个sheet
            sheet = wb.getSheetAt(0);

            for (Row row : sheet) {
                if(row !=null){
                    Cell nocell = row.getCell(0);
                    if (nocell == null){
                        continue;
                    }
                    String no = nocell.getStringCellValue();

                    Cell namecell = row.getCell(1);
                    if (namecell == null){
                        continue;
                    }
                    if (no.contains(",")) {
                        String[] noArr = no.split(",");
                        for (String arr : noArr) {
                            map.put(arr.trim(), namecell.getStringCellValue());
                        }
                    } else {
                        map.put(no.trim(), namecell.getStringCellValue());
                    }
                }else{
                    break;
                }
            }
        }

        if (wb != null) {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 读取xlsx
     * @param filePath
     * @return
     */
    private static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        File file = new File(filePath);
        System.out.println(file.length());
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            if(".xls".equals(extString)){
                return new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                wb = StreamingReader.builder()
                        .rowCacheSize(1000)
                        .bufferSize(10240)
                        .open(is);
                return wb;
            }else{
                return null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
}
