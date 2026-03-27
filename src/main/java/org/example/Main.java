package org.example;

import org.example.entity.Employee;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        LinkedList<Employee> employees = new LinkedList<>();
        employees.add(new Employee(1, "Dogancan", "Kinik"));
        employees.add(new Employee(1, "Dogancan", "Kinik"));   // duplicate
        employees.add(new Employee(2, "Seyyit Battal", "Arvas"));
        employees.add(new Employee(2, "Seyyit Battal", "Arvas")); // duplicate
        employees.add(new Employee(3, "Anil", "Ensari"));
        employees.add(new Employee(3, "Anil", "Ensari"));       // duplicate
        employees.add(new Employee(4, "Burak", "Cevizli"));
        employees.add(null);

        System.out.println("=== findDuplicates ===");
        findDuplicates(employees).forEach(System.out::println);

        System.out.println("\n=== findUniques ===");
        findUniques(employees).forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println("\n=== removeDuplicates ===");
        removeDuplicates(employees).forEach(System.out::println);
    }


    public static List<Employee> findDuplicates(List<Employee> list) {
        HashMap<Integer, Employee> seen = new HashMap<>();
        LinkedList<Employee> duplicates = new LinkedList<>();

        for (Employee emp : list) {
            if (emp == null) continue;
            if (seen.containsKey(emp.getId())) {
                // İkinci kez görüldü → duplicate listesine ekle (henüz eklenmemişse)
                if (!duplicates.contains(emp)) {
                    duplicates.add(emp);
                }
            } else {
                seen.put(emp.getId(), emp);
            }
        }
        return duplicates;
    }

    public static Map<Integer, Employee> findUniques(List<Employee> list) {
        HashMap<Integer, Employee> map = new HashMap<>();

        for (Employee emp : list) {
            if (emp == null) continue;
            // putIfAbsent: ilk görülen kaydı koyar, tekrarı yok sayar
            map.putIfAbsent(emp.getId(), emp);
        }
        return map;
    }

    public static List<Employee> removeDuplicates(List<Employee> list) {
        HashMap<Integer, Integer> countMap = new HashMap<>();

        for (Employee emp : list) {
            if (emp == null) continue;
            countMap.put(emp.getId(), countMap.getOrDefault(emp.getId(), 0) + 1);
        }

        LinkedList<Employee> result = new LinkedList<>();
        for (Employee emp : list) {
            if (emp == null) continue;
            if (countMap.get(emp.getId()) == 1) {
                result.add(emp);
            }
        }
        return result;
    }
}