package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
				//createCourseAndStudents(appDAO);
				//findCourseAndStudents(appDAO);
				//findStudentAndCourses(appDAO);
				//addMoreCoursesForStudent(appDAO);
				//deleteCourse(appDAO);
				deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Deleting student with id: "+ theId);

		appDAO.deleteStudentById(theId);

		System.out.println("DONE!!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {

		int theId = 2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		// create courses
		Course tempCourse1 = new Course("Arabic");
		Course tempCourse2 = new Course("English");

		// add courses to students
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);

		System.out.println("Updating student: "+ tempStudent);
		System.out.println("associated courses: "+tempStudent.getCourses());

		appDAO.update(tempStudent);

		System.out.println("DONE!");
	}

	private void findStudentAndCourses(AppDAO appDAO) {

		int theId = 2;

		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		System.out.println("Loading Course: "+ tempStudent);
		System.out.println("Students: "+tempStudent.getCourses());

		System.out.println("DONE!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {

		int theId = 10;

		Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

		System.out.println("Loading Course: "+ tempCourse);
		System.out.println("Students: "+tempCourse.getStudents());

		System.out.println("DONE!");
	}


	private void createCourseAndStudents(AppDAO appDAO) {

		// create course
		Course tempCourse  = new Course("Math Course");

		// create students
		Student tempStudent= new Student("Omar","khaled","Omar@gmail.com");
		Student tempStudent1= new Student("Ahmed","khaled","Ahmed@gmail.com");


		// add student to the course
		tempCourse.addStudent(tempStudent);
		tempCourse.addStudent(tempStudent1);

		// save the course and the associated students
		System.out.println("Saving the Course: "+ tempCourse);
		System.out.println("associated students: "+ tempCourse.getStudents());

		appDAO.save(tempCourse);
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Deleting course id+ "+ theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		// get the course and reviews
		int theId=10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		// print the course
		System.out.println(tempCourse);

		// print the reviews
		System.out.println(tempCourse.getReviews());

		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// create the course
		Course tempCourse = new Course("Math Course");

		// add some reviews
		tempCourse.addReview(new Review("Great"));
		tempCourse.addReview(new Review("Not Bad"));
		tempCourse.addReview(new Review("So Bad"));

		// save the course ,, and leverage the cascade all
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);

		System.out.println("Done!");
	}

	private void deleteCourse(AppDAO appDAO) {

		int theId=10;

		System.out.println("Deleting Course id: "+theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done!");

	}

	private void updateCourse(AppDAO appDAO) {

		int theId = 10;

		// find the course
		System.out.println("Finding course id: "+ theId);
		Course tempCourse = appDAO.findCourseById(theId);

		// update the course
		System.out.println("Updating course id: "+ theId);
		tempCourse.setTitle("Ismaily Team");

		appDAO.update(tempCourse);

		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {

		int theId = 1;

		// find the instructor
		System.out.println("Finding instructor id: "+ theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		// update the instructor
		System.out.println("Updating instructor id: "+theId);
		tempInstructor.setLastName("TESTER");

		appDAO.update(tempInstructor);

		System.out.println("DONE!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		int theId = 1;

		// find the instructor
		System.out.println("Finding instructor id: "+ theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: "+ tempInstructor);
		System.out.println("the associated courses: "+tempInstructor.getCourses());

		System.out.println("Done!");

	}

	private void findCoursesForInstructor(AppDAO appDAO) {

		int theId=1;
		System.out.println("Finding Instructor id :"+theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		List<Course> tempCourses = appDAO.findCoursesByInstructorId(theId);
		tempInstructor.setCourses(tempCourses);

		System.out.println("tempInstructor: "+ tempInstructor);
		System.out.println("the associated courses: "+tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {

		int theId=1;
		System.out.println("Finding Instructor id :"+theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: "+ tempInstructor);
		System.out.println("the associated courses: "+tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// create the instructor
		Instructor tempInstructor =
				new Instructor("Ahmed","Khaled","Omar@gmail.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("http://www.youtube.com/VolleyBall" ,
										"FootBall");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// Create some courses
		Course tempCourse1 = new Course("Ahly Team");
		Course tempCourse2 = new Course("Zamalek Team");

		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// saving the instructor
		//
		// NOTE: this will also save the courses because of CascadeType.PERSIST
		//
		System.out.println("Saving the instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("Done..!");

	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId=5;

		System.out.println("Deleting Instructor id: "+theId);

		appDAO.deleteInstructorDetailById(theId);

		System.out.println("Done");
	}

	private void findInstructorDetail(AppDAO appDAO) {

		// get the instructor detail object
		int theId=1;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		// print the instructor detail
		System.out.println("tempInstructorDetail :"+ tempInstructorDetail);

		// print the associated instructor
		System.out.println("the associated instructor :"+ tempInstructorDetail.getInstructor());

		System.out.println("Done");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId=4;

		System.out.println("Deleting instructor id :"+theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("Done");
	}

	private void findInstructor(AppDAO appDAO) {

		int theId=1;

		System.out.println("Finding instructor id:"+theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("the associated instructorDetial only:"+tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {

		// create the instructor
		Instructor tempInstructor =
					new Instructor("Ahmed","Khaled","Omar@gmail.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
					new InstructorDetail("http://www.youtube.com/VolleyBall" ,"VolleyBall");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		System.out.println("Saving the instructor: "+tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done..!");
	}
}
