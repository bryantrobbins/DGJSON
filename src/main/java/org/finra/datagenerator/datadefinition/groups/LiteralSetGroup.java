package org.finra.datagenerator.datadefinition.groups;

/**
 * Marshall Peters
 * Date: 1/27/15
 */
public class LiteralSetGroup implements CoverageGroupSpecification {

    private String[] literals;

    public LiteralSetGroup(String[] literals) {
        this.literals = literals;
    }

    public LiteralSetGroup() {

    }

    public String[] getLiterals() {
        return literals;
    }

    public String sampleValue() {
        int choice = (int) (Math.random() * literals.length);
        return literals[choice];
    }

    public boolean wellFormed() {
        if (literals == null)
            return false;

        if (literals.length == 0 || literals.length > 50)
            return false;

        return true;
    }
}
