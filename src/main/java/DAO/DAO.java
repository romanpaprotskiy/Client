package DAO;

import java.sql.*;
import Connection.*;

public abstract class DAO {


    private Connection connection;
    //database fields
    static final String ID_CUSTOM = "id_custom",ID_CUSTOMER = "idCustomer",ID_EMPLOYEE = "idEmployee",ID_DEPARTMENT = "idDep",ID_QUEST = "idQuest",
            TYPE = "Type",COUNT_OF_QUEST = "CountOfQuestions",DATE_CUSTOM = "date_custom",DATE_EXC = "dateOfExecution",
            APP_DIR = "ApprovedDirector",APP_ACC = "ApprovedAccoutant",PER_CUS = "person_customer",PER_REP = "person_representative",
            CUS_ADD = "Address",NAME_DEP = "NameDep",EMPL_NAME = "Name",EMPL_BIRTHDAY = "BirthDay",EMPL_DATE = "Date",EMPL_SALARY = "Salary",
            EMPL_POS = "Position",QUEST = "ShortContentOfQuestions",ANSW = "ShortContentOfAnswer",PRICE = "Price";

    int idCustom,idCustomer,idEmployee,CountOfQuest;
    String type,nameEmpl,position,per_cus,per_rep,CusAddress;
    Date date_custom,date_exc,app_acc,app_dir;

    DAO(ConnectionInterface connect) throws SQLException {
        connection = connect.getConnection();
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }


    public Connection getConnection() {
        return connection;
    }
}