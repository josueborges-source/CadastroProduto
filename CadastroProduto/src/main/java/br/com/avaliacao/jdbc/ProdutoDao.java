package br.com.avaliacao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;

import br.com.avaliacao.model.Produto;

public class ProdutoDao {

	private	Connection conexao;
	
	public	ProdutoDao() {
			this.conexao = new ConnectionFactory().getConnection();
	}	
	
	public void adiciona(Produto produto) 
	{
	 String	sql	=	"insert	into produtos	"	+
					"(nome, descricao, quantidade, preco, dataAlteracao, dataCriacao)"	+
					"values	(?,?,?,?,?,?)";
	
	try	{
		// Prepared Statement para Insercao
		PreparedStatement	stmt	=	conexao.prepareStatement(sql);
		
		// Definicao de valores
		stmt.setString (1, produto.getNome());
		stmt.setString(2, produto.getDescricao());
		stmt.setLong(3, produto.getQuantidade());
		stmt.setBigDecimal( 4, produto.getPreco());
		stmt.setDate(5, Date.valueOf(produto.getDataAlteracao()));
		stmt.setDate(6,Date.valueOf(produto.getDataCriacao()));
		
		//Execução
		stmt.execute();
		stmt.close();
		
}	catch	(SQLException	e)	{
		throw new	RuntimeException(e);
}
}

	
}
