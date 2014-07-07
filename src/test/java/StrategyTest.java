
import behavioral.strategy.*;
import org.apache.poi.hssf.usermodel.*;
import org.junit.*;
import org.testng.*;
import org.testng.annotations.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * StrategyTest.java
 * Copyright (c) 2013 Teamnet. All Rights Reserved.
 * <p/>
 * This source file may not be copied, modified or redistributed,
 * in whole or in part, in any form or for any reason, without the express
 * written consent of Teamnet.
 */
@Test
public class StrategyTest {


    @org.junit.Test
    public void testCheckWorkbook() throws Exception {

        HSSFWorkbook workbook = new HSSFWorkbook();

        XLSValidator xlsValidator = new XLSValidator();
        xlsValidator.validateGeneralSheetCriteria(workbook, Sh2CellCriteriaCNP.class);
//        xlsValidator.validateCellCriteriaInstance(workbook, Sh2CellCriteriaDihotomic.class);

//        System.out.println(xlsValidator.getWorkbookErrors().get(0));
//        System.out.println(xlsValidator.getWorkbookErrors().get(1));
        System.out.println("xlsValidator = " + xlsValidator.getWorkbookErrors().size());
        org.junit.Assert.assertEquals(xlsValidator.getWorkbookErrors().isEmpty(), true);
    }
}
