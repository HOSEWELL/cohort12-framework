package app.framework;

import app.utility.helper.ClassScanner;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cohort12Framework {

    public static void htmlForm(PrintWriter writer, Class<?> clazz){

        if (!clazz.isAnnotationPresent(Cohort12Form.class))
            return;

        Cohort12Form formAnnot = clazz.getAnnotation(Cohort12Form.class);

        writer.println("<h2>" + formAnnot.label() + "</h2>");
        writer.println("<form method='" + formAnnot.method() + "' action='" + formAnnot.actionUrl() + "'>");

        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Cohort12FormField.class))
                continue;

            Cohort12FormField fieldInfo = field.getAnnotation(Cohort12FormField.class);
            writer.println("<label>" + fieldInfo.label() + ":</label>");
            writer.println("<input type='text' name='"
                + (fieldInfo.name().isEmpty()? field.getName() : fieldInfo.name()) + "' placeholder='Enter " + fieldInfo.placeholder() + "' required />");
        }

        writer.println("<button type='submit'>Register</button>");
        writer.println("</form>");

        if (clazz.isAnnotationPresent(Cohort12Table.class)) {
            Cohort12Table cohort12Table = clazz.getAnnotation(Cohort12Table.class);
            writer.println("<section>");
            writer.println("<a href=\"" + cohort12Table.tableUrl() + "\">&larr; List Registered " + cohort12Table.label() + " </a>");
            writer.println("</section>");
        }
    }

    public static void htmlTable(PrintWriter writer, Class<?> clazz,
         List<?> tableData) {

        if (!clazz.isAnnotationPresent(Cohort12Table.class))
            return;

        Cohort12Table cohort12Table = clazz.getAnnotation(Cohort12Table.class);

        writer.println("<section>");
        writer.println("<h2>" + cohort12Table.label() + " Registered</h2>");
        writer.println("<p>");

        writer.println("<table style='border-collapse: collapse; width: 50%; font-family: Arial, sans-serif;'>");

        List<String> fieldNames = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Cohort12TableCol.class))
                continue;

            fieldNames.add(field.getName());
        }

        writer.println("<tr>");
        for (String fieldName : fieldNames) {

            // Header row
            writer.println("<th style='border: 1px solid #000; padding: 8px; background-color: #f2f2f2;'>" + fieldName + "</th>");
        }
        writer.println("</tr>");

        for (Object data : tableData) {
            writer.println("<tr>");
            for (String fieldName : fieldNames) {
                try {
                    Field field = data.getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    writer.println("<td style='border: 1px solid #000; padding: 8px;'>"
                            + field.get(data) + "</td>");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
            writer.println("</tr>");
        }

        writer.println("</table>");
        writer.println("</p>");
        writer.println("</section>");

// Navigation
        writer.println("<section>");
        writer.println("<a href=\"" + cohort12Table.registerUrl() + "\">&larr; Register " + cohort12Table.label() + " </a>");
        writer.println("</section>");

    }

    public static String generateMenuItem(){
        Set<Class<?>> entities = ClassScanner.scanForDbTables("app.model");

        return entities.stream()
            .filter(clazz -> clazz.isAnnotationPresent(PageMenuItem.class))
            .map(clazz -> {
                PageMenuItem annotation = clazz.getAnnotation(PageMenuItem.class);
                return "<a href='./" + annotation.url() + "'>" + annotation.label() + "</a>";
            })
            .collect(Collectors.joining("\n"));
    }
}
