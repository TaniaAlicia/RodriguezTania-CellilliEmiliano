package dao.implementaciones;

import dao.H2Connection;
import dao.IDao;
import entity.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class H2OdontologoIDao implements IDao<Odontologo> {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(H2OdontologoIDao.class));

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement("INSERT INTO ODONTOLOGOS (NUMEROMATRICULA NOMBRE APELLIDO) VALUES(?,?,?) ", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, odontologo.getNumeroMatricula());
            ps.setString(2, odontologo.getNombre());
            ps.setString(3, odontologo.getApellido());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()){
                odontologo.setId(rs.getInt(1));
            }
            connection.commit();
            LOGGER.info("Se registro un nuevo odontologo " + odontologo);
        }
        catch(Exception e){
            LOGGER.info(e.getMessage());
            e.printStackTrace();
            if(connection!=null){
                try{
                    connection.rollback();
                    LOGGER.info("Hay un problema!");
                    e.printStackTrace();
                }catch(Exception ex){
                    LOGGER.info(ex.getMessage());
                    ex.printStackTrace();

                }
            }
        }finally {
            try{
                connection.close();
            }catch(Exception exception){
                LOGGER.info("Ocurrio un error al cerrar la conexion" +  exception.getMessage());
                exception.printStackTrace();

            }
        }
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos(){
        Connection connection = null;
        List<Odontologo> listaOdontologo = new ArrayList<Odontologo>();
        Odontologo odontologo = null;
        try{
            connection = H2Connection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ODONTOLOGOS;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                odontologo = new Odontologo(rs.getInt(1), rs.getInt(2),rs.getString(3), rs.getString(4));
                LOGGER.info("Odontologo n°" + odontologo.getId());
            }

        }catch(Exception e){
            LOGGER.info("Ocurrio un problema" + e.getMessage());
            e.printStackTrace();
        }finally{
            try{
                connection.close();
            }catch(Exception e){
                LOGGER.info("Ocurrio un problema al cerrar la conexion" + e.getMessage());
                e.printStackTrace();
            }
        }


        return listaOdontologo;
    }
}
