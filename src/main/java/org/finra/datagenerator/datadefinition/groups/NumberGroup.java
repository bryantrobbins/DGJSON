package org.finra.datagenerator.datadefinition.groups;

import org.finra.datagenerator.consumer.DataPipe;
import org.finra.datagenerator.consumer.EquivalenceClassTransformer;

import java.math.BigDecimal;

/**
 * Marshall Peters
 * Date: 1/27/15
 */
public class NumberGroup implements CoverageGroupSpecification {

    private int wholeDigits;
    private int fractionalDigits;
    private boolean bounded;
    private String mustBeAbove;
    private String mustBeBelow;

    private EquivalenceClassTransformer eq;
    private DataPipe dataPipe;

    public NumberGroup(int wholeDigits, int fractionalDigits, boolean bounded, String above, String below) {
        this.wholeDigits = wholeDigits;
        this.fractionalDigits = fractionalDigits;
        this.bounded = bounded;
        this.mustBeAbove = above;
        this.mustBeBelow = below;
        eq = new EquivalenceClassTransformer();
        dataPipe = new DataPipe();
    }

    public NumberGroup() {
        eq = new EquivalenceClassTransformer();
        dataPipe = new DataPipe();
    }

    public String sampleValue() {
        dataPipe.getDataMap().put("var", "%number(" + wholeDigits + "," + fractionalDigits + ")");
        eq.transform(dataPipe);

        BigDecimal producedNumber = new BigDecimal(dataPipe.getDataMap().get("var"));

        if (Math.random() > 0.5) {
            producedNumber = producedNumber.multiply(new BigDecimal(-1));
        }

        if (bounded) {
            BigDecimal aboveDec = new BigDecimal(mustBeAbove);
            BigDecimal belowDec = new BigDecimal(mustBeBelow);

            if (aboveDec.compareTo(producedNumber) > 0) {
                producedNumber = aboveDec;
            } else if (belowDec.compareTo(producedNumber) < 0) {
                producedNumber = belowDec;
            }
        }

        return producedNumber.toString();
    }

    public int getWholeDigits() {
        return wholeDigits;
    }

    public int getFractionalDigits() {
        return fractionalDigits;
    }

    public boolean isBounded() {
        return bounded;
    }

    public String getMustBeAbove() {
        return mustBeAbove;
    }

    public String getMustBeBelow() {
        return mustBeBelow;
    }

    public boolean wellFormed() {
        if (wholeDigits < 0 || wholeDigits > 100)
            return false;

        if (fractionalDigits < 0 || fractionalDigits > 100)
            return false;

        if (bounded) {
            if (mustBeAbove == null)
                return false;

            if (mustBeBelow == null)
                return false;

            BigDecimal aboveDec = new BigDecimal(mustBeAbove);
            BigDecimal belowDec = new BigDecimal(mustBeBelow);

            if (aboveDec.compareTo(belowDec) > 0)
                return false;
        }
        return true;
    }
}
