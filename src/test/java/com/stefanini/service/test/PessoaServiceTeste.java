package com.stefanini.service.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.junit.Before;
import org.junit.Test;

import com.stefanini.dao.PerfilDao;
import com.stefanini.dao.PessoaDao;
import com.stefanini.exception.NegocioException;
import com.stefanini.model.Endereco;
import com.stefanini.model.Perfil;
import com.stefanini.model.Pessoa;
import com.stefanini.servico.EnderecoServico;
import com.stefanini.servico.PerfilServico;
import com.stefanini.servico.PessoaPerfilServico;
import com.stefanini.servico.PessoaServico;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;


public class PessoaServiceTeste {

	@Injectable
	PessoaPerfilServico pessoaPerfilServico;
	
	@Injectable
	EnderecoServico enderecolServico;
	
	@Injectable
    EntityManager em;

	@Tested
	PessoaServico pessoaServico;


	@Injectable
    @Mocked
	PessoaDao pessoaDao;

	@Tested
	Pessoa pessoa;

	private Long id;
	
    
    @Before
    public void SetUp() {
    	id = 1L;
    	String email = "teste";
    	LocalDate dataNascimento = LocalDate.of(1999, 11,11);
    	
    	pessoa = new Pessoa();
    	pessoa.setId(id);
    	pessoa.setEmail(email);
    	pessoa.setDataNascimento(dataNascimento);
    	
    	pessoa.setEnderecos(new HashSet<>());
    	pessoa.getEnderecos().add(new Endereco());
    	    }
	
	
    @Test
	public void PessoaServicoSalvarTeste() {
    	
    	 
    	
		new Expectations() {{
			pessoaDao.salvar((@Valid Pessoa) any);
			result = pessoa;
			}
		};
		
		Pessoa retorno = pessoaServico.salvar(pessoa);
		assertEquals(retorno.getId(),pessoa.getId());
		
	}
	
    
    
    
    
	@Test
	public void PessoaValidarTest() {
		new Expectations() {
			{
				pessoaDao.encontrar(id);
				result = pessoa;
			}
		};

		Boolean retorno = pessoaServico.validarPessoa(pessoa);
		assertTrue(retorno);
	}
	
	
	
	@Test
	public void PessoaValidarEmailTest() {
		Optional<Pessoa> pessoa = pessoaDao.buscarPessoaPorEmail("teste");
	    assertTrue(pessoa.isEmpty());
	}
	
	
	
	@Test
	public void PessoaAtualizarTeste() {
		new Expectations() {{
			pessoaDao.atualizar((@Valid Pessoa) any);
			result = pessoa;
			}
		};
		
		Pessoa retorno = pessoaServico.atualizar(pessoa);
		assertEquals(retorno.getId(), pessoa.getId());
		
	}
	
	
	
	
	@Test
	public void PessoaGetTeste() {
		new Expectations() {
			{
				pessoaDao.getList();
				result = Optional.of(pessoa);
			}
		};

		Optional<List<Pessoa>> retorno = pessoaServico.getList();
		assertEquals(retorno.get(), pessoa);
	}
	
	
	
	
	@Test
	public void PessoaRemoverTeste() throws NegocioException {
		
		new Expectations() {{
			pessoaDao.remover(id);
			}
		};
		
		pessoaServico.remover(id);
		
		new Verifications() {
			{
				pessoaDao.remover(id);
				
			}
		};
		
	}
	
	
	
	
	
	@Test
	public void ObterPessoaCheiaTeste() {
		new Expectations() {
			{
				pessoaDao.getPessoaCheia();
				result = new ArrayList<Pessoa>();
			}
		};

		List<Pessoa> retorno = pessoaServico.obterPessoaCheia();
		assertTrue(retorno.isEmpty());
	}

	
	
	@Test
	public void PessoaEncontrarTeste() {
		new Expectations() {
			{
				pessoaDao.encontrar(anyLong);
				result = pessoa;
			}
		};

		Optional<Pessoa> retorno = pessoaServico.encontrar(id);
		assertEquals(retorno.get(), pessoa);
	}
	
	
	
	
	
	

}
