package behavioral.strategy.util;

import java.util.*;

public enum Check {
    da("Da"),
    nu("Nu");
    private String label;

    Check(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static List<String> getLabels() {
        List<String> labels = new ArrayList<String>();
        for(Check value : Check.values()) {
            labels.add(value.getLabel());
        }
        return labels;
    }
}
