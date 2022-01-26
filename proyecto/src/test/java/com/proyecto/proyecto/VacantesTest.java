package com.proyecto.proyecto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
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
        } 
       

    }

    @Test
    void testCrearVacanteErronea() {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Vacante vacante = new Vacante();
            vacante.setId(12);
            vacante.setNombre("Pilotooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
            vacante.setDescripcion("Piloto con 3 años de experiencia");
            vacante.setFecha(sdf.parse("10-101-20122"));
            vacante.setSalario(50000.0);
            vacante.setEstatus("");
            vacante.setDestacado(110);
            vacante.setImagen("logo11.png");
            vacante.setDetalles("Abogado responsable y proactivo");
            Categoria categoria = new Categoria();
            categoria.setId(27);
            categoria.setNombre("Aerolineasssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
            categoria.setDescripcion("Pilotos de todo tipo");
            repoCategorias.save(categoria);
            vacante.setCategoria(categoria);
            repoVacantes.save(vacante);

            assertNull(repoVacantes.findById(12).get());
     
            

        } catch(ParseException e) {
            System.out.println("Error" + e.getMessage());
        } 

    }

    @Test
    void testBuscarPropiedadVacante() {
        Vacante vacante = repoVacantes.findById(6).orElse(null);
        assertEquals(12000, vacante.getSalario());
    }

    @Test
    void testBuscarPropiedadVacanteErronea() {
        Vacante vacante = repoVacantes.findById(6).orElse(null);
        assertNotEquals(200000, vacante.getSalario());
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
        boolean vacanteExiste = repoVacantes.findById(10).isPresent();
        repoVacantes.deleteById(10);
        assertTrue(vacanteExiste);

    }

    @Test
    void testBorrarVacanteInexistente() {
        boolean vacanteInexistente = repoVacantes.findById(1000).isPresent();
        repoVacantes.deleteById(1000);
        assertFalse(vacanteInexistente);
    }


}
