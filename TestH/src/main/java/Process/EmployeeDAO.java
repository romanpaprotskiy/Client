package Process;

import Data.Employee;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class EmployeeDAO {

    private SessionFactory factory;

    @Autowired
    public EmployeeDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public Employee persist(int employeeId) {
        Employee employee = null;
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Employee where EmployeeId = ?");
            query.setInteger(0,employeeId);
            employee =(Employee) query.list().get(0);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.out.println(e);
        } finally {
            session.close();
        }
        return employee;
    }

    public List getListEmployees() {
        List employees = new ArrayList<>();
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            employees = session.createCriteria(Employee.class).list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.out.println(e);
        } finally {
            session.close();
        }
        return employees;
    }

    public void saveEmployee(Employee employee){
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println(e);
        } finally {
            session.close();
        }
    }
}
