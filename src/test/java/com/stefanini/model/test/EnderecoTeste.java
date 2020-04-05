package com.stefanini.model.test;

import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;

import com.stefanini.model.Endereco;
import com.stefanini.model.Perfil;
import com.stefanini.model.Pessoa;


import mockit.Injectable;
import mockit.Tested;

public class EnderecoTeste {
	@Injectable
    EntityManager em;

    @Tested
    Endereco endereco;
    
    
	@Test
	public void test() {
		
		Long id = 1L;
		String cep = "71881664";
		String uf = "df";
		String localidade = "riacho";
		String bairro = "fundo";
		String logradouro = "brasilia";
		String complemento = "academia civil";
		Long idPessoa = 1L;
		
		
		
		endereco.setId(id);
		endereco.setCep(cep);
		endereco.setUf(uf);
		endereco.setLocalidade(localidade);
		endereco.setBairro(bairro);
		endereco.setLogradouro(logradouro);
		endereco.setComplemento(complemento);
		endereco.setIdPessoa(idPessoa);
		
		
		
		
		Assert.assertEquals(endereco.getId(), id);
		Assert.assertEquals(endereco.getCep(), cep);
		Assert.assertEquals(endereco.getUf(), uf);
		Assert.assertEquals(endereco.getLocalidade(), localidade);
		Assert.assertEquals(endereco.getBairro(), bairro);
		Assert.assertEquals(endereco.getLogradouro(), logradouro);
		Assert.assertEquals(endereco.getComplemento(), complemento);
		Assert.assertEquals(endereco.getIdPessoa(), idPessoa);
		
		
		
		String tostring = "Endereco{" +
                "id=" + id +
                ", cep='" + cep + '\'' +
                ", uf='" + uf + '\'' +
                ", localidade='" + localidade + '\'' +
                ", bairro='" + bairro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", idPessoa=" + idPessoa +
                '}';
		
		
				 Assert.assertEquals(endereco.toString(), tostring);
		
	}

	
	
	
}
