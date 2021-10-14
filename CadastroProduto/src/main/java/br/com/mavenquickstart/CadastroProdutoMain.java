package br.com.mavenquickstart;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import br.com.avaliacao.crudhibernate.ProdutoJpaDAO;
import br.com.avaliacao.model.Produto;

/**
 * Hello world!
 *
 */
public class CadastroProdutoMain 
{
    public static void main( String[] args )
    { 	 
    	 
    	int opcaoEscolhida = Integer.MIN_VALUE;
    	 
    	while (opcaoEscolhida!=0) 
    	{
    	 Scanner scanner = new Scanner(System.in); 
    	 
    	 System.out.print("1- Cadastrar três produtos\n"
    	 		+ "2- Atualizar segundo produto\n"
    	 		+ "3- Excluir o segundo produto\n"
    	 		+ "0- Finalizar aplicação\n");    	     	 
    	   	 
    	 
    	 opcaoEscolhida = scanner.nextInt();  
    	 System.out.println("Opcao Escolhida: " + opcaoEscolhida);   	 
    	 	
    	 
    	 switch(opcaoEscolhida) 
    	 {
    	  case 1:
    	    new CadastroProdutoMain().adicionarTresProdutos();
    	    break;
    	  case 2:
    		new CadastroProdutoMain().atualizarSegundoProduto();
    	    break;
    	  case 3:
    		new CadastroProdutoMain().excluirSegundoProduto();
      	    break;
    	  case 0:
      		new CadastroProdutoMain().sair();
        	break;
    	  default:
    	}
    	 
    	 scanner.close();    	 
    	}
    }

	private void sair() {
		System.out.println("Aplicação finalizada.");  
		
	}

	private void adicionarTresProdutos() 
    {    	
    	 for (int j = 0; j < 3; j++) 
    	 {    		 
    		 Produto produto = new Produto();
    		 
    		 produto.setNome("Produto " + j);
    		 produto.setDescricao("Descricao " + j);
    		 produto.setPreco(new BigDecimal(j));
    		 produto.setDataCriacao(LocalDate.now());
    		 produto.setDataAlteracao(LocalDate.now());
    		 produto.setQuantidade(j);    		 
    		 
    		 ProdutoJpaDAO.getInstance().persist(produto);    		 
		}   
    	 
    	System.out.println("Produto inserido.");  	 
    }
	
	private void atualizarSegundoProduto() {
		
		ProdutoJpaDAO produtoJpaDAO = ProdutoJpaDAO.getInstance();
		
		Produto segundoProduto = produtoJpaDAO.getById(2);
		segundoProduto.setDataAlteracao(LocalDate.now());
		
		produtoJpaDAO.merge(segundoProduto);
		
		System.out.println("Segundo produto atualizado."); 		
	}
	
	private void excluirSegundoProduto() 
	{
		
		ProdutoJpaDAO.getInstance().removeById(2);
		System.out.println("Segundo produto removido.");  
	}
	
}
