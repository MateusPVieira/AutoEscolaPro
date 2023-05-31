package br.edu.ifsp.application.view.model.usecases.student;

import br.edu.ifsp.application.view.model.entities.student.Student;
import br.edu.ifsp.application.view.model.dao.StudentDAO;
import br.edu.ifsp.application.view.model.validators.StudentInputRequestValidator;
import br.edu.ifsp.application.view.model.exceptions.EntityAlreadyExistsException;
import br.edu.ifsp.application.view.model.entities.notification.Notification;
import br.edu.ifsp.application.view.model.validators.Validator;

/**
 * Class responsible for handling the use case of creating a new student.
 * It interacts with the StudentDAO to perform database operations.
 * @author Stefhani Alkin
 */
public class CreateStudentUseCase {
    private StudentDAO studentDAO; // Dependency

    /**
     * Constructs a CreateStudentUseCase object with the specified StudentDAO.
     *
     * @param studentDAO the data access object for students
     */
    public CreateStudentUseCase(StudentDAO studentDAO){
        this.studentDAO = studentDAO;
    }

    /**
     * Inserts a new student into the system.
     *
     * @param student the student object to be inserted
     * @return the ID of the newly inserted student
     * @throws IllegalArgumentException if the student object is invalid
     * @throws EntityAlreadyExistsException if the student's CPF, RG, CNH, or email already exist in the system
     */
    public Long insert(Student student){
        Validator<Student> validator = new StudentInputRequestValidator();
        Notification notification = validator.validate(student);
        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        String cpf = student.getCpf();
        if(studentDAO.findOneByCPF(cpf).isPresent())
            throw new EntityAlreadyExistsException("This CPF is already in use!");

        String rg = student.getRg();
        if(studentDAO.findOneByRG(rg).isPresent())
            throw new EntityAlreadyExistsException("This RG is already in use!");

        String cnh = student.getCnh();
        if(studentDAO.findOneByCNH(cnh).isPresent())
            throw new EntityAlreadyExistsException("This CNH is already in use!");

        if(studentDAO.findOneByEmail(student.getEmail()).isPresent())
            throw new EntityAlreadyExistsException("This e-mail is already in use!");

        // Verifica se já existe algum estudante cadastrado no sistema com o mesmo CPF, RG, CNH ou e-mail.
        // Se algum desses dados já estiver em uso, uma exceção é lançada com a mensagem de erro.

        return studentDAO.create(student);
    }
}

//Insert//
// Recebe um objeto Student como parâmetro, representando os dados do estudante a ser criado.
// É criado um objeto validator do tipo Validator<Student>, utilizando uma implementação chamada StudentInputRequestValidator.
// O objeto validator é usado para validar o objeto student e obter uma notificação de validação chamada notification.
// e houver erros na validação (notification.hasErrors() é true), uma exceção IllegalArgumentException é lançada, contendo a mensagem de erro da notificação.

