package com.stefanini.model.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;

import com.stefanini.model.Endereco;
import com.stefanini.model.Perfil;
import com.stefanini.model.Pessoa;


import mockit.Injectable;
import mockit.Tested;

public class PessoaTeste {

	@Injectable
    EntityManager em;

    @Tested
    Pessoa Pessoa;
    
    
    Endereco enderecos = new Endereco();
    Pessoa pessoa = new Pessoa();
    Perfil perfils = new Perfil();
    
    
    @Test
    public void TestSets() {
    	
    	Pessoa pessoa = new Pessoa();
    	Set<Perfil> perfils = null;
    	Set<Endereco> enderecos = null;
    	
    	pessoa.setPerfils(perfils);
    	Assert.assertEquals(pessoa.getPerfils(),perfils);
    	
        pessoa.setEnderecos(enderecos);
    	Assert.assertEquals(pessoa.getEnderecos(),enderecos);
    	
    	
    	
    }
    
    @Test
    public void TestPessoa1() {
    	Long id = 1L;
    	String imagem = "foto" ;
    	String nome = "eu";
    	String email = "meu";
    	LocalDate dataNascimento = LocalDate.of(1999, 11,11);
    	Boolean situacao = true ;
    	
    	Set<Perfil> perfils = null;
    	Set<Endereco> enderecos = null;
    	
    	
    	String tostring = "Pessoa [id=" + id + ", nome=" + nome + ", imagem=" + imagem + ", email=" + email + ", dataNascimento="
				+ dataNascimento + ", situacao=" + situacao + ", enderecos=" + enderecos + ", perfils=" + perfils + "]";
    	
    	
    	
    	
    	pessoa.setId(id);
    	pessoa.setImagem(imagem);
    	pessoa.setNome(nome);
    	pessoa.setEmail(email);
    	pessoa.setDataNascimento(dataNascimento);
    	pessoa.setSituacao(situacao);
    	pessoa.setEnderecos(enderecos);
    	pessoa.setPerfils(perfils);
    	
    	
    	
    
    	Assert.assertEquals(pessoa.getId(),id);
    	Assert.assertEquals(pessoa.getImagem(),imagem);
    	Assert.assertEquals(pessoa.getNome(),nome);
    	Assert.assertEquals(pessoa.getEmail(),email);
    	Assert.assertEquals(pessoa.getDataNascimento(),dataNascimento);
    	Assert.assertEquals(pessoa.getSituacao(),situacao);
    	
        
        Assert.assertEquals(pessoa.toString(), tostring);
        
        
        
    	
    }
    
    
    
    
    
    
    
    @Test
    public void testeHashCode() {

        Long id = 1L;
        String nome = "nome";
        String email = "email";
        LocalDate dataNascimento = LocalDate.now();
        Boolean situacao = true;
        String imagem = "imagem";
        Set<Endereco> enderecos = new HashSet<Endereco>();
        Set<Perfil> perfils = new HashSet<Perfil>();

        Pessoa pessoa = new Pessoa();

        pessoa.setId(id);
        pessoa.setNome(nome);
        pessoa.setEmail(email);
        pessoa.setDataNascimento(dataNascimento);
        pessoa.setSituacao(situacao);
        pessoa.setImagem(imagem);
        pessoa.setEnderecos(enderecos);
        pessoa.setPerfils(perfils);

        final int prime = 31;
        int result = 1;
        result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((enderecos == null) ? 0 : enderecos.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((imagem == null) ? 0 : imagem.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((perfils == null) ? 0 : perfils.hashCode());
        result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());

        int resultado = pessoa.hashCode();
        Assert.assertEquals(resultado, result);
    }

    
}
