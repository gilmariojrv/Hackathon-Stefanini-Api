package com.stefanini.servico;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.validation.Valid;

import com.stefanini.dao.PessoaDao;
import com.stefanini.exception.NegocioException;
import com.stefanini.model.Endereco;
import com.stefanini.model.Pessoa;

/**
 * 
 * Classe de servico, as regras de negocio devem estar nessa classe
 * 
 * @author joaopedromilhome
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PessoaServico implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaDao dao;

	@Inject
	private PessoaPerfilServico pessoaPerfilServico;
	
	@Inject
	private EnderecoServico enderecoServico;

	/**
	 * Salvar os dados de uma Pessoa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Pessoa salvar(@Valid Pessoa pessoa) {
	
		
	//List<Endereco> enderecos = pessoa.getEnderecos().stream().map(endereco -> endereco).collect(Collectors.toList());
	List<Endereco> enderecos = new ArrayList<>();
	for (Endereco enderecoDaPessoa : pessoa.getEnderecos()) {
		
		enderecos.add(enderecoDaPessoa);
	}
	
	pessoa.getEnderecos().clear();
	pessoa.setImagem(decodeToImage(pessoa.getImagem(),pessoa.getEmail())) ;
	Pessoa pessoaSalva = dao.salvar(pessoa);
	
	
	for (Endereco enderecoSalvo : enderecos) {

	enderecoSalvo.setIdPessoa(pessoaSalva.getId()); 
	
	enderecoServico.salvar(enderecoSalvo);
	
	}
	
	
	
	
	
	
		return pessoaSalva;
	}
	/**
	 * Validando se existe pessoa com email
	 */
	public Boolean validarPessoa(@Valid Pessoa pessoa){
		if(pessoa.getId() != null){
			Optional<Pessoa> encontrar = dao.encontrar(pessoa.getId());
			if(encontrar.get().getEmail().equals(pessoa.getEmail())){
				return Boolean.TRUE;
			}
		}
		Optional<Pessoa> pessoa1 = dao.buscarPessoaPorEmail(pessoa.getEmail());
		return pessoa1.isEmpty();
	}

	/**
	 * Atualizar o dados de uma pessoa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Pessoa atualizar(@Valid Pessoa entity) {
		return dao.atualizar(entity);
	}

	/**
	 * Remover uma pessoa pelo id
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(@Valid Long id) throws NegocioException {
		if(pessoaPerfilServico.buscarPessoaPerfil(id,null).count() == 0){
			dao.remover(id);
			return;
		}
		throw new NegocioException("Não foi possivel remover a pessoa");
	}

	/**
	 * Buscar uma lista de Pessoa
	 */
	public Optional<List<Pessoa>> getList() {
		return dao.getList();
	}

	
	public List<Pessoa> obterPessoaCheia() {
		return dao.getPessoaCheia();
	}

	
	
	
	public String decodeToImage(String imageString, String email ) {
		String url = "C:\\Users\\Torres\\Desktop\\hackaton-stefanini-api\\src\\imagens\\foto"+email+".jpg";
		imageString = imageString.split(",")[1];
        BufferedImage image = null;
        byte[] imageByte;
        try {
     
        	imageByte = Base64.getDecoder().decode(imageString.getBytes());
            
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
           ImageIO.write(image, "JPG", new File(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url ;
    }
	
	
//	public void salvarImg () {
//		
//		
//		ImageIO.write(image, "JPG", new File("/src/imagens"));
//		
//	}
	
	
	
	
	/**
	 * Buscar uma Pessoa pelo ID
	 */
//	@Override
	public Optional<Pessoa> encontrar(Long id) {
		return dao.encontrar(id);
	}

}
