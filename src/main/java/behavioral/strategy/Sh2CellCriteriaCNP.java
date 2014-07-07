package behavioral.strategy;

import behavioral.strategy.util.*;
import org.apache.commons.lang.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import java.util.*;

/**
 * Created by IntelliJ IDEA. User: OanaD Date: 08.02.2011 Time: 12:09 To change this template use
 * File | Settings | File Templates.
 */
public class Sh2CellCriteriaCNP extends CriteriaBase {
    private static final int NATIONALITY_COLUMN_INDEX = 15;
    private static final String NUMERIC_REGEX_PATTERN = "[0-9]+";
    private static final String NATIONALITATE = "Rom\u00E2n\u0103";

    @Override
    public FileErrors validate() {
        String cnp = XlsUtility.cast(getCell());
// CNP-ul este obligatoriu de completat
        if(StringUtils.isEmpty(cnp) || getCell().getCellType() == Cell.CELL_TYPE_BLANK) {
            return new FileErrors();
        } else {
// verificam daca nationalitatea aleasa este Romana (urmatoarele validari)
            HSSFCell nationalitate =
                getCell().getRow().getCell(NATIONALITY_COLUMN_INDEX);
            String nationalit = XlsUtility.cast(nationalitate);
            if(NATIONALITATE.equals(nationalit)) {
// verificam sa aiba lungimea de 13 caractere numerice
                if(!cnp.matches(NUMERIC_REGEX_PATTERN) || cnp.length() != 13) {
                    return new FileErrors();
                } else {
// verificam primul caracter - cifra sex: 1, 5 - masculin; 2, 6 - feminin; 9 - cetatean strain
                    int sexDigit = 0;
                    int firstDigit = cnp.charAt(0);
                    String yearSec = "19";
                    switch(firstDigit) {
                        case '1':
                            sexDigit = 1;
                            break;
                        case '5':
                            sexDigit = 1;
                            yearSec = "20";
                            break;
                        case '2':
                            sexDigit = 2;
                            break;
                        case '6':
                            sexDigit = 2;
                            yearSec = "20";
                            break;
                        case '7':
                        case '8':
                        case '9':
                            sexDigit = 3;
                            break;
                        default:
                            sexDigit = 0;
                            break;
                    }
// verificare cifra sex
                    if(sexDigit == 0) {
                        return new FileErrors();
                    }
// verificare cifre data calendaristica
                    Calendar calendar = Calendar.getInstance();
// "when a Calendar is in non-lenient mode, it throws an exception if there is any inconsistency in its calendar fields"
// setam lenient = false pentru a nu accepta o valoarea de genul 32 Ianuarie (ca si cum ar fi 1 Februrie)
                    calendar.setLenient(false);
                    String year = cnp.substring(1, 3);
                    String month = cnp.substring(3, 5);
                    String day = cnp.substring(5, 7);
                    try {
                        calendar.set(Integer.parseInt(yearSec + year), Integer.parseInt(month) - 1,
                            // luna este zero-based
                            Integer.parseInt(day));
                    } catch(NumberFormatException e1) {
                        throw new RuntimeException("Eroare la parsare cifre CNP", e1);
                    } catch(Exception e) {
                        return new FileErrors();
                    }
// verificare cifra de control
                    int cifraControl = Integer.parseInt(cnp.substring(12, 13));
                    int coeficienti[] = new int[]{2, 7, 9, 1, 4, 6, 3, 5, 8, 2, 7, 9};
                    int cifraCorecta = 0;
// se calculeaza cifra de corectie
                    for(int k = 0; k < 12; k++) {
                        cifraCorecta += Integer.parseInt(cnp.substring(k, k + 1)) * coeficienti[k];
                    }
                    cifraCorecta = cifraCorecta % 11;
                    if(cifraCorecta == 10) {
                        cifraCorecta = 1;
                    }
                    if(cifraCorecta != cifraControl) {
                        return new FileErrors();
                    }
                }
            }
        }
        return null;
    }

    /**
     * Folosim aceasta metoda pentru a verifica daca un CNP trebuie adaugat in lista de CNP-uri
     * pentru validare CNP duplicat sau nu. Pentru duplicate, consideram valid doar un camp CNP
     * completat si format din 13 cifre
     * @return
     */
    public boolean isCnpValidForDuplication() {
        String cnp = XlsUtility.cast(getCell());
        if(StringUtils.isEmpty(cnp) || getCell().getCellType() == Cell.CELL_TYPE_BLANK) {
            return false;
        }
        if(!cnp.matches(NUMERIC_REGEX_PATTERN) || cnp.length() != 13) {
            return false;
        }
        return true;
    }
}
