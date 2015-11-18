package de.dresden.ba.wi.se.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import de.dresden.ba.wi.se.hibernate.domainModel.Student;

public class HibernateDemonstrationTest {

	public static void main(String[] args) {
		// .configures()  --  configures settings from the hibernate.cfg.xml
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
			// Write an object
			session.beginTransaction();
			
			Student newStudent01 = new Student("Max", "Mustermann", "123456");
			Student newStudent02 = new Student("Max", "Mayer", "000001");
			Student newStudent03 = new Student("Susi", "Sorglos", "987654");
			System.out.println("Note: There is no Student.id yet:\t" + newStudent01.getId());
			
			session.saveOrUpdate(newStudent01);
			session.saveOrUpdate(newStudent01); // Just to check if Student01 will be saves multiple times instead only once
			session.saveOrUpdate(newStudent02);
			session.saveOrUpdate(newStudent03);
			System.out.println("Note: The Student.id was auto generated:\t" + newStudent01.getId());
			
			int generatedOId01 = newStudent01.getId();
			int generatedOId03 = newStudent03.getId();
			
			session.getTransaction().commit();
			
			// Read objects (via queries)
			session.beginTransaction();
			
			List<Student> result = session.createQuery("from Student where firstName='Max'").list();
			for (Student student : result ) {
			    System.out.println("Query result: " + student.getFullInfo());
			}
			
			session.getTransaction().commit();
			
			// Read an uniqe object (via OID)
			//  -- Variant 01
			session.beginTransaction();
			
			Student loadedStudent01 = (Student) session.createQuery("from Student where id=" + generatedOId01).uniqueResult();
			System.out.println("Unique object (Variant 01): " + loadedStudent01.getFullInfo());
			
			session.getTransaction().commit();
			
			//  -- Variant 02
			session.beginTransaction();
			
			Student loadedStudent02 = (Student) session.get(Student.class.getName(), generatedOId03);
			System.out.println("Unique object (Variant 02): " + loadedStudent02.getFullInfo());
			
			session.getTransaction().commit();
		}
		catch (RuntimeException e) {
			session.getTransaction().rollback();
			
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
}