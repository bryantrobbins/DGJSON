package org.finra.datagenerator.datadefinition.groups;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;

/**
 * Marshall Peters
 * Date: 1/26/15
 */

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "group")
@JsonSubTypes({@Type(value = AlphaGroup.class, name = "alpha"),
        @Type(value = DigitSequenceGroup.class, name = "digitSequence"),
        @Type(value = LiteralGroup.class, name = "literal"),
        @Type(value = LiteralSetGroup.class, name = "literalSet"),
        @Type(value = NumberGroup.class, name = "number"),
        @Type(value = RegexGroup.class, name = "regex"),
        @Type(value = SSNGroup.class, name = "ssn")})
public interface CoverageGroupSpecification {

    public String sampleValue();

    public boolean wellFormed();

}
