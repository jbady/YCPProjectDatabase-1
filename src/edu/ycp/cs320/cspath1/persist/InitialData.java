package edu.ycp.cs320.cspath1.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.cspath1.enums.ClassType;
import edu.ycp.cs320.cspath1.enums.MajorType;
import edu.ycp.cs320.cspath1.enums.SolicitationType;
import edu.ycp.cs320.cspath1.enums.UserType;
//import edu.ycp.cs320.cspath1.project.Solicitation;
import edu.ycp.cs320.cspath1.user.Business;
import edu.ycp.cs320.cspath1.user.Faculty;
import edu.ycp.cs320.cspath1.user.Student;
import edu.ycp.cs320.cspath1.user.User;

public class InitialData {
	public static List<User> getUsers() throws IOException {
		List<User> userList = new ArrayList<User>();
		ReadCSV readUser = new ReadCSV("Users.CSV");
		try {
			Integer userID = 0;
			while (true) {
				List<String> tuple = readUser.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Student user = new Student();
				user.setUserID(userID++);
				user.setUsername(i.next());
				user.setPassword(i.next());
				user.setEmail(i.next());
				user.setUsertype(getUserTypeFromParameter(i.next()));
				if (user.getUsertype().equals(UserType.STUDENT)) {
					user.setFirstname(i.next());
					user.setLastname(i.next());
					user.setMajor(getMajorTypeFromParameter(i.next()));
					user.setClassLevel(getClassTypeFromParameter(i.next()));
					userList.add(user);
				} else if (user.getUsertype().equals(UserType.FACULTY)) {
					user.setFirstname(i.next());
					user.setLastname(i.next());
					user.setMajor(getMajorTypeFromParameter(i.next()));
					userList.add(user);
				} else if (user.getUsertype().equals(UserType.BUSINESS)) {
					Business business = new Business();
					business.setUserID(user.getUserID());
					business.setUsername(user.getUsername());
					business.setPassword(user.getPassword());
					business.setEmail(user.getPassword());
					business.setUsertype(user.getUsertype());
					business.setName(i.next());
					business.setAddress(i.next());
					business.setNumber(i.next());
					userList.add(business);
				}
			} 
			return userList;
		} finally {
				readUser.close();
		}
	}
	
	public static List<Faculty> getFaculty() throws IOException {
		List<Faculty> facultyList = new ArrayList<Faculty>();
		ReadCSV readFaculty = new ReadCSV("FacultyUser.CSV");
		try {
			Integer userID = 1;
			while (true) {
				List<String> tuple = readFaculty.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Faculty faculty = new Faculty();
				faculty.setUserID(userID++);
				faculty.setFirstname(i.next());
				faculty.setLastname(i.next());
				faculty.setPassword(i.next());
				String string = i.next();
				MajorType major = getMajorTypeFromParameter(string);
				faculty.setMajor(major);
				faculty.setEmail(i.next());
				faculty.setUsername(i.next());
				UserType usertype = getUserTypeFromParameter(i.next());
				faculty.setUsertype(usertype);
				facultyList.add(faculty);
			}
			return facultyList;
		} finally {
			readFaculty.close();
		}
		
	}
	
	public static List<Student> getStudents() throws IOException {
		List<Student> studentList = new ArrayList<Student>();
		ReadCSV readStudents = new ReadCSV("StudentUsers.CSV");
		try {
			Integer userID = 18;
			while (true) {
				List<String> tuple = readStudents.next();
				if (tuple == null){
					break;
				}
				Iterator<String> i = tuple.iterator();
				Student student = new Student();
				student.setUserID(userID++);
				student.setFirstname(i.next());
				student.setLastname(i.next());
				student.setPassword(i.next());
				MajorType major = getMajorTypeFromParameter(i.next());
				student.setMajor(major);
				ClassType classtype = getClassTypeFromParameter(i.next());
				student.setClassLevel(classtype);
				student.setEmail(i.next());
				student.setUsername(i.next());
				UserType usertype = getUserTypeFromParameter(i.next());
				student.setUsertype(usertype);
				
				
				studentList.add(student);
			}
			return studentList;
		} finally {
			readStudents.close();

		}
	}
	
	
	public static List<Business> getBusinesses() throws IOException {
		List<Business> businessList = new ArrayList<Business>();
		ReadCSV readBusiness = new ReadCSV("BusinessUsers.CSV");
		try {
			int userID = 65;
			while (true) {
				List<String> tuple = readBusiness.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Business business = new Business();
				business.setUserID(userID++);
				business.setUsername(i.next());
				business.setUsername(i.next());
				business.setEmail(i.next());
				business.setPassword(i.next());
				business.setAddress(i.next());
				business.setNumber(i.next());
				business.setUsertype(getUserTypeFromParameter(i.next()));
				businessList.add(business);
			}
			return businessList;
		} finally {
			readBusiness.close();

		}
	}
 	
	private static MajorType getMajorTypeFromParameter(String s){
		MajorType majortype = null;
		if (s == null || s.equals("")){
			return null;
		}
		else if (s.equals("ME")){
			majortype = MajorType.ME;
			
		}
		else if (s.equals("CE")){
			majortype = MajorType.CE;
		}
		else if(s.equals("CS")){
			majortype = MajorType.CS;
		}
		else if(s.equals("EE")){
			majortype = MajorType.EE;
		}
		else if (s.equals("UN")) {
			majortype = MajorType.UN;
		}
		else if (s.equals("CIV")){
			majortype = MajorType.CIV;
		}
		return majortype;
	}
	
	private static ClassType getClassTypeFromParameter(String s){
		ClassType classtype = null;
		if(s == null || s.equals("")){
			return null;
		}
		else if (s.equals("FRESHMAN")){
			classtype = ClassType.FRESHMAN;
		}
		else if (s.equals("SOPHOMORE")){
			classtype = ClassType.SOPHOMORE;
		}
		else if (s.equals("JUNIOR")){
			classtype = ClassType.JUNIOR;
		}
		else if (s.equals("SENIOR")){
			classtype = ClassType.SENIOR;
		}
		return classtype;
	}
	
	private static UserType getUserTypeFromParameter(String s) {
		if (s == null || s.equals("")){
			return null;
		}
		else if (s.equals("FACULTY")){
			return UserType.FACULTY;
		}
		else if (s.equals("ADMIN")){
			return UserType.ADMIN;
		}
		else if (s.equals("STUDENT")){
			return UserType.STUDENT;
		}
		else if (s.equals("BUSINESS")){
			return UserType.BUSINESS;
		}
		return null;
	}
	private static SolicitationType getSolicitationTypeFromParameter(String s){
		if (s == null || s.equals("")){
			return null;
		}
		else if (s.equals("SW_ENGINEERING")){
			return SolicitationType.SW_ENGINEERING;
		}
		else if (s.equals("CivE_CAPSTONE")){
			return SolicitationType.CivE_CAPSTONE;
		}
		else if (s.equals("ME_ECE_CAPSTONE")){
			return SolicitationType.ME_ECE_CAPSTONE;
		}
		else if (s.equals("CS_SENIOR_DESIGN_I")){
			return SolicitationType.CS_SENIOR_DESIGN_I;
		}
		else if (s.equals("CS_SENIOR_DESIGN_II")){
			return SolicitationType.CS_SENIOR_DESIGN_II;
		}
		else if (s.equals("ECE_CAPSTONE")){
			return SolicitationType.ECE_CAPSTONE;
		}
		else if (s.equals("ME_CAPSTONE")){
			return SolicitationType.ME_CAPSTONE;
		}
		else if (s.equals("CS_INTERNSHIP")){
			return SolicitationType.CS_INTERNSHIP;
		}
		else if (s.equals("INDEPENDENT_STUDY")){
			return SolicitationType.INDEPENDENT_STUDY;
		}
		else if (s.equals("ENGINEERING_COOP")){
			return SolicitationType.ENGINEERING_COOP;
		}
		else if (s.equals("CLASS_PROJECT")){
			return SolicitationType.CLASS_PROJECT;
		}
		return null;
	}
	
}
