package org.finra.datagenerator.datadefinition.coverage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Marshall Peters
 * Date: 1/26/15
 */
public class RandomCoverageType implements CoverageType {

    public List<Map<String,String>> produceRows(Map<String,List<String>> variableGroups, int desiredNumberOfRows) {
        List<Map<String,String>> producedRows = new ArrayList<>(desiredNumberOfRows);

        int currentRowCount = 0;
        while (currentRowCount < desiredNumberOfRows) {
            Map<String, String> nextRow = new HashMap<>();

            for (String variable : variableGroups.keySet()) {
                List<String> groupsForVariable = variableGroups.get(variable);

                int numberOfChoices = groupsForVariable.size();
                int choiceNumber = (int) (Math.random() * numberOfChoices);
                String chosenGroup = groupsForVariable.get(choiceNumber);

                nextRow.put(variable, chosenGroup);
            }

            currentRowCount++;
            producedRows.add(nextRow);
        }

        return producedRows;
    }

}
