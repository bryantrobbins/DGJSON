package org.finra.datagenerator.datadefinition.groups;

import org.finra.datagenerator.consumer.DataPipe;
import org.finra.datagenerator.consumer.EquivalenceClassTransformer;

/**
 * Marshall Peters
 * Date: 1/27/15
 */
public class DigitSequenceGroup implements CoverageGroupSpecification {

    private int length;

    private EquivalenceClassTransformer eq;
    private DataPipe dataPipe;

    public DigitSequenceGroup(int length) {
        this.length = length;
        eq = new EquivalenceClassTransformer();
        dataPipe = new DataPipe();
    }

    public DigitSequenceGroup() {
        eq = new EquivalenceClassTransformer();
        dataPipe = new DataPipe();
    }

    public int getLength() {
        return length;
    }

    public String sampleValue() {
        dataPipe.getDataMap().put("var", "%digits(" + length + ")");
        eq.transform(dataPipe);
        return  dataPipe.getDataMap().get("var");
    }

    public boolean wellFormed() {
        if (length <= 0 || length > 1024)
            return false;

        return true;
    }
}
