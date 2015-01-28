package org.finra.datagenerator.datadefinition.coverage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Marshall Peters
 * Date: 1/27/15
 */
public class NWiseCoverageType implements CoverageType {

    private int nWise;

    public NWiseCoverageType(int nWise) {
        this.nWise = nWise;
    }

    public NWiseCoverageType() {

    }

    public int getnWise() {
        return nWise;
    }

    private void expandTuple(String[] variables, Map<String, List<String>> variableDomains,
                             int nextVariable, Map<String, String> partialAssignment,
                             List<Map<String,String>> producedRows, int desiredNumberOfRows, Map<String,String> defaultAssignments) {
        if (producedRows.size() >= desiredNumberOfRows) {
            return;
        }

        if (nextVariable >= variables.length) {
            Map<String, String> resultAssignment = new HashMap<>(defaultAssignments);
            resultAssignment.putAll(partialAssignment);
            producedRows.add(resultAssignment);

            return;
        }
        String variable = variables[nextVariable];
        for (String domainValue : variableDomains.get(variable)) {
            partialAssignment.put(variable, domainValue);
            expandTuple(variables, variableDomains, nextVariable + 1, partialAssignment, producedRows, desiredNumberOfRows, defaultAssignments);
        }
    }

    private void makeTestCases(String[] variables, int startVariable, Set<String> preGivenVariables,
                               int nWise, Map<String, List<String>> variableGroups,
                               List<Map<String, String>> producedRows, int desiredNumberOfRows, Map<String,String> defaultAssignments) {
        if (producedRows.size() >= desiredNumberOfRows) {
            return;
        }

        if (nWise <= 0) {
            Set<String> result = new HashSet<>(preGivenVariables);
            String[] tupleVariables = new String[result.size()];
            tupleVariables = result.toArray(tupleVariables);

            expandTuple(tupleVariables, variableGroups, 0, new HashMap<String, String>(), producedRows, desiredNumberOfRows, defaultAssignments);

            return;
        }
        for (int varIndex = startVariable; varIndex <= variables.length - nWise; varIndex++) {
            preGivenVariables.add(variables[varIndex]);
            makeTestCases(variables, varIndex + 1, preGivenVariables, nWise - 1, variableGroups, producedRows, desiredNumberOfRows, defaultAssignments);
            preGivenVariables.remove(variables[varIndex]);
        }
    }

    public List<Map<String, String>> produceRows(Map<String, List<String>> variableGroups, int desiredNumberOfRows) {
        List<Map<String,String>> producedRows = new ArrayList<>(desiredNumberOfRows);
        String[] variables = variableGroups.keySet().toArray(new String[variableGroups.keySet().size()]);

        Map<String,String> defaultAssignments = new HashMap<>();
        for (String variable : variableGroups.keySet()) {
            List<String> groupsForVariable = variableGroups.get(variable);
            defaultAssignments.put(variable, groupsForVariable.get(0));
        }

        while (producedRows.size() < desiredNumberOfRows) {
            makeTestCases(variables, 0, new HashSet<String>(), nWise, variableGroups, producedRows, desiredNumberOfRows, defaultAssignments);
        }

        return producedRows;
    }
}
