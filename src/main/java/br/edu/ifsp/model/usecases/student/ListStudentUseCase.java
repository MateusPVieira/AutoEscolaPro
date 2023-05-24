package br.edu.ifsp.model.usecases.student;

import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.dao.StudentDAO;

import java.util.List;
import java.util.Optional;

public class ListStudentUseCase {
    private StudentDAO studentDAO;

    public ListStudentUseCase(StudentDAO studentDAO){
        this.studentDAO = studentDAO;
    } // Construtor

    public Optional<Student> findOne(Long id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null!");
        return studentDAO.findOne(id);
    }
    // FindOne //
    // Recebe o id do estudante a ser procurado.
    // Verifica se o id é nulo. Se for nulo, lança uma exceção com mensagem de erro.
    // Caso contrário, chama o método, passando o id como argumento.
    // Retorna um objeto Optional<Student>, que pode conter um estudante encontrado com o id informado.

    public List<Student> findAll(){
        return studentDAO.findAll();
    }

    // findAll //
    // Retorna uma lista de todos os estudantes cadastrados no sistema.


    //Reserva//
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
}
