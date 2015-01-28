package org.finra.datagenerator.datadefinition.coverage;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.util.List;
import java.util.Map;

/**
 * Marshall Peters
 * Date: 1/27/15
 */

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = RandomCoverageType.class, name = "random"),
        @JsonSubTypes.Type(value = AllCombosCoverageType.class, name = "allCombos"),
        @JsonSubTypes.Type(value = NWiseCoverageType.class, name = "nWise")})
public interface CoverageType {

    public List<Map<String, String>> produceRows(Map<String, List<String>> variableGroups, int desiredNumberOfRows);
}
