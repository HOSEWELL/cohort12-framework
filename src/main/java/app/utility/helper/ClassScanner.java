package app.utility.helper;

import app.framework.Action;
import app.framework.DbTable;
import org.reflections.Reflections;

import java.util.HashSet;
import java.util.Set;

public class ClassScanner {

    public Set<Class<?>> scanForDbTables(String basePackage) {

        Reflections reflections = new Reflections(basePackage);

        Set<Class<?>> annotatedClasses =
                reflections.getTypesAnnotatedWith(DbTable.class);

        return new HashSet<>(annotatedClasses);
    }

    public static Set<Class<?>> scanForAction(String basePackage) {
        Reflections reflections = new Reflections(basePackage);
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(Action.class);
        return new HashSet<>(annotatedClasses);
    }
}