/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fairqueue;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Haseeb Khizar
 */
public class Fairqueue {

    static float band = 800;
    static int max_users = 7800;
    static int max_faculty = 1500;
    
        
    
    
    
    
    public static void main(String[] args) throws IOException {
        Set<String> activity = new HashSet<String>();
        String csvFile = "Academic _Schedule.csv";
        String line = "";
        String csvSplit = ",";
        Map <String, Integer> priority = new HashMap<String, Integer>();
        priority.put("Assignment Upload", 3);
        priority.put("Assignment Grading", 4);
        priority.put("1st Student Feedback", 2);
        priority.put("Project Submission(12am)", 8);
        priority.put("Project Grading", 8);
        priority.put("Maintenance", 6);
        priority.put("Quiz Grading", 4);
        priority.put("Data Backup(3am-4am)", 6);
        priority.put("Lecture Download", 3);
        priority.put("Data Backup(1am-2am)", 6);
        priority.put("Course Registration", 4);
        priority.put("2nd Student Feedback", 2);
        priority.put("ESE GuideLine Update", 2);
        priority.put("Student Result Comments", 3);
        priority.put("Lecture Preparation", 6);
        priority.put("Assignment Submission(12am)", 6);
        priority.put("Project Upload Link Creation", 4);
        priority.put("New User Registration", 8);
        priority.put("Final Grading", 8);
        priority.put("Lab Task Upload(5pm)", 4);
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {//buffered reader to parse csv file
            line = br.readLine();//skip the second line of the csv
            while ((line = br.readLine()) != null) {//loop to parse through lines of the csv
                String[] planner = line.split(csvSplit);//splitting the whole line into a string array
                Calendar cal = Calendar.getInstance();
			String current_date = new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime());
			SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
			try {
				
				Date date1 = myFormat.parse(planner[0]);
				Date date2 = myFormat.parse(planner[1]);
				long diff = date2.getTime() - date1.getTime();
				try (FileWriter fw = new FileWriter("", true);
						BufferedWriter bw = new BufferedWriter(fw);
						PrintWriter out = new PrintWriter(bw)) {
                                    for(String s : priority.keySet()){
                                        if (s.equals(planner)){
                                            out.println(date1 + "," + date2 + "," + diff +"," + priority.get(s));
                                        }
                                    }
						
				} catch (IOException e) { // exception handling left as an
											// exercise for the reader
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
                
            }
        }        
        
        
    }
}
    

