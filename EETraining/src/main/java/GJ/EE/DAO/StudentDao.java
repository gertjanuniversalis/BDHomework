package GJ.EE.DAO;

import GJ.EE.domain.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class StudentDao {

    @PersistenceContext
    private EntityManager em;

    public Student get(int id){return em.find(Student.class, id);}
}
