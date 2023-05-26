package Service;

import dao.implementaciones.H2OdontologoIDao;
import entity.Odontologo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {

    private static Connection connection = null;
    private OdontologoService odontologoService = new OdontologoService(new H2OdontologoIDao());


    @BeforeAll
    static void doBefore() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/Parcial;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Test
    public void deberiaListarTodos(){
        List<Odontologo> odontologoTest = odontologoService.listarTodos();
        assertFalse(odontologoTest.isEmpty());
        assertTrue(odontologoTest.size()<=2);
    }
}