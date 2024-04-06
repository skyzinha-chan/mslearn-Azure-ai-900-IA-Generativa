package jogodavelha;

import java.util.Scanner;

public class JogoDaVelha_Copilot {
	private char[][] tabuleiro;
	private char jogadorAtual;
	private boolean jogoTerminado;

	public JogoDaVelha_Copilot() {
		tabuleiro = new char[3][3];
		jogadorAtual = 'X';
		jogoTerminado = false;
		inicializarTabuleiro();
	}

	public void inicializarTabuleiro() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tabuleiro[i][j] = '-';
			}
		}
	}

	public void imprimirTabuleiro() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(tabuleiro[i][j] + " ");
			}
			System.out.println();
		}
	}

	public boolean jogar(int linha, int coluna) {
		if (linha < 1 || coluna < 1 || linha > 3 || coluna > 3 || tabuleiro[linha - 1][coluna - 1] != '-') {
			return false;
		} else {
			tabuleiro[linha - 1][coluna - 1] = jogadorAtual;
			verificarVencedor();
			jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
			return true;
		}
	}

	public void verificarVencedor() {
		// Verificar linhas, colunas e diagonais
		for (int i = 0; i < 3; i++) {
			if (tabuleiro[i][0] != '-' && tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {
				jogoTerminado = true;
				break;
			}
			if (tabuleiro[0][i] != '-' && tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]) {
				jogoTerminado = true;
				break;
			}
		}
		if (!jogoTerminado) {
			if (tabuleiro[0][0] != '-' && tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
				jogoTerminado = true;
			} else if (tabuleiro[0][2] != '-' && tabuleiro[0][2] == tabuleiro[1][1]
					&& tabuleiro[1][1] == tabuleiro[2][0]) {
				jogoTerminado = true;
			}
		}
	}

	public boolean isJogoTerminado() {
		return jogoTerminado;
	}

	public static void main(String[] args) {
		JogoDaVelha_Copilot jogo = new JogoDaVelha_Copilot();
		Scanner scanner = new Scanner(System.in);
		int linha, coluna;

		System.out.println("Jogo da Velha!");
		jogo.imprimirTabuleiro();

		while (!jogo.isJogoTerminado()) {
			System.out.println("Jogador " + jogo.jogadorAtual + ", escolha linha e coluna (1, 2 ou 3):");
			linha = scanner.nextInt();
			coluna = scanner.nextInt();

			if (!jogo.jogar(linha, coluna)) {
				System.out.println("Posição inválida, tente novamente.");
			}

			jogo.imprimirTabuleiro();
		}
		char vencedor = (jogo.jogadorAtual == 'X') ? 'O' : 'X';
		System.out.println("Parabéns, Jogador " + vencedor + "! Você ganhou!");

		scanner.close();
	}
}