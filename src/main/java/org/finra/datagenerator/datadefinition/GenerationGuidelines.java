package org.finra.datagenerator.datadefinition;

import org.finra.datagenerator.datadefinition.coverage.CoverageType;

/**
 * Marshall Peters
 * Date: 1/26/15
 */
public class GenerationGuidelines {

    private int desiredLineCount;
    private char columnDelimiter;
    private CoverageType coverageType;

    public GenerationGuidelines(int desiredLineCount, char columnDelimiter, CoverageType coverageType) {
        this.desiredLineCount = desiredLineCount;
        this.columnDelimiter = columnDelimiter;
        this.coverageType = coverageType;
    }

    public GenerationGuidelines() {

    }

    public boolean isValid() {
        if (columnDelimiter == 0)
            return false;

        if (coverageType == null)
            return false;

        if (desiredLineCount <= 0 || desiredLineCount > 100000)
            return false;

        return true;
    }

    public CoverageType getCoverageType() {
        return coverageType;
    }

    public int getDesiredLineCount() {
        return desiredLineCount;
    }


    public char getColumnDelimiter() {
        return columnDelimiter;
    }
}
