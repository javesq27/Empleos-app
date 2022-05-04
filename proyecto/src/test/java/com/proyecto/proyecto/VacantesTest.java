package com.proyecto.proyecto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;

import com.proyecto.proyecto.entities.Categoria;
import com.proyecto.proyecto.entities.Vacante;
import com.proyecto.proyecto.repositories.RepositorioCategorias;
import com.proyecto.proyecto.repositories.RepositorioVacantes;

import java.text.ParseException;

@SpringBootTest
class VacantesTest {

    @Autowired
    private RepositorioVacantes repoVacantes;

    @Autowired
    private RepositorioCategorias repoCategorias;

    @Test
    void testCrearVacante() {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Vacante vacante = new Vacante();
            vacante.setId(11);
            vacante.setNombre("Abogado penalista");
            vacante.setDescripcion("Abogado con 1 año de experiencia");
            vacante.setFecha(sdf.parse("10-01-2022"));
            vacante.setSalario(50000.0);
            vacante.setEstatus("Creada");
            vacante.setDestacado(0);
            vacante.setImagen("logo11.png");
            vacante.setDetalles("Abogado responsable y proactivo");
            Categoria categoria = new Categoria();
            categoria.setId(23);
            categoria.setNombre("Justicia");
            categoria.setDescripcion("Abogados de todo tipo");
            repoCategorias.save(categoria);
            vacante.setCategoria(categoria);
            repoVacantes.save(vacante);

            assertNotNull(repoVacantes.findById(11).get());
     
            

        } catch(ParseException e) {
            System.out.println("Error" + e.getMessage());
            assertNull(repoVacantes.findById(11).get());
        } 
       

    }

    @Test
    void testCrearVacanteSinNombre() {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Vacante vacante = new Vacante();
            vacante.setId(12);
            vacante.setNombre("");
            vacante.setDescripcion("Piloto con 3 años de experiencia");
            vacante.setFecha(sdf.parse("10-01-2022"));
            vacante.setSalario(50000.0);
            vacante.setEstatus("Creada");
            vacante.setDestacado(110);
            vacante.setImagen("logo11.png");
            vacante.setDetalles("Abogado responsable y proactivo");
            Categoria categoria = new Categoria();
            categoria.setId(27);
            categoria.setNombre("Aerolineas");
            categoria.setDescripcion("Pilotos de todo tipo");
            repoCategorias.save(categoria);
            vacante.setCategoria(categoria);
            repoVacantes.save(vacante);

            assertNull(repoVacantes.findById(12).get());
     
            

        } catch(ParseException e) {
            System.out.println("Error" + e.getMessage());
            assertNotNull(repoVacantes.findById(11).get());
        } 

    }

    @Test
    void testCrearVacanteConFechaErronea() {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Vacante vacante = new Vacante();
            vacante.setId(12);
            vacante.setNombre("Piloto");
            vacante.setDescripcion("Piloto con 3 años de experiencia");
            vacante.setFecha(sdf.parse("105-5601-5672022"));
            vacante.setSalario(50000.0);
            vacante.setEstatus("Eliminada");
            vacante.setDestacado(0);
            vacante.setImagen("logo11.png");
            vacante.setDetalles("Abogado responsable y proactivo");
            Categoria categoria = new Categoria();
            categoria.setId(27);
            categoria.setNombre("Aerolineas");
            categoria.setDescripcion("Pilotos de todo tipo");
            repoCategorias.save(categoria);
            vacante.setCategoria(categoria);
            repoVacantes.save(vacante);

            assertNull(repoVacantes.findById(12).get());
     
            

        } catch(ParseException e) {
            System.out.println("Error" + e.getMessage());
            assertNotNull(repoVacantes.findById(11).get());
        } 

    }

    @Test
    void testCrearVacanteConDestacadoErroneo() {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Vacante vacante = new Vacante();
            vacante.setId(12);
            vacante.setNombre("Piloto");
            vacante.setDescripcion("Piloto con 3 años de experiencia");
            vacante.setFecha(sdf.parse("105-5601-5672022"));
            vacante.setSalario(50000.0);
            vacante.setEstatus("Creada");
            vacante.setDestacado(1100);
            vacante.setImagen("logo11.png");
            vacante.setDetalles("Abogado responsable y proactivo");
            Categoria categoria = new Categoria();
            categoria.setId(27);
            categoria.setNombre("Aerolineas");
            categoria.setDescripcion("Pilotos de todo tipo");
            repoCategorias.save(categoria);
            vacante.setCategoria(categoria);
            repoVacantes.save(vacante);

            assertNull(repoVacantes.findById(12).get());
     
            

        } catch(ParseException e) {
            System.out.println("Error" + e.getMessage());
            assertNotNull(repoVacantes.findById(11).get());
        } 

    }

    @Test
    void testCrearVacanteConEstatusErroneo() {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Vacante vacante = new Vacante();
            vacante.setId(12);
            vacante.setNombre("Piloto");
            vacante.setDescripcion("Piloto con 3 años de experiencia");
            vacante.setFecha(sdf.parse("19-01-2022"));
            vacante.setSalario(0.0);
            vacante.setEstatus("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            vacante.setDestacado(110);
            vacante.setImagen("logo11.png");
            vacante.setDetalles("Abogado responsable y proactivo");
            Categoria categoria = new Categoria();
            categoria.setId(27);
            categoria.setNombre("Aerolineas");
            categoria.setDescripcion("Pilotos de todo tipo");
            repoCategorias.save(categoria);
            vacante.setCategoria(categoria);
            repoVacantes.save(vacante);

            assertNull(repoVacantes.findById(12).get());
     
            

        } catch(ParseException e) {
            System.out.println("Error" + e.getMessage());
            assertNotNull(repoVacantes.findById(11).get());
        } 

    }


    @Test
    void testActualizarSalarioVacante() {
        Vacante vacante = repoVacantes.findById(6).orElse(null);
        vacante.setSalario(80000.0);
        repoVacantes.save(vacante);
        assertEquals(80000.0, repoVacantes.findById(6).orElse(null).getSalario());
    }

    @Test
    void testBorrarVacante() {
        assertTrue(repoVacantes.findById(10).isPresent());
        repoVacantes.deleteById(10);
        assertFalse(repoVacantes.findById(10).isPresent());

    }

    


}
