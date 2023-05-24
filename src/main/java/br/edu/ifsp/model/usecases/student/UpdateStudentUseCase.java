package br.edu.ifsp.model.usecases.student;

import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.dao.StudentDAO;
import br.edu.ifsp.model.validators.StudentInputRequestValidator;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.entities.notification.Notification;
import br.edu.ifsp.model.validators.Validator;


/**
 * Class responsible for handling the use case of updating an existing student's information.
 * It interacts with the StudentDAO to perform database operations.
 * @author Stefhani Alkin
 */
public class UpdateStudentUseCase {
    private StudentDAO studentDAO;

    /**
     * Constructs an UpdateStudentUseCase object with the specified StudentDAO.
     *
     * @param studentDAO the data access object for students
     */
    public UpdateStudentUseCase(StudentDAO studentDAO){
        this.studentDAO = studentDAO;
    }

    /**
     * Updates the information of an existing student in the system.
     *
     * @param student the student object with the updated information
     * @return true if the update was successful, false otherwise
     * @throws IllegalArgumentException if the student object is invalid
     * @throws EntityNotFoundException if the student with the specified ID is not found in the system
     */
    public boolean update(Student student){
        Validator<Student> validator = new StudentInputRequestValidator();
        Notification notification = validator.validate(student);
        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Long id = student.getId();
        if(studentDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Student not found!");

        return studentDAO.update(student);
    }
}
    // update //
    // Recebe um objeto Student
    // É criado um objeto validator do tipo Validator<Student>, utilizando uma implementação chamada StudentInputRequestValidator.
    // O objeto validator é usado para validar o objeto student e obter uma notificação de validação chamada notification.
    // Se houver erros na validação (notification.hasErrors() é true), chama a exceção com a mensagem de erro.
    // Obtem-se o ID do estudante a partir do objeto student.
    // Verifica se o estudante com o ID informado existe. Se não existir, chama a exceção com mensagem de erro.
    // Chama o método update do objeto studentDAO, passando o objeto student como argumento.
    // update retorna um valor booleano que indica se a atualização foi bem-sucedida. Esse valor é retornado pelo método update.