package behavioral.strategy.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import static org.apache.poi.ss.usermodel.Cell.*;

public class XlsUtility {
    private HSSFRow hssfRow;
    private static final char[] A2Z =
            {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                    'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public XlsUtility(HSSFSheet sheet, int rowWithTextNo) {
        hssfRow = sheet.getRow(rowWithTextNo);
    }

    public XlsUtility(HSSFRow hssfRow) {
        this.hssfRow = hssfRow;
    }

    public static String cast(HSSFCell hssfCell) {
        String ret = null;
        Object val = getValue(hssfCell);
        if(val == null) {
            return null;
        }
        Class<?> valClass = val.getClass();
        if(valClass.equals(String.class)) {
            ret = (String) val;
        } else if(valClass.equals(double.class)) {
            ret = Long.valueOf(((Double) val).longValue()).toString();
        } else if(valClass.equals(Double.class)) {
            ret = Long.valueOf(((Double) val).longValue()).toString();
        }
        return ret != null ? ret.trim() : ret;
    }

    public static Object getValue(HSSFCell hssfCell) {
        Object ret = null;
        if(hssfCell == null) {
            return null;
        }
        switch(hssfCell.getCellType()) {
            case CELL_TYPE_BLANK:
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                ret = hssfCell.getBooleanCellValue();
                break;
            case CELL_TYPE_NUMERIC:
                ret = hssfCell.getNumericCellValue();
                break;
            case CELL_TYPE_STRING:
                ret = hssfCell.getStringCellValue();
                break;
            case CELL_TYPE_FORMULA:
                try{
                    ret=hssfCell.getStringCellValue();
                }catch (Exception e){

                    ret=hssfCell.getNumericCellValue();

                }
                break;
        }
        return ret;
    }

    /**
     * Returns the excel cell number (eg. C11, E4, AD1305 etc.) for this cell.
     * @param row row index
     * @param cellNum cell index
     * @return cell letter representation
     */
    public static String getCellRefString(int row, int cellNum) {
        StringBuffer retval = new StringBuffer();
        int tempcellnum = cellNum;
        do {
            retval.insert(0, A2Z[tempcellnum % 26]);
            tempcellnum = (tempcellnum / 26) - 1;
        } while(tempcellnum >= 0);
        retval.append(row + 1);
        return retval.toString();
    }
}
