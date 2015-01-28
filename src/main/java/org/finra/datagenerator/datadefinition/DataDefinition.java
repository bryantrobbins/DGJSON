package org.finra.datagenerator.datadefinition;

import java.util.List;

/**
 * Marshall Peters
 * Date: 1/26/15
 */
public class DataDefinition {

    private GenerationGuidelines generationGuidelines;
    private List<VariableSpecification> variables;

    public DataDefinition() {

    }

    public DataDefinition(GenerationGuidelines generationGuidelines, List<VariableSpecification> variables) {
        this.generationGuidelines = generationGuidelines;
        this.variables = variables;
    }

    public boolean isValid() {
        if (generationGuidelines == null)
            return false;

        if (!generationGuidelines.isValid())
            return false;

        if (variables == null)
            return false;

        if (variables.size() == 0 || variables.size() > 50)
            return false;

        for (VariableSpecification variableSpecification : variables) {
            if (!variableSpecification.isValid())
                return false;
        }

        return true;
    }

    public GenerationGuidelines getGenerationGuidelines() {
        return generationGuidelines;
    }

    public List<VariableSpecification> getVariables() {
        return variables;
    }
}
