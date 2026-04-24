package app.framework;

import app.utility.helper.ClassScanner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cohort12Framework {

    public static String htmlForm(Class<?> clazz){

        if (!clazz.isAnnotationPresent(Cohort12Form.class))
            return "";

        Cohort12Form formAnnot = clazz.getAnnotation(Cohort12Form.class);

        StringBuilder formBuilder = new StringBuilder();
        formBuilder.append("<div class='card'>");
        formBuilder.append("<h2>" + formAnnot.label() + "</h2>");
        formBuilder.append("<form method='" + formAnnot.method() + "' action='" + formAnnot.actionUrl() + "'>");

        formBuilder.append("<div class='form-group'>");
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Cohort12FormField.class))
                continue;

            Cohort12FormField fieldInfo = field.getAnnotation(Cohort12FormField.class);
            formBuilder.append("<label>" + fieldInfo.label() + ":</label>");
            formBuilder.append("<input type='text' name='"
                + (fieldInfo.name().isEmpty()? field.getName() : fieldInfo.name()) + "' placeholder='Enter " + fieldInfo.placeholder() + "' required />");
        }
        formBuilder.append("</div>");

        formBuilder.append("<button type='submit' class='btn'>Register</button>");
        formBuilder.append("</form>");

        if (clazz.isAnnotationPresent(Cohort12Table.class)) {
            Cohort12Table cohort12Table = clazz.getAnnotation(Cohort12Table.class);
            formBuilder.append("<a href=\"" + cohort12Table.tableUrl() + "\"  class='back-link'>&larr; List Registered " + cohort12Table.label() + " </a>");
        }

        formBuilder.append("</div>");

        return formBuilder.toString();
    }

    public static String htmlTable(Class<?> clazz,
         List<?> tableData) {

        if (!clazz.isAnnotationPresent(Cohort12Table.class))
            return "";

        Cohort12Table cohort12Table = clazz.getAnnotation(Cohort12Table.class);

        StringBuilder tableBuilder = new StringBuilder();

        tableBuilder.append("<div class='container'>");
        tableBuilder.append("<div class='card'>");
        tableBuilder.append("<h2>")
            .append(cohort12Table.label())
            .append(" Registered</h2>");

        tableBuilder.append("<table style='border-collapse: collapse; width: 50%; font-family: Arial, sans-serif;'>");

        List<String> fieldNames = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Cohort12TableCol.class))
                continue;

            fieldNames.add(field.getName());
        }

        tableBuilder.append("<thead><tr>");
        for (String fieldName : fieldNames)
            tableBuilder.append("<th>").append(fieldName).append("</th>");
        tableBuilder.append("</tr></thead>");

        for (Object data : tableData) {
            tableBuilder.append("<tr>");
            for (String fieldName : fieldNames) {
                try {
                    Field field = data.getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    tableBuilder.append("<td style='border: 1px solid #000; padding: 8px;'>"
                            + field.get(data) + "</td>");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
            tableBuilder.append("</tr>");
        }

        tableBuilder.append("</table>");

        tableBuilder.append("<a href=\"" + cohort12Table.registerUrl() + "\">&larr; Register " + cohort12Table.label() + " </a>");
        tableBuilder.append("</div>");
        tableBuilder.append("</div>");

        return tableBuilder.toString();

    }

    public static String generateMenuItem(){
        Set<Class<?>> entities = ClassScanner.scanForMenuItem("app.model");

        return entities.stream()
            .filter(clazz -> clazz.isAnnotationPresent(PageMenuItem.class))
            .map(clazz -> {
                PageMenuItem annotation = clazz.getAnnotation(PageMenuItem.class);
                return "<a href='./" + annotation.url() + "'>" + annotation.label() + "</a>";
            })
            .collect(Collectors.joining("\n"));
    }
}
