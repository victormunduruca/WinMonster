package br.uefs.ecomp.winMonster.util;

public class Fila implements IFila{
	/**
	 * @author Diego Louren�o
	 */

	private Celula primeiro;//Refer�ncia para o inicio da fila.
	private Celula ultimo;//Refer�ncia para o final da fila.
	private int tamanhoFila = 0;//Vari�vel que armazena o tamanho total da fila.

	public Fila(){
		//Inicializo a fila com suas refer�ncias nulas, pois a fila est� vazia inicialmente.
		primeiro = null;
		ultimo = null;
	}

	/**
	 * M�todo que verifica se a fila est� ou n�o v�zia.
	 * @return false ou true
	 */
	@Override
	public boolean estaVazia() {
		//Se "primeiro" for nulo significa que a fila est� vazia.
		if(primeiro == null)
			return true;
		return false;
	}

	/**
	 * M�todo que retorna o tamanho da fila.
	 * @return int
	 */
	@Override
	public int obterTamanho() {
		return tamanhoFila;//Retorno o tamanho da fila.
	}

	/**
	 * M�todo que insere um objeto no final da fila.
	 * @param o
	 */
	@Override
	public void inserirFinal(Object o) {
		//Crio uma nova c�lula e insiro o objeto recebido.
		Celula novo = new Celula();
		novo.setObjeto(o);
		//Verifico se a fila est� vazia.
		if(estaVazia() == true){
			primeiro = novo;
			ultimo = primeiro;
		}
		else{
			//Se n�o estiver v�zia o novo objeto � inserido no final da fila.
			ultimo.setProximo(novo);
			ultimo = novo;
		}
		tamanhoFila++;
	}


	@SuppressWarnings("unchecked")
	@Override
	public void inserirPrioridade(Object o) {
		Celula novo = new Celula();
		novo.setObjeto(o);
		if(estaVazia()){
			//Se a fila estiver v�zia, o primeiro elemento tamb�m � o �ltimo.
			primeiro = novo;
			ultimo = novo;
		}
		else{
			Celula celulaAtual = primeiro;
			Celula anterior = primeiro;
			Comparable<Object> atual = null; //Vari�vel do tipo da interface.
			while(celulaAtual != null){
				atual =  (Comparable<Object>) celulaAtual.getObjeto();
				/*A vari�vel atual referencia o objeto da celulaAtual e compara o valor de frequ�ncia do
				 * objeto atual com o objeto que eu quero inserir na fila. Essa compara��o � feita num
				 * m�todo especifico na classe Dados.
				 */
				if(atual.compareTo(o) == 1){
					if(celulaAtual == primeiro){
						/*Se a frequencia do novo objeto que quero inserir for menor que a do objeto que est� no 
						 * inicio da fila, ent�o o primeiro da fila passa a ser esse novo objeto.
						 */
						novo.setProximo(celulaAtual);
						primeiro = novo;
					}
					else{
						/*Se a frequencia do novo objeto que quero inserir estiver no intervalo entre 
						 * dois objetos da fila, ent�o fa�o o anterior referenciar o novo objeto e o novo
						 * objeto referencia o pr�ximo.
						 */
						anterior.setProximo(novo);
						novo.setProximo(celulaAtual);
					}
					break;
					/*Esse break serve para impedir que o la�o continue rodando mesmo ap�s fazer a inser��o.
					 * Pois se continuar, s� ir� parar quando celulaAtual for null, e ent�o ir� entrar na 
					 * condi��o abaixo que depende que a celulaAtual seja nula.
					 */
				}
				anterior  = celulaAtual;
				celulaAtual = celulaAtual.getProximo();
				if(celulaAtual == null){
					/*Se a celulaAtual for nula significa que n�o existe objeto na fila que tenha frequencia maior
					 * que a do novo objeto que quero inserir, ent�o insiro ele no final da fila.
					 */
					anterior.setProximo(novo);
					ultimo = novo;
				}
			}
		}
		tamanhoFila++;
	}

	/**
	 * M�todo que remove um objeto do in�cio da fila.
	 * @return Object
	 */
	@Override
	public Object removerInicio() {
		//Crio uma refer�ncia auxiliar para guardar o objeto removido.
		Celula aux = null;
		//Verifico se a fila est� vazia.
		if(estaVazia()){
			return null;
		}
		else if(primeiro == ultimo){
			/*Se s� existir um objeto na fila ele ser� o primeiro e o �ltimo simultaneamente.
			 * Ent�o basta fazer suas refer�ncias serem nulas.*/
			aux = primeiro;
			primeiro = null;
			ultimo = null;
		}
		else{
			/*Se existir mais de um objeto na fila basta ir removendo o primeiro da fila.
			 * Fazendo "primeiro" refer�nciar o seu pr�ximo (que estaria atr�s dele).*/
			aux = primeiro;//a refer�ncia auxiliar guarda o primeiro objeto que foi removido.
			primeiro = primeiro.getProximo();
		}
		tamanhoFila--;//Decremento o tamanho da fila.
		return aux.getObjeto();//Retorno o objeto que foi removido.
	}

	/**
	 * M�todo que retorna o objeto do in�cio da fila.
	 * @return Object
	 */
	@Override
	public Object recuperarInicio() {
		//Se "primeiro" for nulo significa que a fila est� vazia.
		if(estaVazia())
			return null;
		else
			//Sen�o retorno o primeiro objeto da fila.
			return primeiro.getObjeto();
	}

	/**
	 * M�todo que retorna a refer�ncia da fila.
	 * @return Iterador
	 */
	@Override
	public Iterador iterador() {
		//Crio uma refer�ncia do tipo "MeuIterador" passando a refer�ncia da fila.
		MeuIterador iterador = new MeuIterador(primeiro);
		return iterador;//Retorno a refer�ncia da fila atrav�s do iterador.
	}
}
