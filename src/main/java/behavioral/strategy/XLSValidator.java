package behavioral.strategy;

import behavioral.strategy.util.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.context.annotation.*;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 * Main object for managing validation rules. Created by IntelliJ IDEA. User: oanad Date: 1/25/11
 * Time: 2:33 PM To change this template use File | Settings | File Templates.
 */
@Scope(value = "prototype")
public class XLSValidator {

    public List<FileErrors> workbookErrors = new ArrayList<FileErrors>();

    private HSSFWorkbook workbook;

    public List<FileErrors> getWorkbookErrors() {
        return workbookErrors;
    }


    /**
     * Metoda verifica validari generale la nivel de sheet (vizibilitate, denumire)
     * @param workbook - obiectul POI ce contine datele intregului fisier XLS
     * @param criteriaClass - clasa ce va fi instantiata pentru a procesa verificarile
     */
    public void validateGeneralSheetCriteria(HSSFWorkbook workbook,
        Class<? extends CriteriaBase> criteriaClass)
    {
        FileErrors error;
        try {
            CriteriaBase criteria = criteriaClass.newInstance();
            criteria.setWorkbook(workbook);
// se va apela metoda validate a instantei respective
            error = criteria.validate();
        } catch(InstantiationException e) {
            throw new RuntimeException("Eroare la instantiere criterii generale", e);
        } catch(IllegalAccessException ex) {
            throw new RuntimeException("Eroare la accesare criterii generale", ex);
        } catch(Exception ex2) {
            throw new RuntimeException(
                "Eroare la validare criteriu general", ex2);
        }
        if(error != null) {
            workbookErrors.add(error);
        }
    }

    /**
     * Metoda verifica validarile pentru fiecare cell-urile primului sheet (text static)
     * @param cell
     * @param errorCode
     * @return
     */
    public FileErrors validateCellCriteriaInstance(HSSFCell cell, ProjectErrorCodes errorCode)
    {
        FileErrors error = null;
        try {
// instantiem clasa de validare a campului
            CriteriaBase criteria = errorCode.getCriteriaClass().newInstance();
// setam cell-ul curent
            criteria.setWorkbook(getWorkbook());
            criteria.setCell(cell);
// validare camp
            error = criteria.validate();
        } catch(InstantiationException e) {
            throw new RuntimeException(
                    "Eroare la instantiere criteriu celula " + XlsUtility
                            .getCellRefString(cell.getRowIndex(), cell.getColumnIndex()), e);
        } catch(IllegalAccessException ex) {
            throw new RuntimeException(
                    "Eroare la accesare criteriu celula " + XlsUtility
                            .getCellRefString(cell.getRowIndex(), cell.getColumnIndex()), ex);
        } catch(Exception ex2) {
            throw new RuntimeException(
                    "Eroare la validare criteriu pentru celula: " + XlsUtility
                            .getCellRefString(cell.getRowIndex(), cell.getColumnIndex()) + " din sheet-ul "
                            + cell.getSheet().getSheetName(), ex2);
        }
        return error;
    }

    public HSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(HSSFWorkbook workbook) {
        this.workbook = workbook;
    }
}
