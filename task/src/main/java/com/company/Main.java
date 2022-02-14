package com.company;


import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;


public class Main {

    //connecting DB
    private static final String DB_USERNAME="postgres";
    private static final String DB_PASSWORD="0880";
    private static final String DB_URL="jdbc:postgresql://localhost:5432/task";


    public static void main(String[] args) throws Exception {

        //raed file
        try (CSVReader reader = new CSVReader(new FileReader("test.csv"));
             PreparedStatement stmt = connection.prepareStatement(INSERT_PERSON_QUERY)) {
            String[] record;
            while ((record = reader.readNext()) != null) {
                stmt.setString(1, record[0]);
                stmt.setInt(2, Integer.valueOf(record[1]));
                stmt.executeUpdate();
            }
        }
        catch(IOException exc) {
            exc.printStackTrace();

        // read console
        Scanner scanner = new Scanner(System.in);

        //db connect
        Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME, DB_PASSWORD);
        //actions
        while (true){
            System.out.println("1. show tasks");
            System.out.println("2. show projects");
            System.out.println("3. show peoples");
            System.out.println("4. add task to user");
            System.out.println("5. add user to project");
            System.out.println("6. create new task");
            System.out.println("7. create new project");
            System.out.println("8. add new user");
            System.out.println("9. delete task");
            System.out.println("10. delete project");
            System.out.println("11. delete user");
            System.out.println("12. output a report of projects");
            System.out.println("0. exit");

            int command = scanner.nextInt();

            //command1
            if (command ==1){
                System.out.println("output form: id | name | status | executor | name of the project\n");
                Statement statement = connection.createStatement();
                String SQL_SELECT_TASK="SELECT * FROM task.tasks ORDER BY id_tasks;";
                //storage result
                ResultSet result = statement.executeQuery(SQL_SELECT_TASK);
                //show result
                while (result.next()){
                    System.out.println(result.getInt("id_tasks")+" | "
                            +result.getString("name_tasks")+ " | "
                            + result.getString("status_tasks")+ " | "
                            + result.getString("executor_tasks")+ " | "
                            + result.getString("name_project_tasks")+ "\n");
                 }
                System.out.println("Select an action:");
            }
            //command2
            else if (command==2){
                System.out.println("output form: id of project | name of project | project status | project executor\n");
                Statement statement = connection.createStatement();
                String SQL_SELECT_TASK="SELECT * FROM task.projects ORDER BY id_project;";
                //storage result
                ResultSet result = statement.executeQuery(SQL_SELECT_TASK);
                //show result
                while (result.next()){
                    System.out.println(result.getInt("id_project")+" | "
                            +result.getString("name_project")+ " | "
                            + result.getString("status_project")+ " | "
                            +  result.getString("executor_project")+"\n");
                }
                System.out.println("Select an action:");
            }
            //command3
            else if (command==3){
                System.out.println("output form: id of executor | executors name | executors age\n");
                Statement statement = connection.createStatement();
                String SQL_SELECT_TASK="SELECT * FROM task.executor ORDER BY id_executor;";
                //storage result
                ResultSet result = statement.executeQuery(SQL_SELECT_TASK);
                //show result
                while (result.next()){
                    System.out.println(result.getInt("id_executor")+" | "
                            +result.getString("name_executor")+ " | "
                            + result.getInt("age_executor")+ "\n");
                }
                System.out.println("Select an action:");
            }
            //command4
            else if (command==4){
                System.out.println("enter the user id and the id of the task that you want to give the user\n");
                System.out.println("enter the user id");
                int userid =scanner.nextInt();
                String SELECT_NAME_SQL="SELECT name_executor FROM task.executor WHERE id_executor = '"+ userid +"';";
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(SELECT_NAME_SQL);
                System.out.println("enter the task id");
                int taskid =scanner.nextInt();
                String name=null;
                //show result
                while (result.next())
                {
                    name=result.getString("name_executor");
                }
                String UPDATE_TASKS_EXECUTOR = "UPDATE task.tasks SET executor_tasks='"+name+"' WHERE id_tasks='"+taskid+"';";
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TASKS_EXECUTOR);
                preparedStatement.executeUpdate();
                System.out.println("Select an action:");
            }
            //command5
            else if (command==5){
                System.out.println("enter the user id and the id of the project that you want to give the user\n");
                System.out.println("enter the user id");
                int userid =scanner.nextInt();
                String SELECT_NAME_SQL="SELECT name_executor FROM task.executor WHERE id_executor = '"+ userid +"';";
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(SELECT_NAME_SQL);
                System.out.println("enter the project id");
                int projectid =scanner.nextInt();
                String name=null;
                //show result
                while (result.next())
                {
                    name=result.getString("name_executor");
                }
                String UPDATE_TASKS_EXECUTOR = "UPDATE task.projects SET executor_project='"+name+"' WHERE id_project='"+projectid+"';";
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TASKS_EXECUTOR);
                preparedStatement.executeUpdate();
                System.out.println("Select an action:");
            }
            //command6
            else if (command==6){
                System.out.println("enter the id of task:");
                int idtask =scanner.nextInt();
                System.out.println("enter the name of task:");
                String nametask =scanner.next();
                System.out.println("enter the status of task:");
                String statustask =scanner.next();
                System.out.println("enter the executor of task:");
                String executortask =scanner.next();
                System.out.println("enter the project name of task:");
                String projecttask =scanner.next();
                //show result
                String CREATE_TASK="INSERT INTO task.tasks(id_tasks, name_tasks, status_tasks, executor_tasks, name_project_tasks) VALUES ("+idtask+",'"+nametask+"','"+statustask+"','"+executortask+"','"+projecttask+"');";
                Statement statement = connection.createStatement();
                statement.executeUpdate(CREATE_TASK);
                System.out.println("Select an action:");
            }
            //command7
            else if (command==7){
                System.out.println("enter the id of project :");
                int idproject =scanner.nextInt();
                System.out.println("enter the name of project:");
                String nameproject =scanner.next();
                System.out.println("enter the status of project:");
                String statuproject =scanner.next();
                System.out.println("enter the executor of project:");
                String executorproject =scanner.next();
                //show result
                String CREATE_PROJECT="INSERT INTO task.projects(id_project, name_project, status_project, executor_project) VALUES ("+idproject+",'"+nameproject+"','"+statuproject+"','"+executorproject+"');";
                Statement statement = connection.createStatement();
                statement.executeUpdate(CREATE_PROJECT);
                System.out.println("Select an action:");
            }
            //command8
            else if (command==8){
                System.out.println("enter the users id :");
                int iduser =scanner.nextInt();
                System.out.println("enter the users name :");
                String nameuser =scanner.next();
                System.out.println("enter the users age:");
                int ageuser =scanner.nextInt();
                //show result
                String CREATE_USER="INSERT INTO task.executor(id_executor, name_executor, age_executor) VALUES ("+iduser+",'"+nameuser+"','"+ageuser+"');";
                Statement statement = connection.createStatement();
                statement.executeUpdate(CREATE_USER);
                System.out.println("Select an action:");
            }
            //command9
            else if (command==9){
                System.out.println("enter the id of the task that you want to delete:");
                int idtaskdel=scanner.nextInt();
                //show result
                String DELETE_TASK="DELETE FROM task.tasks WHERE id_tasks="+idtaskdel+";";
                Statement statement = connection.createStatement();
                statement.executeUpdate(DELETE_TASK);
            }
            //command10
            else if (command==10){
                System.out.println("enter the id of the project that you want to delete:");
                int idprojectdel=scanner.nextInt();
                //show result
                String DELETE_PROJECT="DELETE FROM task.projects WHERE id_project="+idprojectdel+";";
                Statement statement = connection.createStatement();
                statement.executeUpdate(DELETE_PROJECT);
            }
            //command11
            else if (command==11){
                System.out.println("enter the id of the user that you want to delete:");
                int idexecutordel=scanner.nextInt();
                //show result
                String DELETE_EXECUTOR="DELETE FROM task.executor WHERE id_executor="+idexecutordel+";";
                Statement statement = connection.createStatement();
                statement.executeUpdate(DELETE_EXECUTOR);
            }
            //command12
            else if (command==12){
                System.out.println("enter the id of the project:");
                int idreport=scanner.nextInt();
                String SELECT_REPORT="SELECT name_project FROM task.projects WHERE id_project = '"+ idreport +"';";
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(SELECT_REPORT);
                String name1=null;
                while (result.next())
                {
                    name1=result.getString("name_project");
                }
                System.out.println("enter the id of the user:");
                int iduser=scanner.nextInt();
                String SELECT_NAME_REPORT="SELECT name_executor FROM task.executor WHERE id_executor = '"+ iduser +"';";
                ResultSet result1 = statement.executeQuery(SELECT_NAME_REPORT);
                String name2=null;
                while (result1.next())
                {
                    name2=result1.getString("name_executor");
                }
                String REPORT = "SELECT *FROM task.tasks WHERE name_project_tasks='"+name1+"' AND executor_tasks='"+name2+"';";
                ResultSet result2 = statement.executeQuery(REPORT);
                //show result

                while (result2.next()){
                    System.out.println(result2.getInt("id_tasks")+" | "
                            +result2.getString("name_tasks")+ " | "
                            + result2.getString("status_tasks")+ " | "
                            + result2.getString("executor_tasks")+ " | "
                            + result2.getString("name_project_tasks")+ "\n");
                }
                System.out.println("Select an action:");

            }
            //exit
            else if (command==0){
                System.exit(0);
            }
            //not correct command
            else{
                System.err.println("enter the correct command");
            }
        }
    }
}
