package br.edu.ifsp.model.usecases.student;

import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.dao.StudentDAO;

import java.util.List;
import java.util.Optional;

public class ListStudentUseCase {
    private StudentDAO studentDAO;

    public ListStudentUseCase(StudentDAO studentDAO){
        this.studentDAO = studentDAO;
    }

    //AQUI PRECISA ARRUMAR PARA RETORNAR FINDALL...
    public Optional<Student> findOne(Long id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null!");
        return studentDAO.findOne(id);
    }

    /*public Optional<Student> findOneByCpf(String cpf){
        if(Validator.nullOrEmpty(cpf))
            throw new IllegalArgumentException("CPF can not be null or empty.");
        return studentDAO.findOneByCPF(cpf);
    }

    public Optional<Student> findOneByRg(String rg){
        if(Validator.nullOrEmpty(rg))
            throw new IllegalArgumentException("RG can not be null or empty.");
        return studentDAO.findOneByCPF(rg);
    }

    public Optional<Student> findOneByCnh(String cnh){
        if(Validator.nullOrEmpty(cnh))
            throw new IllegalArgumentException("CNH can not be null or empty.");
        return studentDAO.findOneByCPF(cnh);
    }

    public Optional<Student> findOneByEmail(String email){
        if(Validator.nullOrEmpty(email))
            throw new IllegalArgumentException("E-mail can not be null or empty.");
        return studentDAO.findOneByCPF(email);
    }*/

    public List<Student> findAll(){
        return studentDAO.findAll();
    }
}
