package org.finra.datagenerator.datadefinition.groups;

/**
 * Marshall Peters
 * Date: 1/27/15
 */
public class LiteralGroup implements CoverageGroupSpecification {

    private String literal;

    public LiteralGroup(String literal) {
        this.literal = literal;
    }

    public LiteralGroup() {

    }

    public String sampleValue() {
        return literal;
    }

    public boolean wellFormed() {
        return literal != null;
    }

    public String getLiteral() {
        return literal;
    }
}
