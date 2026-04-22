package managers;

import worker.*;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Менеджер для работы с файлами
 * *   парсер JSON
 */
public class FileManager {
    private final String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public void saveCollection(Collection<Worker> collection) {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            StringBuilder json = new StringBuilder("[\n");
            Iterator<Worker> it = collection.iterator();
            while (it.hasNext()) {
                Worker w = it.next();
                json.append("  {\n");
                json.append("    \"id\": ").append(w.getId()).append(",\n");
                json.append("    \"name\": \"").append(escape(w.getName())).append("\",\n");
                json.append("    \"coordinates\": {\n");
                json.append("      \"x\": ").append(w.getCoordinates().getX()).append(",\n");
                json.append("      \"y\": ").append(w.getCoordinates().getY()).append("\n");
                json.append("    },\n");
                json.append("    \"creationDate\": \"").append(w.getCreationDate().getTime()).append("\",\n");
                json.append("    \"salary\": ").append(w.getSalary()).append(",\n");
                json.append("    \"position\": \"").append(w.getPosition()).append("\",\n");
                json.append("    \"status\": ").append(w.getStatus() == null ? "null" : "\"" + w.getStatus() + "\"")
                        .append(",\n");
                json.append("    \"organization\": {\n");
                json.append("      \"employeesCount\": ").append(w.getOrganization().getEmployeesCount()).append(",\n");
                json.append("      \"type\": \"").append(w.getOrganization().getType()).append("\"\n");
                json.append("    }\n");
                json.append("  }");
                if (it.hasNext())
                    json.append(",");
                json.append("\n");
            }
            json.append("]");
            fos.write(json.toString().getBytes());
            System.out.println("Коллекция успешно сохранена в файл.");
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении в файл: " + e.getMessage());
        }
    }

    public PriorityQueue<Worker> readCollection() {
        PriorityQueue<Worker> collection = new PriorityQueue<>();
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Файл не найден. Будет создана новая коллекция.");
            return collection;
        }

        try (FileReader reader = new FileReader(file)) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
            String content = sb.toString().trim();
            if (content.isEmpty() || content.equals("[]"))
                return collection;

            // парсинг JSON
            Pattern pattern = Pattern.compile("\\{[^\\{\\}]*(\\{[^\\{\\}]*\\}[^\\{\\}]*)*\\}");
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                try {
                    Worker worker = parseWorker(matcher.group());
                    collection.add(worker);
                    IdManager.useId(worker.getId());
                } catch (Exception e) {
                    System.err.println("Ошибка при разборе объекта в JSON: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return collection;
    }

    private Worker parseWorker(String json) {
        Worker w = new Worker();
        w.setId(Long.parseLong(getValue(json, "id")));
        w.setName(unescape(getValue(json, "name")));

        Coordinates coords = new Coordinates(
                Integer.parseInt(getValue(json, "x")),
                Double.parseDouble(getValue(json, "y")));
        w.setCoordinates(coords);

        w.setCreationDate(new Date(Long.parseLong(getValue(json, "creationDate"))));

        String salaryStr = getValue(json, "salary");
        w.setSalary(salaryStr.equals("null") ? null : Integer.parseInt(salaryStr));

        w.setPosition(Position.valueOf(getValue(json, "position")));

        String statusStr = getValue(json, "status");
        w.setStatus(statusStr.equals("null") ? null : Status.valueOf(statusStr));

        Organization org = new Organization(
                getValue(json, "employeesCount").equals("null") ? null
                        : Integer.parseInt(getValue(json, "employeesCount")),
                OrganizationType.valueOf(getValue(json, "type")));
        w.setOrganization(org);

        return w;
    }

    private String getValue(String json, String key) {
        Pattern p = Pattern.compile("\"" + key + "\":\\s*(\"[^\"]*\"|[^,\\s\\}]+)");
        Matcher m = p.matcher(json);
        if (m.find()) {
            String val = m.group(1).trim();
            if (val.startsWith("\"") && val.endsWith("\"")) {
                return val.substring(1, val.length() - 1);
            }
            return val;
        }
        return "null";
    }

    private String escape(String s) {
        return s.replace("\"", "\\\"");
    }

    private String unescape(String s) {
        return s.replace("\\\"", "\"");
    }
}
