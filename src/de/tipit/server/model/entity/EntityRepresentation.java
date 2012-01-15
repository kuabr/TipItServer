package de.tipit.server.model.entity;

import java.util.SortedMap;

import javax.persistence.Transient;

public abstract class EntityRepresentation {

    @Transient
    public abstract String getEntityName();

    @Transient
    public abstract String getIdAsString();

    @Transient
    public abstract SortedMap<String, Object> getDataAsMap();

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        // add entity name and id
        result.append(this.getEntityName());
        result.append("(id=");
        try {
            result.append(this.getIdAsString());
        } catch (NullPointerException e) {
            result.append("null");
        }

        // add attributes
        SortedMap<String, Object> data = this.getDataAsMap();
        for (String dataKey : data.keySet()) {
            Object dataVal = data.get(dataKey);

            result.append(", ");
            result.append(dataKey);
            result.append("=");

            if (dataVal != null) {
                if (dataVal instanceof String) {
                    result.append("'");
                    result.append((String) dataVal);
                    result.append("'");
                } else {
                    result.append(dataVal.toString());
                }
            } else {
                result.append("null");
            }
        }

        // add closing bracket
        result.append(")");

        // return string
        return result.toString();
    }
}
