package br.edu.ifsp.model.usecases.student;

import br.edu.ifsp.model.entities.student.Student;
import br.edu.ifsp.model.dao.StudentDAO;
import br.edu.ifsp.model.validators.StudentInputRequestValidator;
import br.edu.ifsp.model.exceptions.EntityNotFoundException;
import br.edu.ifsp.model.entities.notification.Notification;
import br.edu.ifsp.model.validators.Validator;


public class UpdateStudentUseCase { // atualiza informações de um estudante
    private StudentDAO studentDAO;

    public UpdateStudentUseCase(StudentDAO studentDAO){
        this.studentDAO = studentDAO;
    } // construtor

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
    // update //
    // Recebe um objeto Student
    // É criado um objeto validator do tipo Validator<Student>, utilizando uma implementação chamada StudentInputRequestValidator.
    // O objeto validator é usado para validar o objeto student e obter uma notificação de validação chamada notification.
    // Se houver erros na validação (notification.hasErrors() é true), chama a exceção com a mensagem de erro.
    // Obtem-se o ID do estudante a partir do objeto student.
    // Verifica se o estudante com o ID informado existe. Se não existir, chama a exceção com mensagem de erro.
    // Chama o método update do objeto studentDAO, passando o objeto student como argumento.
    // update retorna um valor booleano que indica se a atualização foi bem-sucedida. Esse valor é retornado pelo método update.

}
