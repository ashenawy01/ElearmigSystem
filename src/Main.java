import Controller.Valid;
import Controller.ValidStudent;
import Entities.Course;
import Entities.Department;
import Entities.Student;
import Entities.Week;
import Models.CourseDB;
import Models.StudentDB;

public class Main {

    public static void main(String[] args) {

//        Week [] weeks = {
//                (new Week(1, "Lecture 1", "Tutorial 1", "Lap 1")),
//                (new Week(2, "Lecture 2", "Tutorial 2", "Lap 2")),
//                (new Week(3, "Lecture 3", "Tutorial 3", "Lap 3")),
//                (new Week(4, "Lecture 4", "Tutorial 4", "Lap 4")),
//                (new Week(5, "Lecture 5", "Tutorial 5", "Lap 5")),
//                (new Week(6, "Lecture 6", "Tutorial 6", "Lap 6")),
//                (new Week(7, "Lecture 7", "Tutorial 7", "Lap 7")),
//                (new Week(8, "Lecture 8", "Tutorial 8", "Lap 8")),
//                (new Week(9, "Lecture 9", "Tutorial 9", "Lap 9")),
//                (new Week(10, "Lecture 10", "Tutorial 10", "Lap 10")),
//
//        };
//
//        Course[] courses = {
//                (new Course("C++", "ICSCPP22",2255, "mgs")),
//                (new Course("Java", "ICSjv23", 2222, "ddfs")),
//                (new Course("Math", "ICSmath22",2211, "mgs")),
//                (new Course("Database", "ICSdb23", 1122, "ddfs"))
//        };
//        for (int i = 0; i < courses.length; i++) {
//            for (int j = 0; j < weeks.length; j++) {
//                courses[i].addWeek(weeks[j]);
//            }
//        }
//        CourseDB courseDB = new CourseDB();
//        courseDB.createDB(courses);
//
//        Student[] students = {
//                (new Student("Abdelrhman", 2258, "pass225", Department.ICS, 2, 1)),
//                (new Student("Ali",  22,"pass2222", Department.Business, 1, 2)),
//                (new Student("Ahmed", 2589,"pass422", Department.Engineering, 3, 1)),
//        };
//
//        Student student = new Student("Ali", 6876, "pass2222", Department.Engineering, 1, 2);
//
//        StudentDB studentDB = new StudentDB();
//
//        studentDB.createDB(students);
//        studentDB.appendObject(student);
//
//
//        ValidStudent valid = new ValidStudent();
//        valid.signIn(2258, "pass225");
//        Student s = (Student) valid.signUp("Abdelrhman Ali",225328, "ali2255", 2, 1, Department.ICS);
////        System.out.println(s.getCourses().get(0).getWeeks());
//        valid.resetPassword("aaa55566", 225328);
//        valid.signIn(225328, "aaa55566");

//
//        Student [] students = {
//                (new Student("Abdelrhman", 2258, "pass225", Department.ICS, 2, 1)),
//                (new Student("Ali",  22,"pass2222", Department.Business, 1, 2)),
//                (new Student("Ahmed", 2589,"pass422", Department.Engineering, 3, 1)),
//        };
//
//        Student student = new Student("Ali", 6876, "pass2222", Department.Engineering, 1, 2);
//
//        StudentDB studentDB = new StudentDB();

//        studentDB.createDB(students);
//        studentDB.appendObject(student);
//        studentDB.removeObj(students[2]);
//        System.out.println(studentDB.lastID());
//        System.out.println(studentDB.find(22, "pass2222"));
//        studentDB.appendObject(students[0]);
//        studentDB.retrieveAll();
//        System.out.println(studentDB.lastID());
//
//        Week [] weeks = {
//                (new Week(1, "dsdf", "sdfsd","dsfsdf")),
//                (new Week(2, "dsdf", "sdfsd","dsfsdf")),
//                (new Week(3)),
//
//        };
//        Course [] courses = {(new Course("C++", "ICSCPP",22, "mgs")),
//                (new Course("CSJava", "jv225", 2222, "ddfs")),
//                (new Course("CSC++", "CPP",22, "mgs")),
//                (new Course("ENGJava", "ICSjv225", 2222, "ddfs"))
//        };
//        Course c = new Course("Math", "mt2253", 256, "dfa");
//        c.addWeek(weeks[0]);
//        c.addWeek(weeks[1]);
//        c.addWeek(weeks[2]);
//
//        CourseDB courseDB = new CourseDB();
//        courseDB.createDB(courses);
//        courseDB.appendObject(c);
//        courseDB.removeObj(courses[0]);
////        System.out.println();
////        courseDB.retrieveAll();
////        System.out.println(Department.ICS.name());
//        Course coursen = (Course) courseDB.find("mt2253");
//        System.out.println(coursen.getWeeks());
//        System.out.println(courseDB.find("jv225"));;

//        Professor [] professors = {
//                (new Professor("Ales", 225566, "ales25")),
//                (new Professor("Ahmed", 22525566, "ales25")),
//                (new Professor("Ali", 2255686, "ales25")),
//                (new Professor("Omar", 225, "ales25")),
//        };
//        Professor p = new Professor("Basma", 259, "sasa");
//
//        ProfessorDB professorDB = new ProfessorDB();
//        professorDB.createDB(professors);
//        professorDB.appendObject(p);
//        professorDB.removeObj(professors[1]);
//        System.out.println(professorDB.find(259, "sasa"));
////        professorDB.retrieveAll();


//        courseDB.retrieveAll();
//        System.out.println(courseDB.getAll());
//
//

//
//
//        StudentDB database = new StudentDB();
//
//        database.createDB(students);
//        database.retrieveAll();
//
//
////
//        System.out.println(database.find("CPP23"));
//        database.createDB(courses);
//
//        Course c = new Course("DS", "ds2255", 5533, "src");
//
//        database.appendObject(c);
//        database.removeObj(c);
//        database.retrieveAll();
//
//        FormDB database = new FormDB();
//        database.addText("Login", """
//                Login Instruction :
//                1- enter your ID
//                2- Enter you r password
//                Note : in case of you could not login to your account and get (Incorrect ID or password),
//                        try to reset your password by entering r, R, Reset or reset
//                        or create a new account by entering c, C, Create, or create
//                end of text""");
//        database.addText("Sign Up", """
//                Sign up Instructions :
//                1- enter your real name (must be more than 2 letters)
//                2- Enter your password (the password must include characters and numbers)
//                3- Enter a valid department [CS - ENG - BS - ]
//                end of text""");
//        database.printTxt("Login");

//        ArrayList<Entities.Week> weeks = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            weeks.add(new Entities.Week(i));
//        }
//        ArrayList<Entities.Course> courses = new ArrayList<>();
//
//        char c = 65;
//        for (int i = 0; i < 10; i++) {
//            courses.add(new Entities.Course(Character.toString(c), Character.toString(c), "CS", Character.toString(c)));
//            c++;
//        }
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(courseFile))) {
//            for (int i = 0; i < 10; i++) {
//
//                oos.writeObject(courses.get(i));
//                System.out.println("Entities.Course : " + courses.get(i).getName());
//            }
//
//        } catch (EOFException e ){
//            System.out.println("done!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        Entities.User nstudent = new Entities.Student("C", 55,"kk", "CS", 2, 1);
////
//        Controller.Valid valid = new Controller.validStudent();
//        valid.login(55, "kk");
//
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.bin"))){
//            oos.writeObject(nstudent);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.bin"))) {
//            while (true) {
//                Object obj = ois.readObject();
//                if (obj instanceof Entities.Student) {
//                    Entities.Student student = (Entities.Student) obj;
//                    System.out.println(student);
//                    // Do something with the student object
//                }
//            }
//        } catch (EOFException e) {
//            // End of file reached
//        } catch (ClassNotFoundException | IOException e) {
//            e.printStackTrace();
//        }
//        Model.Database.StudentDB studentDB = new Model.Database.StudentDB();
//
//        studentDB.appendObject(new Entities.Student("A", 22, "aa", "bb", 2, 1));
//        studentDB.appendObject(new Entities.Student("D",33, "aa", "CS", 3, 2));
//        studentDB.appendObject(new Entities.Student("K", 22, "aa", "gh", 2, 1));
//
//
////        valid.signUp();
//
//
//        studentDB.retrieveAll();
//        studentDB.removeObj(nstudent);
//        studentDB.retrieveAll();

    }
}