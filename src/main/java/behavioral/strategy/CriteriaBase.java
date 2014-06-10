package behavioral.strategy;

import org.apache.poi.hssf.usermodel.*;

/**
 * Created by IntelliJ IDEA.
 */
public abstract class CriteriaBase {


    public abstract FileErrors validate();

    private HSSFWorkbook workbook;

    public HSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(HSSFWorkbook workbook) {
        this.workbook = workbook;
    }
}
