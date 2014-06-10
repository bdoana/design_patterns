package behavioral.strategy;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.context.annotation.*;

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
    private void validateGeneralSheetCriteria(HSSFWorkbook workbook,
        Class<? extends CriteriaBase> criteriaClass)
    {
        FileErrors error;
        try {
            CriteriaBase criteria = criteriaClass.newInstance();
            criteria.setWorkbook(workbook);
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

}
