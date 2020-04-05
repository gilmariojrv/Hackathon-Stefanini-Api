package com.stefanini.service.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.junit.Before;
import org.junit.Test;

import com.stefanini.dao.PerfilDao;
import com.stefanini.exception.NegocioException;
import com.stefanini.model.Perfil;
import com.stefanini.model.Pessoa;
import com.stefanini.servico.PerfilServico;
import com.stefanini.servico.PessoaPerfilServico;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;

public class PerfilServiceTeste {
	@Injectable
    EntityManager em;

	@Tested
	PerfilServico perfilServico;

	@Injectable
	PessoaPerfilServico pessoaPerfilServico;

	@Injectable
    @Mocked
	PerfilDao perfilDao;

	@Tested
	Perfil perfil;

	private Long id;
   
    
    @Before
    public void SetUp() {
    	id = 1L;
    	perfil = new Perfil();
    	perfil.setId(id);
    	
    	    }
    
	
	@Test
	public void PerfilServicoSalvarTeste() {
		new Expectations() {{
			perfilDao.salvar((@Valid Perfil) any);
			result = perfil;
			}
		};
		
		Perfil retorno = perfilServico.salvar(perfil);
		assertEquals(retorno.getId(),perfil.getId());
		
	}
	
	
	
	@Test
	public void TestPerfilServicoValidarPerfil() {
		new Expectations() {
			{
				perfilServico.validarPerfil(perfil);
				result = false;
			}
		};

		Boolean retorno = perfilServico.validarPerfil(perfil);
		assertFalse(retorno);
	}
	
	@Test
	public void PerfilAtualizarTeste() {
		new Expectations() {{
			perfilDao.atualizar((@Valid Perfil) any);
			result = perfil;
			}
		};
		
		Perfil retorno = perfilServico.atualizar(perfil);
		assertEquals(retorno.getId(), perfil.getId());
		
	}
	
	
	@Test
	public void PerfilRemoverTeste() throws NegocioException {
		
		new Expectations() {{
			perfilDao.remover(id);
			}
		};
		
		pessoaPerfilServico.buscarPessoaPerfil(null,id);
		
		new Verifications() {
			{
				perfilDao.remover(id);
				
			}
		};
		
	}
	
	
	@Test
	public void PerfilGetTeste() {
		new Expectations() {
			{
				perfilDao.getList();
				result = Optional.of(perfil);
			}
		};

		Optional<List<Perfil>> retorno = perfilServico.getList();
		assertEquals(retorno.get(), perfil);
	}
	
	
	@Test
	public void PerfilEncontrarTeste() {
		new Expectations() {
			{
				perfilDao.encontrar(anyLong);
				result = perfil;
			}
		};

		Optional<Perfil> retorno = perfilServico.encontrar(id);
		assertEquals(retorno.get(), perfil);
	}
	
	
}
