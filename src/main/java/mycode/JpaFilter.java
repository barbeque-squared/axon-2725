package mycode;

import org.hibernate.boot.model.relational.Namespace;
import org.hibernate.boot.model.relational.Sequence;
import org.hibernate.mapping.Table;
import org.hibernate.tool.schema.spi.SchemaFilter;

import java.util.List;

public class JpaFilter implements SchemaFilter {
    public static final JpaFilter INSTANCE = new JpaFilter();
    /**
     * in this list, put the table names you don't want to write a migration for yet<br/>
     * <b>BE WARNED</b>: trying to actually access these on non-development <b>WILL</b> cause crashes!
     */
    private static final List<String> DEVELOPMENT_TABLES = List.of();

    @Override
    public boolean includeNamespace(Namespace namespace) {
        return true;
    }

    @Override
    public boolean includeTable(Table table) {
        return !DEVELOPMENT_TABLES.contains(table.getName());
    }

    @Override
    public boolean includeSequence(Sequence sequence) {
        return true;
    }
}
