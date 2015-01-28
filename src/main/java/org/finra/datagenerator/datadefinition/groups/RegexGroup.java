package org.finra.datagenerator.datadefinition.groups;

import org.finra.datagenerator.consumer.DataPipe;
import org.finra.datagenerator.consumer.EquivalenceClassTransformer;

/**
 * Marshall Peters
 * Date: 1/27/15
 */
public class RegexGroup implements CoverageGroupSpecification {

    private String expression;

    private EquivalenceClassTransformer eq;
    private DataPipe dataPipe;

    public RegexGroup(String expression) {
        this.expression = expression;
        eq = new EquivalenceClassTransformer();
        dataPipe = new DataPipe();
    }

    public RegexGroup() {
        eq = new EquivalenceClassTransformer();
        dataPipe = new DataPipe();
    }

    public String getExpression() {
        return expression;
    }

    public String sampleValue() {
        dataPipe.getDataMap().put("var", "%regex(" + expression + ")");
        eq.transform(dataPipe);
        return dataPipe.getDataMap().get("var");
    }

    public boolean wellFormed() {
        return expression != null;
    }
}
