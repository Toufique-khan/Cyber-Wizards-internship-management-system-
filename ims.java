

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Intern {
    private String name;
    private String internshipCompany;
    private String externalMentorName;
    private List<String> reports = new ArrayList<>();
    private String assignment;
    private String finalEvaluation;

    public Intern(String name, String internshipCompany, String externalMentorName) {
        this.name = name;
        this.internshipCompany = internshipCompany;
        this.externalMentorName = externalMentorName;
    }

    public String getName() {
        return name;
    }

    public String getInternshipCompany() {
        return internshipCompany;
    }

    public String getExternalMentorName() {
        return externalMentorName;
    }

    public List<String> getReports() {
        return reports;
    }

    public void submitReport(String report) {
        reports.add(report);
    }

    public void submitAssignment(String assignment) {
        this.assignment = assignment;
    }

    public void submitFinalEvaluation(String evaluation) {
        this.finalEvaluation = evaluation;
    }

    public String getAssignment() {
        return assignment;
    }

    public String getFinalEvaluation() {
        return finalEvaluation;
    }
}

class Coordinator {
    private String name;
    private List<Intern> interns = new ArrayList<>();

    public Coordinator(String name) {
        this.name = name;
    }

    public void addIntern(Intern intern) {
        interns.add(intern);
    }

    public List<Intern> getInterns() {
        return interns;
    }

    public void viewInternProgress() {
        System.out.println("Coordinator " + name + " viewing progress of all interns:");
        for (Intern intern : interns) {
            System.out.println("Intern Name: " + intern.getName());
            System.out.println("Company: " + intern.getInternshipCompany());
            System.out.println("External Mentor: " + intern.getExternalMentorName());
            System.out.println("Reports submitted: " + intern.getReports().size());
            System.out.println("Assignment: " + 
                (intern.getAssignment() != null ? intern.getAssignment() : "Not submitted"));
            System.out.println("Final Evaluation: " + 
                (intern.getFinalEvaluation() != null ? intern.getFinalEvaluation() : "Not submitted"));
            System.out.println("----------------------------");
        }
    }
}

public class ims {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize coordinator
        System.out.print("Enter coordinator's name: ");
        String coordinatorName = scanner.nextLine();
        Coordinator coordinator = new Coordinator(coordinatorName);

        while (true) {
            System.out.println("\n--- Internship Management System ---");
            System.out.println("1. Add Intern");
            System.out.println("2. Submit Report for Intern");
            System.out.println("3. Submit Assignment for Intern");
            System.out.println("4. Submit Final Evaluation");
            System.out.println("5. View Intern Progress");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Add Intern
                    System.out.print("Enter intern name: ");
                    String internName = scanner.nextLine();
                    System.out.print("Enter internship company: ");
                    String company = scanner.nextLine();
                    System.out.print("Enter external mentor's name: ");
                    String mentor = scanner.nextLine();
                    Intern newIntern = new Intern(internName, company, mentor);
                    coordinator.addIntern(newIntern);
                    System.out.println("Intern added successfully.");
                    break;

                case 2:
                    // Submit Report
                    System.out.print("Enter intern name: ");
                    String internNameForReport = scanner.nextLine();
                    Intern internForReport = getInternByName(internNameForReport, coordinator);
                    if (internForReport != null) {
                        System.out.print("Enter report details: ");
                        String report = scanner.nextLine();
                        internForReport.submitReport(report);
                        System.out.println("Report submitted successfully.");
                    }
                    break;

                case 3:
                    // Submit Assignment
                    System.out.print("Enter intern name: ");
                    String internNameForAssignment = scanner.nextLine();
                    Intern internForAssignment = getInternByName(internNameForAssignment, coordinator);
                    if (internForAssignment != null) {
                        System.out.print("Enter assignment details: ");
                        String assignment = scanner.nextLine();
                        internForAssignment.submitAssignment(assignment);
                        System.out.println("Assignment submitted successfully.");
                    }
                    break;

                case 4:
                    // Submit Final Evaluation
                    System.out.print("Enter intern name: ");
                    String internNameForEvaluation = scanner.nextLine();
                    Intern internForEvaluation = getInternByName(internNameForEvaluation, coordinator);
                    if (internForEvaluation != null) {
                        System.out.print("Enter final evaluation: ");
                        String evaluation = scanner.nextLine();
                        internForEvaluation.submitFinalEvaluation(evaluation);
                        System.out.println("Final evaluation submitted successfully.");
                    }
                    break;

                case 5:
                    // View Intern Progress
                    coordinator.viewInternProgress();
                    break;

                case 6:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Helper method to get Intern by name
    private static Intern getInternByName(String name, Coordinator coordinator) {
        for (Intern intern : coordinator.getInterns()) {
            if (intern.getName().equalsIgnoreCase(name)) {
                return intern;
            }
        }
        System.out.println("Intern not found.");
        return null;
    }
}
