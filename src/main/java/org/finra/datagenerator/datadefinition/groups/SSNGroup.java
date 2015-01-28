package org.finra.datagenerator.datadefinition.groups;

import org.finra.datagenerator.consumer.DataPipe;
import org.finra.datagenerator.consumer.EquivalenceClassTransformer;

/**
 * Marshall Peters
 * Date: 1/27/15
 */
public class SSNGroup implements CoverageGroupSpecification {

    private EquivalenceClassTransformer eq;
    private DataPipe dataPipe;

    public SSNGroup() {
        eq = new EquivalenceClassTransformer();
        dataPipe = new DataPipe();
    }

    public String sampleValue() {
        dataPipe.getDataMap().put("var", "%ssn");
        eq.transform(dataPipe);
        return  dataPipe.getDataMap().get("var");
    }

    public boolean wellFormed() {
        return true;
    }
}
