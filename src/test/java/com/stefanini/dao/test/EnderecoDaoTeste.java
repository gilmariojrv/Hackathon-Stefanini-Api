package com.stefanini.dao.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.stefanini.dao.EnderecoDao;
import com.stefanini.dao.PessoaDao;

import mockit.Injectable;
import mockit.Tested;

public class EnderecoDaoTeste {

	
	@Tested
	EnderecoDao enderecoDao;
	
	@Injectable
	EntityManager entityManager;
	
	
	@Test
	public void test() {
		
	}

}
