package com.proyecto.proyecto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoriasTest {

	@Autowired
	RepositorioCategorias repoCategorias;

	@Test
	void testCrearCategoria() {
		Categoria categoria = new Categoria();
		categoria.setId(10);
		categoria.setNombre("Construccion");
		categoria.setDescripcion("Trabajos relacionados con la construccion");
		repoCategorias.save(categoria);
		assertNotNull(repoCategorias.findById(10).get());

	}

	@Test
	void testCrearCategoriaErronea() {
		Categoria categoria = new Categoria();
		categoria.setId(null);
		categoria.setNombre("Construccion");
		categoria.setDescripcion("Trabajos relacionados con la construccion");
		repoCategorias.save(categoria);
		assertNull(repoCategorias.findById(null).get());
	}

	@Test
	void testBuscarPropiedadCategoria() {
		Categoria categoria = repoCategorias.findById(1).orElse(null);
		assertEquals("Finanzas", categoria.getNombre());
	}

	@Test
	void testBuscarPropiedadErroneaCategoria() {
		Categoria categoria = repoCategorias.findById(1).orElse(null);
		assertNotEquals("Siiiiuuu", categoria.getNombre());

	}

	@Test
	void testActualizarDescripcionCategoria() {
		Categoria categoria = repoCategorias.findById(10).orElse(null);
		categoria.setDescripcion("Se recibe gente sin experiencia");
		repoCategorias.save(categoria);
		assertEquals("Se recibe gente sin experiencia", repoCategorias.findById(10).orElse(null).getDescripcion());
	}

	@Test
	void testActualizarErrorNombreCategoria() {
		Categoria categoria = repoCategorias.findById(22).orElse(null);
		categoria.setNombre("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		repoCategorias.save(categoria);
		assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", repoCategorias.findById(10).orElse(null).getNombre());
	}

	@Test
	void testBorrarCategoria() {
		boolean categoriaExiste = repoCategorias.findById(16).isPresent();
		repoCategorias.deleteById(16);
		assertTrue(categoriaExiste);
		
		
	}

	@Test
	void testBorrarCategoriaInexistente() {
		boolean categoriaNoExiste = repoCategorias.findById(1000).isPresent();
		repoCategorias.deleteById(1000);
		assertFalse(categoriaNoExiste);
	}

	
		

}
