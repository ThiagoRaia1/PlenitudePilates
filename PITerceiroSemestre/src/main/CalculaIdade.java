package main;

import java.time.LocalDate;

public class CalculaIdade {
	public static int calculaIdade(String dataNascimento) {
		// String dataNascimento = JOptionPane.showInputDialog("Digite a data de nascimento do(a) aluno(a) (dd/mm/aaaa):");
		final String FORMATO_INVALIDO = "Formato inválido";
		@SuppressWarnings("unused")
		int diaNascimento = 0, mesNascimento = 0, anoNascimento = 0, idade = 0;
		int diaAtual = 0, mesAtual = 0, anoAtual = 0;
		
		// Valida se a data digitada esta no formato correto
		if (dataNascimento.length() == 10 && dataNascimento.charAt(2) == '/' && dataNascimento.charAt(5) == '/') {
			try {
				
				diaNascimento = Integer.parseInt(dataNascimento.substring(0, 2));
				mesNascimento = Integer.parseInt(dataNascimento.substring(3, 5));
				anoNascimento = Integer.parseInt(dataNascimento.substring(6, 10));
				
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
				
			} catch(NumberFormatException e) {
				System.out.println(FORMATO_INVALIDO+": letras foram digitadas.");
			}
		} else {
			System.out.println(FORMATO_INVALIDO);
		}
		return idade;
	}
}
