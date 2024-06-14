package main;

import java.sql.SQLException;

public class GetRowCount {
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
