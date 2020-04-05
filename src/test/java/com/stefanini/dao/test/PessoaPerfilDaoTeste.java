package com.stefanini.dao.test;

import static org.junit.Assert.*;

import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.stefanini.dao.PerfilDao;
import com.stefanini.dao.PessoaDao;
import com.stefanini.dao.PessoaPerfilDao;
import com.stefanini.model.Pessoa;
import com.stefanini.model.PessoaPerfil;

import mockit.Injectable;
import mockit.Tested;

public class PessoaPerfilDaoTeste {

	@Tested
	PessoaPerfilDao pessoaPerfilDao;
	
	@Injectable
	EntityManager entityManager;
	

	@Test
	
	public void BuscarPessoaPerfilTest() {
		
		Stream<PessoaPerfil> pessoaPerfil = pessoaPerfilDao.buscarPessoaPerfil(1L,1L);
	    assertFalse(pessoaPerfil.isParallel());
		
		
	}

}
