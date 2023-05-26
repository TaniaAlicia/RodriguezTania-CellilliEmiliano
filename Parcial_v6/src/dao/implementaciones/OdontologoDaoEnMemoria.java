package dao.implementaciones;

import dao.IDao;
import entity.Odontologo;
import org.apache.log4j.Logger;

import java.util.List;

public class OdontologoDaoEnMemoria implements IDao<Odontologo> {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(OdontologoDaoEnMemoria.class));

    private List<Odontologo> odontologoRepository;

    public OdontologoDaoEnMemoria(List<Odontologo> odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        odontologoRepository.add(odontologo);
        LOGGER.info ("Odontologo guardado: " + odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info ("La lista de odontologos es la siguiente: \n" + odontologoRepository);
        return odontologoRepository;
    }
}
