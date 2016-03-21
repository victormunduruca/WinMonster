package br.uefs.ecomp.winmonster.util;

public class Lista implements ILista {
	
	/**@author Diego Lourenço*/
	
	private Celula primeiro; // Referência para o incio da lista
	private int tamanhoLista = 0; // armazena o tamnho da lista encadeada
	
	public Lista(){
		primeiro = null;//Construo todo objeto tipo Lista com uma referencia para null
	}
	/**Método que verifica se a lista está vazia.
	 * @return boolean*/
	@Override
	public boolean estaVazia() {
		if(primeiro == null)//Verifico se a lista está vazia
			return true;
		return false;
	}
	/**Método que retorna o tamanho atual da lista.
	 * @return int*/
	@Override
	public int obterTamanho() {
		return tamanhoLista;// retorno o tamanho atual da lista
	}
	/**Método que insere elementos no incio da lista.
	 * @param o
	 * @return void*/
	@Override
	public void inserirInicio(Object o) {
		Celula novo = new Celula();//Crio um objeto do tipo Celula
		novo.setObjeto(o);//Insiro na Celula novo o objeto "o" recebido por parametro
		if(primeiro == null){//Verifico se a lista está vazia
			primeiro = novo;//Faço primeiro referenciar o novo nó da lista
			tamanhoLista++;//Incremento o tamanho da lista
		}
		else{		
			novo.setProximo(primeiro);//novo passa a referenciar o primeiro nó da lista
			primeiro = novo;// E o primeiro passa a referenciar o novo
			tamanhoLista++;//Incremento o tamanho da lista
		}
		
	}
	/**Método que insere os elementos no final da lista.
	 * @param o
	 * @return void*/
	@Override
	public void inserirFinal(Object o) {
		Celula novo = new Celula();//Crio um objeto do tipo Celula
		novo.setObjeto(o);//Insiro na Celula novo o objeto "o" recebido por parametro
		Celula ultimo = primeiro;// Crio uma referência que incialmente referencia o primeiro elemento da lista
		if(primeiro == null)//Verifico se a lista está vazia
		{
			primeiro = novo;//Faço o primeiro referenciar o novo nó
			tamanhoLista++;//Incremento o tamanho da lista
		}
		else{
			while(ultimo.getProximo() != null)//Percorro a lista até que a proxima referência do ultimo seja null 
				ultimo  = ultimo.getProximo();//Incremento o ultimo com a próxima referencia
			ultimo.setProximo(novo);//O ultimo passa a referenciar o novo lemento
			novo.setProximo(null);//O novo passa a referenciar null
			tamanhoLista++;//Incremento o tamanho da lista
		}
	}
	/**Método que remove o primeiro elemento da lista.
	 * @return Object*/
	@Override
	public Object removerInicio() {
		Celula aux = primeiro;//Crio uma referência auxiliar que inicialmente referencia o primeiro elemento da lista
		if(primeiro == null)//Verifico se a lista está vazia
			return null;
		else{
			primeiro = primeiro.getProximo();//O primeiro passa a referenciar o próximo
			tamanhoLista--;//Decremento o tamanho da lista
			return aux.getObjeto();// retorno o objeto removido
		}
	}
	/**Método que remove o ultimo elemnto da lista.
	 * @return Object*/
	@Override
	public Object removerFinal() {
		//Crio duas referências para o ultimo e penultimo elemento da lista
		Celula ultimo = primeiro;
		Celula penultimo = primeiro;
		if(primeiro == null)//Verifico se a lista está vazia
			return null;
		else{
			while(ultimo.getProximo() != null){//Percorro a lista até que a próxima referência de ultimo seja null
				penultimo = ultimo;//Faço penultimo referenciar o ultimo
				ultimo = ultimo.getProximo();//Incremento o ultimo com a próxima referencia
			}
			penultimo.setProximo(null);//O penultimo elemento passa a referenciar null
			tamanhoLista--;//Decremento o tamanho da lista
			return ultimo.getObjeto();//Retorno o objeto removido
		}
	}
	/**Método que remove um elemento específico dalista.
	 * @param index
	 * @return Object*/
	@Override
	public Object remover(int index) {
		if(primeiro != null){//Verifico se a lista está vazia
			Celula aux = primeiro;//Crio uma referência auxiliar que inicialmente referencia o primeiro elemento da lista
			Object objeto;//Crio umareferência para um objeto
			if(index == 0){//Verifico se a posição é a primeira
				objeto = removerInicio();//objeto referencia o objeto quem foi removido no inicio
				tamanhoLista--;//Decremento o tamnho da lista
			}
			else{
				int cont = 0;
				Celula antes = primeiro;//Referência para a celula anterior da que será excluída
				while(cont != index){//Percorro a lista até que o contador seja igual a posição recebida
					antes = aux;// antes recebe a referência da auxiliar
					aux = aux.getProximo();//Incremento aux com a próxima referencia
					cont++;// incremento o contador
				}
				antes.setProximo(aux.getProximo());//antes passa a referenciar a próxima célual após a auxiliar
				objeto = aux.getObjeto();//objeto referencia o objeto a ser removido
				tamanhoLista--;//Decremento o tamnho da lista
			}
			return objeto;//Retorno o objeto removido
		}
		return null;
	}
	/**Método que retorna um objeto desejado.
	 * @param index
	 * @return Object*/
	@Override
	public Object recuperar(int index) {
		if(primeiro != null){//Verifico se a lista está vazia
			Celula aux = primeiro;//Referencio o primeiro elemento da lsita
			int cont = 0;
			while(cont != index){//Percorro a lista
				aux = aux.getProximo();//Incremento com a próxima referência
				cont++;
			}
			return aux.getObjeto();//Retorno o objeto encontrado
		}
		return null;
	}
	/**Método que cria e retorna um Iterador.
	 * @return Iterador*/
	@Override
	public Iterador iterador() {
		MeuIterador iterador = new MeuIterador(primeiro);
		//crio um objeto iterador e passo para seu construtor a referência no incio da lista
		return iterador;//Retorno a referência do objeto iterador
	}
	/**Método que retona a posição d eum objeto específico.
	 * @param objeto
	 * @return int*/
	public int posicaoObjeto(Object objeto){
		Celula aux = primeiro; //Referencio o primeiro elemento
		int posicao = 1;
		while(aux.getProximo() != null){//Percorro a lista
			aux = aux.getProximo();//Incremento com a próxima referência
			if(aux.getObjeto().equals(objeto))//Verifico se o objeto atua é igual ao objeto recebido
				return posicao;// Retorno a posição do objeto
			posicao++;
		}
		return 0;
	}
	/**Método que realiza a ordenação da lista.
	 * @param index1, index2, qtd1, qtd2
	 * @return boolean*/
	public boolean bubbleSort(int index1, int index2, Number qtd1, Number qtd2) {
		//Crio duas referências do tipo Celula e que inicialmente referencial o primeiro elemento da lista
		Celula celula1 = primeiro;
		Celula celula2 = primeiro;
		int contador;
		Object objeto;
		
		for(contador = 0; contador <index1; contador++)//Percorro a lista até a posição do index1
			celula1 = celula1.getProximo();
		for(contador = 0; contador <index2; contador++)//Percorro a lista até a posição do index2
			celula2 = celula2.getProximo();
		
		if((double) qtd2 > (double) qtd1){//Verifico se a quantidade da próxima celula é maior que a da célula atual
			objeto = celula1.getObjeto();//Guardo a referência do objeto da celula1
			celula1.setObjeto(celula2.getObjeto());//Faço a celula1 referenciar o objeto da celula2
			celula2.setObjeto(objeto);//Faço a celula2 referenciar o objeto que antes estava na celula1
			return true;
		}
		return false;
	}
}
