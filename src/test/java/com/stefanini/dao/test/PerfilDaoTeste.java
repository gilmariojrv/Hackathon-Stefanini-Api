package com.stefanini.dao.test;

import static org.junit.Assert.*;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.stefanini.dao.PerfilDao;
import com.stefanini.dao.PessoaDao;
import com.stefanini.model.Perfil;
import com.stefanini.model.Pessoa;

import mockit.Injectable;
import mockit.Tested;

public class PerfilDaoTeste {
	@Tested
	PerfilDao perfilDao;
	
	@Injectable
	EntityManager entityManager;
	

	@Test
	
	public void BuscarPessoaPorNomeTest() {
		
		Optional<Perfil> perfil = perfilDao.buscarPessoaPorNome("qualquercoisa");
	    assertTrue(perfil.isEmpty());
		
		
	}

}
