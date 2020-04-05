package com.stefanini.model.test;



import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;

import com.stefanini.model.Perfil;
import com.stefanini.model.Pessoa;
import com.stefanini.model.PessoaPerfil;

import static org.junit.Assert.*;

import mockit.Injectable;
import mockit.Tested;

public class PerfilTeste {
	
	
	@Injectable
    EntityManager em;
	
	 @Tested
	  Perfil perfil;
	 
	@Test
	public void test() {
		
		Perfil perfil = new Perfil();
		
		
		
		Long id = 1L;
		String nome = "torres";
		String descricao = "lindo";
		Timestamp dataHoraInclusao =  new Timestamp(System.currentTimeMillis());
		LocalDateTime dataHoraAlteracao = null;
        dataHoraAlteracao = dataHoraAlteracao.now();
		
		Set<Pessoa> pessoas = null;
		
		perfil.setId(id);
		perfil.setNome(nome);
		perfil.setDescricao(descricao);
		perfil.setDataHoraInclusao(dataHoraInclusao);
		perfil.setDataHoraAlteracao(dataHoraAlteracao);
		
		
		
		Assert.assertEquals(perfil.getId(),id);
		Assert.assertEquals(perfil.getNome(),nome);
		Assert.assertEquals(perfil.getDescricao(),descricao);
		Assert.assertEquals(perfil.getDataHoraInclusao(),dataHoraInclusao);
		Assert.assertEquals(perfil.getDataHoraAlteracao(),dataHoraAlteracao);
		
		
		
		
		String tostring = "Perfil{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataHoraInclusao=" + dataHoraInclusao +
                ", dataHoraAlteracao=" + dataHoraAlteracao +
               ", pessoas=" + pessoas +
                '}';
		
		Assert.assertEquals(perfil.toString(), tostring);
	
		
		
		
		
		
		
		
		
	}
	
	@Test
	public void testeConstrutor() {
		Long id = 1L;
		perfil = new Perfil(id);
		Assert.assertEquals(perfil.getId(),id);
	}
	
	

}
