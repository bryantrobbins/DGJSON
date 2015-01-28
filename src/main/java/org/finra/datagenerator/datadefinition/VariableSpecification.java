package org.finra.datagenerator.datadefinition;

import org.finra.datagenerator.datadefinition.groups.CoverageGroupSpecification;

import java.util.List;

/**
 * Marshall Peters
 * Date: 1/26/15
 */
public class VariableSpecification {

    private String name;
    private List<CoverageGroupSpecification> coverageGroups;

    public VariableSpecification() {

    }

    public VariableSpecification(String name, List<CoverageGroupSpecification> coverageGroups) {
        this.name = name;
        this.coverageGroups = coverageGroups;
    }

    public boolean isValid() {
        if (name == null)
            return false;

        if (coverageGroups == null)
            return false;

        if (coverageGroups.size() == 0 || coverageGroups.size() > 25)
            return false;

        for (CoverageGroupSpecification groupSpecification : coverageGroups) {
            if (groupSpecification == null)
                return false;

            if (!groupSpecification.wellFormed())
                return false;
        }

        return true;
    }

    public String getName() {
        return name;
    }

    public List<CoverageGroupSpecification> getCoverageGroups() {
        return coverageGroups;
    }
}
