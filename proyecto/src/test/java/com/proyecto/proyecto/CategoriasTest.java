package com.proyecto.proyecto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
	void testCrearCategoriaSinId() {
		Categoria categoria = new Categoria();
		categoria.setId(null);
		categoria.setNombre("Construccion");
		categoria.setDescripcion("Trabajos relacionados con la construccion");
		repoCategorias.save(categoria);
		assertNull(repoCategorias.findById(null).get());
	}

	@Test
	void testCrearCategoriaSinNombre() {
		Categoria categoria = new Categoria();
		categoria.setId(10);
		categoria.setNombre("");
		categoria.setDescripcion("Trabajos relacionados con la construccion");
		repoCategorias.save(categoria);
		assertNull(repoCategorias.findById(10).get());
	}

	@Test
	void testCrearCategoriaSinDescripcion() {
		Categoria categoria = new Categoria();
		categoria.setId(10);
		categoria.setNombre("Construccion");
		categoria.setDescripcion("");
		repoCategorias.save(categoria);
		assertNull(repoCategorias.findById(10).get());
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
		assertTrue(repoCategorias.findById(16).isPresent());
		repoCategorias.deleteById(16);
		assertFalse(repoCategorias.findById(16).isPresent());
		
		
	}

	

	
		

}
