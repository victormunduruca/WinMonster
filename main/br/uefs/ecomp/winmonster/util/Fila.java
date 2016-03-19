package br.uefs.ecomp.winmonster.util;

public class Fila implements IFila{
	/**
	 * @author Diego Lourenço
	 */

	private Celula primeiro;//Referência para o inicio da fila.
	private Celula ultimo;//Referência para o final da fila.
	private int tamanhoFila = 0;//Variável que armazena o tamanho total da fila.

	public Fila(){
		//Inicializo a fila com suas referências nulas, pois a fila está vazia inicialmente.
		primeiro = null;
		ultimo = null;
	}

	/**
	 * Método que verifica se a fila está ou não vázia.
	 * @return false ou true
	 */
	@Override
	public boolean estaVazia() {
		//Se "primeiro" for nulo significa que a fila está vazia.
		if(primeiro == null)
			return true;
		return false;
	}

	/**
	 * Método que retorna o tamanho da fila.
	 * @return int
	 */
	@Override
	public int obterTamanho() {
		return tamanhoFila;//Retorno o tamanho da fila.
	}

	/**
	 * Método que insere um objeto no final da fila.
	 * @param o
	 */
	@Override
	public void inserirFinal(Object o) {
		//Crio uma nova célula e insiro o objeto recebido.
		Celula novo = new Celula();
		novo.setObjeto(o);
		//Verifico se a fila está vazia.
		if(estaVazia() == true){
			primeiro = novo;
			ultimo = primeiro;
		}
		else{
			//Se não estiver vázia o novo objeto é inserido no final da fila.
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
			//Se a fila estiver vázia, o primeiro elemento também é o último.
			primeiro = novo;
			ultimo = novo;
		}
		else{
			Celula celulaAtual = primeiro;
			Celula anterior = primeiro;
			Comparable<Object> atual = null; //Variável do tipo da interface.
			while(celulaAtual != null){
				atual =  (Comparable<Object>) celulaAtual.getObjeto();
				/*A variável atual referencia o objeto da celulaAtual e compara o valor de frequência do
				 * objeto atual com o objeto que eu quero inserir na fila. Essa comparação é feita num
				 * método especifico na classe Dados.
				 */
				if(atual.compareTo(o) == 1){
					if(celulaAtual == primeiro){
						/*Se a frequencia do novo objeto que quero inserir for menor que a do objeto que está no 
						 * inicio da fila, então o primeiro da fila passa a ser esse novo objeto.
						 */
						novo.setProximo(celulaAtual);
						primeiro = novo;
					}
					else{
						/*Se a frequencia do novo objeto que quero inserir estiver no intervalo entre 
						 * dois objetos da fila, então faço o anterior referenciar o novo objeto e o novo
						 * objeto referencia o próximo.
						 */
						anterior.setProximo(novo);
						novo.setProximo(celulaAtual);
					}
					break;
					/*Esse break serve para impedir que o laço continue rodando mesmo após fazer a inserção.
					 * Pois se continuar, só irá parar quando celulaAtual for null, e então irá entrar na 
					 * condição abaixo que depende que a celulaAtual seja nula.
					 */
				}
				anterior  = celulaAtual;
				celulaAtual = celulaAtual.getProximo();
				if(celulaAtual == null){
					/*Se a celulaAtual for nula significa que não existe objeto na fila que tenha frequencia maior
					 * que a do novo objeto que quero inserir, então insiro ele no final da fila.
					 */
					anterior.setProximo(novo);
					ultimo = novo;
				}
			}
		}
		tamanhoFila++;
	}

	/**
	 * Método que remove um objeto do início da fila.
	 * @return Object
	 */
	@Override
	public Object removerInicio() {
		//Crio uma referência auxiliar para guardar o objeto removido.
		Celula aux = null;
		//Verifico se a fila está vazia.
		if(estaVazia()){
			return null;
		}
		else if(primeiro == ultimo){
			/*Se só existir um objeto na fila ele será o primeiro e o último simultaneamente.
			 * Então basta fazer suas referências serem nulas.*/
			aux = primeiro;
			primeiro = null;
			ultimo = null;
		}
		else{
			/*Se existir mais de um objeto na fila basta ir removendo o primeiro da fila.
			 * Fazendo "primeiro" referênciar o seu próximo (que estaria atrás dele).*/
			aux = primeiro;//a referência auxiliar guarda o primeiro objeto que foi removido.
			primeiro = primeiro.getProximo();
		}
		tamanhoFila--;//Decremento o tamanho da fila.
		return aux.getObjeto();//Retorno o objeto que foi removido.
	}

	/**
	 * Método que retorna o objeto do início da fila.
	 * @return Object
	 */
	@Override
	public Object recuperarInicio() {
		//Se "primeiro" for nulo significa que a fila está vazia.
		if(estaVazia())
			return null;
		else
			//Senão retorno o primeiro objeto da fila.
			return primeiro.getObjeto();
	}

	/**
	 * Método que retorna a referência da fila.
	 * @return Iterador
	 */
	@Override
	public Iterador iterador() {
		//Crio uma referência do tipo "MeuIterador" passando a referência da fila.
		MeuIterador iterador = new MeuIterador(primeiro);
		return iterador;//Retorno a referência da fila através do iterador.
	}
}
