package behavioral.strategy;

import behavioral.strategy.util.*;
import org.apache.commons.lang.*;
import org.apache.poi.ss.usermodel.*;

/**
 * Created by IntelliJ IDEA. User: OanaD Date: 08.02.2011 Time: 12:13 To change this template use
 * File | Settings | File Templates.
 */
public class Sh2CellCriteriaDihotomic extends CriteriaBase {
    @Override
    public FileErrors validate() {
        String cellValue = XlsUtility.cast(getCell());
        if(StringUtils.isNotEmpty(cellValue) && getCell().getCellType() != Cell.CELL_TYPE_BLANK
            && !Check.getLabels().contains(cellValue))
        {
            return new FileErrors();
        }
        return null;
    }
}
