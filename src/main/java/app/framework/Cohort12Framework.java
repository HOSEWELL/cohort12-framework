package app.framework;

import app.utility.helper.ClassScanner;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class Cohort12Framework {

    @Inject
    private ClassScanner clazzScanner;

    private Map<String, List<SelectBox>> formSelections = new HashMap<>();

    @PostConstruct
    public void init() {
        System.out.println("****************Cohort12Framework Contextual Instance created ********");
        resetFormSelections();
    }

    public String htmlForm(Class<?> clazz){

        if (!clazz.isAnnotationPresent(Cohort12Form.class))
            return "";

        Cohort12Form formAnnot = clazz.getAnnotation(Cohort12Form.class);

        StringBuilder formBuilder = new StringBuilder();
        formBuilder.append("<div class='container'>");
        formBuilder.append("<div class='card'>");
        formBuilder.append("<h2>").append(formAnnot.label().toUpperCase()).append("</h2><br/><br/>");
        formBuilder.append("<form method='").append(formAnnot.method())
            .append("' action='")
            .append(ActionMap.APP_PATH)
            .append(formAnnot.actionUrl()).append("'>");

        formBuilder.append("<div class='form-group'>");
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Cohort12FormField.class))
                continue;

            Cohort12FormField fieldInfo = field.getAnnotation(Cohort12FormField.class);
            formBuilder.append("<label>").append(fieldInfo.label()).append(":</label>");
            if (!fieldInfo.select().equalsIgnoreCase("")
                && formSelections.containsKey(fieldInfo.select())) {
                    formBuilder.append("<select name='")
                        .append(field.getName())
                        .append("' required>");

                    formSelections.get(fieldInfo.select()).forEach(formSelection ->
                        formBuilder.append("<option value='").append(formSelection.getValue()).append("'>")
                        .append(formSelection.getName()).append("</option>"));

                formBuilder.append("</select>");

            } else {
                formBuilder.append("<input type='text' name='")
                    .append(fieldInfo.name().isEmpty() ? field.getName() : fieldInfo.name())
                    .append("' placeholder='Enter ")
                    .append(fieldInfo.placeholder())
                    .append("' required />");
            }
        }

        //reset form selection
        resetFormSelections();

        formBuilder.append("</div>");

        formBuilder.append("<button type='submit' class='btn'>Register</button>");
        formBuilder.append("</form>");

        formBuilder.append("</div>");
        formBuilder.append("</div>");

        return formBuilder.toString();
    }

    public String htmlTable(Class<?> clazz, List<?> tableData) {

        if (!clazz.isAnnotationPresent(Cohort12Table.class))
            return "";

        Cohort12Table cohort12Table = clazz.getAnnotation(Cohort12Table.class);

        StringBuilder tableBuilder = new StringBuilder();

        tableBuilder.append("<div class='container'>");
        tableBuilder.append("<div class='card'>");
        tableBuilder.append("<h2>")
            .append(cohort12Table.label())
            .append(" Registered</h2>");

        if (!cohort12Table.addLink().equalsIgnoreCase(""))
            tableBuilder.append("<a href=\"")
                .append(ActionMap.APP_PATH)
                .append(cohort12Table.addLink())
                .append("\" class='back-link'>&#43; Register ")
                .append(cohort12Table.label()).append(" </a>");

        tableBuilder.append("<table>");

        class ColMetaData {
            final String fieldName;
            final String columnName;

            ColMetaData(String fieldName, String columnName){
                this.fieldName = fieldName;
                this.columnName = columnName;
            }

        }

        List<ColMetaData> colsMedaData = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Cohort12TableCol.class))
                continue;

            Cohort12TableCol tableCol = field.getAnnotation(Cohort12TableCol.class);

            colsMedaData.add(new ColMetaData(field.getName(), tableCol.label()));
        }

        int colWidth = 96/colsMedaData.size();
        tableBuilder.append("<thead><tr>");
        for (ColMetaData colMedaData : colsMedaData)
            tableBuilder.append("<th style='width:")
                .append(colWidth).append("%;'>")
                .append(colMedaData.columnName).append("</th>");

        tableBuilder.append("<th style='width:2%;></th><th style='width:2%;></th>");
        tableBuilder.append("</tr></thead>");
        tableBuilder.append("<tbody>");

        for (Object data : tableData) {
            tableBuilder.append("<tr>");
            for (ColMetaData colMedaData : colsMedaData) {
                try {
                    Field field = data.getClass().getDeclaredField(colMedaData.fieldName);
                    field.setAccessible(true);
                    tableBuilder.append("<td style='border: 1px solid #000; padding: 8px;'>")
                        .append(field.get(data))
                        .append("</td>");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
            tableBuilder.append("<td class='actions'>");

            try {
                Field idField = clazz.getSuperclass().getDeclaredField("id");
                idField.setAccessible(true);

                /* EDIT BUTTON */
                tableBuilder.append("<a href='./edit_trainer?id=")
                    .append(idField.get(data))
                    .append("' class='icon-btn edit-btn' title='Edit'>");
                tableBuilder.append("<i class='fa-solid fa-pen'></i>");
                tableBuilder.append("</a>");

                /* DELETE BUTTON */
                tableBuilder.append("<a href='")
                    .append(ActionMap.APP_PATH)
                    .append(cohort12Table.deleteLink())
                    .append("/")
                    .append(idField.get(data))
                    .append("' class='icon-btn delete-btn' title='Delete' onclick='return confirm(\"Delete record?\")'>");
                tableBuilder.append("<i class='fa-solid fa-trash'></i>");
                tableBuilder.append("</a>");

            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            tableBuilder.append("</td>");

            tableBuilder.append("</tr>");

        }
        tableBuilder.append("</tbody>");
        tableBuilder.append("</table>");

        tableBuilder.append("</div>");
        tableBuilder.append("</div>");

        return tableBuilder.toString();

    }

    public String generateMenuItem(){
        return ClassScanner.scanForAction("app.action").stream()
            .map(clazz -> clazz.getAnnotation(Action.class))
            .filter(Objects::nonNull)
            .filter(Action::showLink)
            .sorted(Comparator.comparingInt(Action::linkPosition))
            .map(annotation -> "<a href='" + ActionMap.APP_PATH + annotation.value() + "/" + annotation.pageLink() + "'>"
                    + annotation.label() + "</a>")
            .collect(Collectors.joining("\n"));
    }

    public Map<String, List<SelectBox>> getFormSelections() {
        return formSelections;
    }

    public void setFormSelections(Map<String, List<SelectBox>> formSelections) {
        this.formSelections = formSelections;
    }

    public void resetFormSelections(){
        formSelections = new HashMap<>();
        List<SelectBox> genderSelections = new ArrayList<>();
        genderSelections.add(SelectBox.builder()
                .value("Male")
                .name("Male")
                .build());
        genderSelections.add(SelectBox.builder()
                .value("Female")
                .name("Female")
                .build());
        genderSelections.add(SelectBox.builder()
                .value("Non-Binary")
                .name("Non-Binary")
                .build());

        formSelections.put("gender", genderSelections);
    }

}
