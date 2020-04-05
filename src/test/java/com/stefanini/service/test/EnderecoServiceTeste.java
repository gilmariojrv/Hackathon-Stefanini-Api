package com.stefanini.service.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.junit.Before;
import org.junit.Test;

import com.stefanini.dao.EnderecoDao;
import com.stefanini.exception.NegocioException;
import com.stefanini.model.Endereco;
import com.stefanini.model.Perfil;
import com.stefanini.servico.EnderecoServico;
import com.stefanini.servico.PessoaPerfilServico;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;

public class EnderecoServiceTeste {

	@Injectable
    EntityManager em;

	@Tested
	EnderecoServico enderecoServico;

	@Injectable
    @Mocked
	EnderecoDao enderecoDao;

	@Tested
	Endereco endereco;

	private Long id;
	
	
	  @Before
	    public void SetUp() {
	    	id = 1L;
	    	endereco = new Endereco();
	    	endereco.setId(id);
	    	
	    	    }
	  @Test(expected = RuntimeException.class)
		public void BuscarCepTesteErrado()  {
		
			
			String retorno = enderecoServico.buscarCep("1111111");
			
			
		}
	
	
	  @Test
		public void BuscarCepTeste()  {
		
			
			String retorno = enderecoServico.buscarCep("71881664");
			assertFalse(retorno.isEmpty());
			
		}
		
		
		@Test
		public void EnderecoServicoSalvarTeste() {
			new Expectations() {{
				enderecoDao.salvar((@Valid Endereco) any);
				result = endereco;
				}
			};
			
			Endereco retorno = enderecoServico.salvar(endereco);
			assertEquals(retorno.getId(),endereco.getId());
			
		}
		
		
		
		
		@Test
		public void EnderecoAtualizarTeste() {
			new Expectations() {{
				enderecoDao.atualizar((@Valid Endereco) any);
				result = endereco;
				}
			};
			
			Endereco retorno = enderecoServico.atualizar(endereco);
			assertEquals(retorno.getId(), endereco.getId());
			
		}
		
		
		@Test
		public void EnderecoRemoverTeste() throws NegocioException {
			
			enderecoServico.remover(id);
			
			new Verifications() {
				{
					enderecoDao.remover(id);
					times = 1;
				}
			};
			
		}
		
		
		@Test
		public void EnderecoGetTeste() {
			new Expectations() {
				{
					enderecoDao.getList();
					result = Optional.of(endereco);
				}
			};
			Optional<List<Endereco>> retorno = enderecoServico.getList();
			assertEquals(retorno.get(),endereco);
		}
		
		
		@Test
		public void EnderecoEncontrarTeste() {
			new Expectations() {
				{
					enderecoDao.encontrar(anyLong);
					result = endereco;
				}
			};

			Optional<Endereco> retorno = enderecoServico.encontrar(id);
			assertEquals(retorno.get(), endereco);
		}
		
		
		
		
		
		

}
