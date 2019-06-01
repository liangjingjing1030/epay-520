//package org.epay.mgr.utils;
//
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.util.StreamUtils;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ExcelExportUtil {
//
//    @Value("${receipt.path}")
//    static String dealReceiptPath;
//
//    // 电子表格字节数组
//    private static final byte[] receiptBuff;
//
//    static{
//        try {
//            receiptBuff = getReceiptTemplate();// 电子表格字节数组
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static byte[] getReceiptTemplate() throws IOException{
//        // 读取电子表格文件并返回输入流对象
//        String dealReceiptPdf = dealReceiptPath + File.separator + "dealReceipt.xls";
//        File pdf = new File(dealReceiptPdf);
//        InputStream in = new FileInputStream(pdf);
//        // 将读取的电子表格文件转换为字节数组
//        byte[] bs = StreamUtils.copyToByteArray(in);
//        in.close();
//        return bs;
//    }
//
//    public static void noPaper2Pdf(String dealReceiptPath, String date, String pdfFileName, List<String[]> strsList, String nativePdfPath) {
//        String NO_PAPER = dealReceiptPath + File.separator + "dealReceipt.xls";
//        InputStream nopaperInputStream = null;
//        BufferedOutputStream outputStream = null;
//        try {
//            // 输入流读取表格字节数组
//            nopaperInputStream = new ByteArrayInputStream(receiptBuff);
//            // 将数据读取到WorkBook中
//            Workbook workbook = fixWorkbookSingle(nopaperInputStream, strsList);
//            nopaperInputStream.close();
//            File file = new File(nativePdfPath);
//            File parentFile = file.getParentFile();
//            if(!parentFile.exists()) {
//                parentFile.mkdirs();
//            }
//            outputStream = new BufferedOutputStream(new FileOutputStream(file));
//            List<ExcelObject> objectList = new ArrayList<ExcelObject>();
//        } catch (Exception e) {
//
//        } finally {
//            if(inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(outputStream != null) {
//                try {
//                    outputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    private static Workbook fixWorkbookSingle(InputStream inputStream, List<String[]> strsList) throws IOException {
//        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
//        HSSFSheet sheet = workbook.getSheetAt(0);
//        if(strsList != null && strsList.size() > 0) {
//            for(String[] strs : strsList) {
//                if(strs.length != 3) {
//                    throw new RuntimeException("strsList的长度应该为3，实际为：" + strs.length);
//                }
//                sheet.getRow(Integer.parseInt(strs[1]))
//                        .getCell(Integer.parseInt(strs[2]))
//                        .setCellValue(strs[0]);
//            }
//        }
//        return workbook;
//    }
//
//}
