package main;

import java.sql.SQLException;

/**
 * Conta quantos registros existem na tabela informada.
 */
public class GetRowCount {
	/**
	 * Conta quantos registros existem na tabela informada.
	 * @param tabela - Nome da tabela a verificar quantos registros existem. Informe como argumento um getter
	 * da entidade. EX: Aula.getNOME_TABELA() = "Aula"
	 * @return - O numero de registros na tabela.
	 */
	public static int getRowCount(String tabela) {
		String sql = "select count(*) from "+tabela;
		int numeroDeLinhasTabela = 0;
		BD bd = new BD();
		if (bd.getConnection()) {
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.rs = bd.st.executeQuery();
                while (bd.rs.next()) {
                	numeroDeLinhasTabela = bd.rs.getInt(1);
                }
			} catch (SQLException erro) {
				erro.printStackTrace();
			}
		}
		return numeroDeLinhasTabela;
	}
}
