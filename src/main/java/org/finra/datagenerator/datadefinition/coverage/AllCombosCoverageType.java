package org.finra.datagenerator.datadefinition.coverage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Marshall Peters
 * Date: 1/26/15
 */
public class AllCombosCoverageType implements CoverageType {

    private void cartesian(List<Map<String, String>> resultExpansions, String[] variables,
                           Map<String, List<String>> variableDomains, int nextVariable,
                           Map<String, String> partialAssignment, int desiredNumberOfRows) {
        if (resultExpansions.size() >= desiredNumberOfRows) {
            return;
        }

        if (nextVariable >= variables.length) {
            Map<String, String> resultAssignment = new HashMap<>(partialAssignment);
            resultExpansions.add(resultAssignment);
            return;
        }

        String variable = variables[nextVariable];
        for (String domainValue : variableDomains.get(variable)) {
            partialAssignment.put(variable, domainValue);
            cartesian(resultExpansions, variables, variableDomains, nextVariable + 1,
                    partialAssignment, desiredNumberOfRows);
        }
    }

    public List<Map<String,String>> produceRows(Map<String,List<String>> variableGroups, int desiredNumberOfRows) {
        List<Map<String,String>> producedRows = new ArrayList<>(desiredNumberOfRows);
        String[] variables = variableGroups.keySet().toArray(new String[variableGroups.keySet().size()]);

        while (producedRows.size() < desiredNumberOfRows) {
            cartesian(producedRows, variables, variableGroups, 0, new HashMap<String, String>(), desiredNumberOfRows);
        }

        return producedRows;
    }
}
