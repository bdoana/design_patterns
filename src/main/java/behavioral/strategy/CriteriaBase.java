package behavioral.strategy;

import behavioral.strategy.util.*;
import org.apache.poi.hssf.usermodel.*;

/**
 * Created by IntelliJ IDEA.
 */
public abstract class CriteriaBase {


    public abstract FileErrors validate();

    private HSSFWorkbook workbook;

    private HSSFCell cell;

    public HSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(HSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public HSSFCell getCell() {
        return cell;
    }

    public void setCell(HSSFCell cell) {
        this.cell = cell;
    }
}
