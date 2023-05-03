package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exceptions.InsufficientInfoForNewStudentException;
import mk.ukim.finki.wp.lab.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepositoryDB;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.impl.StudentServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StudentSaveTest {

    @Mock
    private StudentRepositoryDB studentRepository;

    private StudentService studentService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        Student student = new Student("username","password","marija","saveska",Boolean.FALSE);
        Mockito.when(this.studentRepository.save(Mockito.any(Student.class))).thenReturn(student);

        // In spying, the real object remains unchanged, and we just spy some specific methods of it
        this.studentService = Mockito.spy(new StudentServiceImpl(this.studentRepository));

    }

    @Test
    public void testSuccessSave(){
        Student student = this.studentService.save("username","password","marija","saveska");

        //verifies whether the method 'save' has been called properly
        Mockito.verify(this.studentService).save("username","password","marija","saveska");

        Assert.assertNotNull("Student is null",student);

        Assert.assertEquals("Username doesn't match","username",student.getUsername());
        Assert.assertEquals("Password doesn't match","password",student.getPassword());
        Assert.assertEquals("Name doesn't match","marija",student.getName());
        Assert.assertEquals("Surname doesn't match","saveska",student.getSurname());

    }

    @Test
    public void testEmptyUsername(){
        Assert.assertThrows(InsufficientInfoForNewStudentException.class,
                ()->this.studentService.save("","password","marija","saveska"));
        Mockito.verify(this.studentService).save("","password","marija","saveska");
    }
    @Test
    public void testEmptyPassword(){
        Assert.assertThrows(InsufficientInfoForNewStudentException.class,
                ()->this.studentService.save("username","","marija","saveska"));
        Mockito.verify(this.studentService).save("username","","marija","saveska");
    }
    @Test
    public void testEmptyName(){
        Assert.assertThrows(InsufficientInfoForNewStudentException.class,
                ()->this.studentService.save("username","password","","saveska"));
        Mockito.verify(this.studentService).save("username","password","","saveska");
    }
    @Test
    public void testEmptySurname(){
        Assert.assertThrows(InsufficientInfoForNewStudentException.class,
                ()->this.studentService.save("username","password","marija",""));
        Mockito.verify(this.studentService).save("username","password","marija","");
    }

    @Test
    public void testUsernameAlreadyExists(){
        Mockito.when(this.studentRepository.existsByUsername(Mockito.anyString())).thenReturn(true);
        Assert.assertThrows(UsernameAlreadyExistsException.class,
                ()->this.studentService.save("username","password","david","arsovski"));
        Mockito.verify(this.studentService).save("username","password","david","arsovski");
    }


}
