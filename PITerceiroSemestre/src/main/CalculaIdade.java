package main;

import java.sql.Date;
import java.time.LocalDate;

public class CalculaIdade {
	public static int calculaIdade(Date dataNascimentoDate) {
		int diaNascimento = 0, mesNascimento = 0, anoNascimento = 0, idade = 0;
		int diaAtual = 0, mesAtual = 0, anoAtual = 0;
		String dataNascimento = dataNascimentoDate.toString();
		
		diaNascimento = Integer.parseInt(dataNascimento.substring(8, 10));
		mesNascimento = Integer.parseInt(dataNascimento.substring(5, 7));
		anoNascimento = Integer.parseInt(dataNascimento.substring(0, 4));
		
		// Cria um formatador de data para o formato definido (dd/MM/yyyy)
		// DateTimeFormatter formatoDataBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		// Retorna a data de nascimento no formato (dd/MM/yyyy)
		// LocalDate.of(anoNascimento, mesNascimento, diaNascimento).format(formatoDataBr);
		
		// LocalDate.now(); // Retorna a data atual no formato yyyy-MM-dd
		// System.out.println(LocalDate.now().minusYears(ano).format(formatoDataBr));
		
		diaAtual = Integer.parseInt(LocalDate.now().toString().substring(8, 10));
		mesAtual = Integer.parseInt(LocalDate.now().toString().substring(5, 7));
		anoAtual = Integer.parseInt(LocalDate.now().toString().substring(0, 4));
		
		idade = anoAtual - anoNascimento;
		
		// Verifica se é o mês do aniversário ou se ja passou
		// System.out.println("Meses até o aniversário: "+(mesAtual - mesNascimento) * (-1));
		if ((mesAtual - mesNascimento) >= 0) {
			// System.out.println("Está no mês do aniversário ou ja passou");
			// System.out.println();
			
			// System.out.println("Dias até o aniversário: "+(diaAtual - diaNascimento) * (-1));
			if ((diaAtual - diaNascimento) < 0) {
				// System.out.println("Aniversário ainda não passou");
				idade--;
			} else {
				// System.out.println("Aniversário ja passou");
			}
		} else {
			// System.out.println("Aniversário ainda não passou");
			idade--;
		}
		
		System.out.println("Idade: "+idade);
		
	return idade;
	}
}
