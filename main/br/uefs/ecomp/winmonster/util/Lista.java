package br.uefs.ecomp.winmonster.util;

public class Lista implements ILista {
	
	/**@author Diego Louren�o*/
	
	private Celula primeiro; // Refer�ncia para o incio da lista
	
	private int tamanhoLista = 0; // armazena o tamnho da lista encadeada
	
	public Lista(){
		primeiro = null;//Construo todo objeto tipo Lista com uma referencia para null
	}
	/**M�todo que verifica se a lista est� vazia.
	 * @return boolean*/
	@Override
	public boolean estaVazia() {
		if(primeiro == null)//Verifico se a lista est� vazia
			return true;
		return false;
	}
	/**M�todo que retorna o tamanho atual da lista.
	 * @return int*/
	@Override
	public int obterTamanho() {
		return tamanhoLista;// retorno o tamanho atual da lista
	}
	/**M�todo que insere elementos no incio da lista.
	 * @param o
	 * @return void*/
	@Override
	public void inserirInicio(Object o) {
		Celula novo = new Celula();//Crio um objeto do tipo Celula
		novo.setObjeto(o);//Insiro na Celula novo o objeto "o" recebido por parametro
		if(primeiro == null){//Verifico se a lista est� vazia
			primeiro = novo;//Fa�o primeiro referenciar o novo n� da lista
			tamanhoLista++;//Incremento o tamanho da lista
		}
		else{		
			novo.setProximo(primeiro);//novo passa a referenciar o primeiro n� da lista
			primeiro = novo;// E o primeiro passa a referenciar o novo
			tamanhoLista++;//Incremento o tamanho da lista
		}
		
	}
	/**M�todo que insere os elementos no final da lista.
	 * @param o
	 * @return void*/
	@Override
	public void inserirFinal(Object o) {
		Celula novo = new Celula();//Crio um objeto do tipo Celula
		novo.setObjeto(o);//Insiro na Celula novo o objeto "o" recebido por parametro
		Celula ultimo = primeiro;// Crio uma refer�ncia que incialmente referencia o primeiro elemento da lista
		if(primeiro == null)//Verifico se a lista est� vazia
		{
			primeiro = novo;//Fa�o o primeiro referenciar o novo n�
			tamanhoLista++;//Incremento o tamanho da lista
		}
		else{
			while(ultimo.getProximo() != null)//Percorro a lista at� que a proxima refer�ncia do ultimo seja null 
				ultimo  = ultimo.getProximo();//Incremento o ultimo com a pr�xima referencia
			ultimo.setProximo(novo);//O ultimo passa a referenciar o novo lemento
			novo.setProximo(null);//O novo passa a referenciar null
			tamanhoLista++;//Incremento o tamanho da lista
		}
	}
	/**M�todo que remove o primeiro elemento da lista.
	 * @return Object*/
	@Override
	public Object removerInicio() {
		Celula aux = primeiro;//Crio uma refer�ncia auxiliar que inicialmente referencia o primeiro elemento da lista
		if(primeiro == null)//Verifico se a lista est� vazia
			return null;
		else{
			primeiro = primeiro.getProximo();//O primeiro passa a referenciar o pr�ximo
			tamanhoLista--;//Decremento o tamanho da lista
			return aux.getObjeto();// retorno o objeto removido
		}
	}
	/**M�todo que remove o ultimo elemnto da lista.
	 * @return Object*/
	@Override
	public Object removerFinal() {
		//Crio duas refer�ncias para o ultimo e penultimo elemento da lista
		Celula ultimo = primeiro;
		Celula penultimo = primeiro;
		if(primeiro == null)//Verifico se a lista est� vazia
			return null;
		else{
			while(ultimo.getProximo() != null){//Percorro a lista at� que a pr�xima refer�ncia de ultimo seja null
				penultimo = ultimo;//Fa�o penultimo referenciar o ultimo
				ultimo = ultimo.getProximo();//Incremento o ultimo com a pr�xima referencia
			}
			penultimo.setProximo(null);//O penultimo elemento passa a referenciar null
			tamanhoLista--;//Decremento o tamanho da lista
			return ultimo.getObjeto();//Retorno o objeto removido
		}
	}
	/**M�todo que remove um elemento espec�fico dalista.
	 * @param index
	 * @return Object*/
	@Override
	public Object remover(int index) {
		if(primeiro != null){//Verifico se a lista est� vazia
			Celula aux = primeiro;//Crio uma refer�ncia auxiliar que inicialmente referencia o primeiro elemento da lista
			Object objeto;//Crio umarefer�ncia para um objeto
			if(index == 0){//Verifico se a posi��o � a primeira
				objeto = removerInicio();//objeto referencia o objeto quem foi removido no inicio
				tamanhoLista--;//Decremento o tamnho da lista
			}
			else{
				int cont = 0;
				Celula antes = primeiro;//Refer�ncia para a celula anterior da que ser� exclu�da
				while(cont != index){//Percorro a lista at� que o contador seja igual a posi��o recebida
					antes = aux;// antes recebe a refer�ncia da auxiliar
					aux = aux.getProximo();//Incremento aux com a pr�xima referencia
					cont++;// incremento o contador
				}
				antes.setProximo(aux.getProximo());//antes passa a referenciar a pr�xima c�lual ap�s a auxiliar
				objeto = aux.getObjeto();//objeto referencia o objeto a ser removido
				tamanhoLista--;//Decremento o tamnho da lista
			}
			return objeto;//Retorno o objeto removido
		}
		return null;
	}
	/**M�todo que retorna um objeto desejado.
	 * @param index
	 * @return Object*/
	@Override
	public Object recuperar(int index) {
		if(primeiro != null){//Verifico se a lista est� vazia
			Celula aux = primeiro;//Referencio o primeiro elemento da lsita
			int cont = 0;
			while(cont != index){//Percorro a lista
				aux = aux.getProximo();//Incremento com a pr�xima refer�ncia
				cont++;
			}
			return aux.getObjeto();//Retorno o objeto encontrado
		}
		return null;
	}
	/**M�todo que cria e retorna um Iterador.
	 * @return Iterador*/
	@Override
	public Iterador iterador() {
		MeuIterador iterador = new MeuIterador(primeiro);
		//crio um objeto iterador e passo para seu construtor a refer�ncia no incio da lista
		return iterador;//Retorno a refer�ncia do objeto iterador
	}
	/**M�todo que retona a posi��o d eum objeto espec�fico.
	 * @param objeto
	 * @return int*/
	public int posicaoObjeto(Object objeto){
		Celula aux = primeiro; //Referencio o primeiro elemento
		int posicao = 1;
		while(aux.getProximo() != null){//Percorro a lista
			aux = aux.getProximo();//Incremento com a pr�xima refer�ncia
			if(aux.getObjeto().equals(objeto))//Verifico se o objeto atua � igual ao objeto recebido
				return posicao;// Retorno a posi��o do objeto
			posicao++;
		}
		return 0;
	}
	/**M�todo que realiza a ordena��o da lista.
	 * @param index1, index2, qtd1, qtd2
	 * @return boolean*/
	public boolean bubbleSort(int index1, int index2, Number qtd1, Number qtd2) {
		//Crio duas refer�ncias do tipo Celula e que inicialmente referencial o primeiro elemento da lista
		Celula celula1 = primeiro;
		Celula celula2 = primeiro;
		int contador;
		Object objeto;
		
		for(contador = 0; contador <index1; contador++)//Percorro a lista at� a posi��o do index1
			celula1 = celula1.getProximo();
		for(contador = 0; contador <index2; contador++)//Percorro a lista at� a posi��o do index2
			celula2 = celula2.getProximo();
		
		if((double) qtd2 > (double) qtd1){//Verifico se a quantidade da pr�xima celula � maior que a da c�lula atual
			objeto = celula1.getObjeto();//Guardo a refer�ncia do objeto da celula1
			celula1.setObjeto(celula2.getObjeto());//Fa�o a celula1 referenciar o objeto da celula2
			celula2.setObjeto(objeto);//Fa�o a celula2 referenciar o objeto que antes estava na celula1
			return true;
		}
		return false;
	}
	/**
	 * @return the primeiro
	 */
	public Celula getPrimeiro() {
		return primeiro;
	}
}
