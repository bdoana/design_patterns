package behavioral.strategy.util;

import behavioral.strategy.*;
import org.apache.commons.lang.*;

/**
 * Created by IntelliJ IDEA. User: oanad Date: 1/26/11 Time: 4:47 PM To change this template use
 * File | Settings | File Templates.
 */
public enum ProjectErrorCodes {


    NONE("NONE", null, null, null, null),

    /* Coduri de eroare pentru validarile sheet-ului 2  */
    SH2_016("SH2_016", ErrorType.BUSINESS, null, 1, 6),

    SH2_022("SH2_022", ErrorType.BUSINESS, Sh2CellCriteriaCNP.class, null, 7),

    SH2_036("SH2_036", ErrorType.BUSINESS, Sh2CellCriteriaDihotomic.class, null, 21)
    ;

    private String code;
    private ErrorType type;
    private Class<? extends CriteriaBase> criteriaClass;
    private Integer row;
    private Integer cell;


    ProjectErrorCodes(String code, ErrorType type, Class<? extends CriteriaBase>
            criteriaClass, Integer row, Integer cell)
    {
        this.code = code;
        this.type = type;
        this.criteriaClass = criteriaClass;
        this.row = row;
        this.cell = cell;
    }

    public String getCode() {
        return code;
    }

    public ErrorType getType() {
        return type;
    }

    public Class<? extends CriteriaBase> getCriteriaClass() {
        return criteriaClass;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getCell() {
        return cell;
    }

    public static ProjectErrorCodes[] sheet2ErrorCodes = { ProjectErrorCodes.SH2_022,
                                                           ProjectErrorCodes.SH2_036};


    private static ProjectErrorCodes getErrorForCell(ProjectErrorCodes[] errorCodes, int row,
        int cell)
    {
        for(ProjectErrorCodes error : values()) {
            if(ArrayUtils.contains(errorCodes, error) &&
                Integer.valueOf(row).equals(error.getRow()) && Integer.valueOf(cell)
                .equals(error.getCell()))
            {
                return error;
            }
        }
        return ProjectErrorCodes.NONE;
    }

    public static ProjectErrorCodes getSheet2ErrorForCell(int cell) {
        for(ProjectErrorCodes error : values()) {
            if(ArrayUtils.contains(sheet2ErrorCodes, error) &&
                Integer.valueOf(cell).equals(error.getCell()))
            {
                return error;
            }
        }
        return ProjectErrorCodes.NONE;
    }
}
