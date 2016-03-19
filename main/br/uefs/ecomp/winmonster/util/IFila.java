package br.uefs.ecomp.winMonster.util;


public interface IFila {
  
    public boolean estaVazia();

    public int obterTamanho();

    public void inserirFinal(Object o);

    public Object removerInicio();

    public Object recuperarInicio();   
    
    public Iterador iterador();
    
    public void inserirPrioridade(Object o);
}
