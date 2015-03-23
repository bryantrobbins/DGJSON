package org.finra.datagenerator;

import org.codehaus.jackson.map.ObjectMapper;
import org.finra.datagenerator.datadefinition.DataDefinition;
import org.finra.datagenerator.datadefinition.VariableSpecification;
import org.finra.datagenerator.datadefinition.coverage.CoverageType;
import org.finra.datagenerator.datadefinition.groups.CoverageGroupSpecification;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Marshall Peters
 * Date: 1/26/15
 */
public class CmdLine {

    public static void main(String[] args) {
        // Load simple args
				String outputKey = args[0];	
        String inputJSON = args[1];

        //Parse and validate the model
        ObjectMapper mapper = new ObjectMapper();
        DataDefinition dataDefinition = null;

        try {
            dataDefinition = mapper.readValue(inputJSON, DataDefinition.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        if (!dataDefinition.isValid()) {
            System.out.println("invalid");
            System.exit(-1);
        }

        //Produce rows
        Map<String, List<String>> variableGroups = new HashMap<>();
        for (VariableSpecification variable : dataDefinition.getVariables()) {
            List<String> groupsForVariable = new LinkedList<>();
            int currentGroupNumber = 0;
            for (CoverageGroupSpecification group : variable.getCoverageGroups()) {
                String currentGroupNumberName = String.valueOf(currentGroupNumber);
                groupsForVariable.add(currentGroupNumberName);
                currentGroupNumber++;
            }
            variableGroups.put(variable.getName(), groupsForVariable);
        }

        CoverageType coverageType = dataDefinition.getGenerationGuidelines().getCoverageType();
        int desiredNumberOfRows = dataDefinition.getGenerationGuidelines().getDesiredLineCount();

        List<Map<String, String>> rows = coverageType.produceRows(variableGroups, desiredNumberOfRows);

        //Fill in macros with values
        for (VariableSpecification variable : dataDefinition.getVariables()) {
            int currentGroupNumber = 0;
            for (CoverageGroupSpecification group : variable.getCoverageGroups()) {
                String currentGroupNumberName = String.valueOf(currentGroupNumber);

                for (Map<String, String> row : rows) {
                    if (currentGroupNumberName.equals(row.get(variable.getName()))) {
                        String sampledValue = group.sampleValue();
                        row.put(variable.getName(), sampledValue);
                    }
                }

                currentGroupNumber++;
            }
        }

        //Write out the results
        try {
            PrintWriter writer = new PrintWriter(outputKey, "UTF-8");

            for (Map<String, String> row : rows) {
                boolean  firstValue = true;
                StringBuilder rowOutput = new StringBuilder();
                for (VariableSpecification variable : dataDefinition.getVariables()) {
                    if (firstValue) {
                        firstValue = false;
                    } else {
                        rowOutput.append(dataDefinition.getGenerationGuidelines().getColumnDelimiter());
                    }

                    String variableValue = row.get(variable.getName());
                    rowOutput.append(variableValue);
                }
                writer.println(rowOutput.toString());
            }

            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println("output difficulty");
            System.exit(-1);
        }
    }
}
