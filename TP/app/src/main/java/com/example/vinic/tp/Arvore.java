package com.example.vinic.tp;
import com.example.vinic.tp.No;

import java.util.ArrayList;
import java.util.Map;
/**
 * @author Vinicius Fracisco da Silva
 */
public class Arvore{
    private No raiz; // raiz da estrutura
    private static ArrayList<Object> estados = new ArrayList<>();
    private static ArrayList<Object> cidades = new ArrayList<>();

    /**
     *
     * @param estados
     * @param cidades
     */
    public Arvore(Object[] estados, Object[] cidades){
        this.raiz = new No(); // inicialização da raiz como nó cabeça
        inserirEstado(estados); // inserindo todos os estados na árvore
        inserirCidade(cidades,estados); // inserindo todas as cidades na árvore
    }// End Arvore()

    /**
     *
     *
     */
    public Arvore(){
        this.raiz = new No(); // inicialização da raiz como nó cabeça
    }// End Arvore()

    /**
     * Inserção de todos os estados
     * federativos na estrutura da árvore
     *
     *
     * @return
     */
    private void inserirEstado(Object[] estados){
        assert estados != null;
        for(Object estado : estados){
            this.raiz.filho.put(estado,new No(estado));
        }// End for
    }// End inserir()

    /**
     *
     * @param cidades
     * @param estados
     * @throws Exception
     */
    private void inserirCidade(Object[] cidades,Object[] estados){
        int i = 0;
        for(Object cidade : cidades){
            this.raiz.filho.get(estados[i]).filho.put(cidade,new No(cidade)); i++;
        }// End for
    }// End inserirCidade()

    /**
     *
     * @param uf
     */
    public boolean removerEstado(Object uf){
        return this.raiz.filho.remove(uf) != null ? true : false;
    }// End  removerEstado()

    /**
     *
     * @param uf
     * @param cidade
     */
    public boolean removerCidade(Object uf, Object cidade){
        return this.raiz.filho.get(uf).filho.remove(uf) != null ? true : false;
    }// End removerCidade()

    /**
     *
     */
    public void mostrar(){
        No no = this.raiz;
        int i,j;
        i = j = 1;
        System.out.println("[ =================== ESTADOS E CAPITAIS =================== ]\n");
        for(Map.Entry<Object,No> entry : no.filho.entrySet()){
            if(entry != null && entry.getKey() != null && entry.getValue() != null){
                System.out.println("    " + i + "° ESTADO: " + entry.getValue().object + " / CHAVE ESTADO: " + entry.getKey() + "\n");
                for(Map.Entry<Object,No> map_entry : no.filho.get(entry.getKey()).filho.entrySet()){
                    if(map_entry != null && map_entry.getKey() != null && map_entry.getValue() != null){
                        System.out.println("    \t" + j + "° CIDADE: " + map_entry.getValue().object + " CHAVE CIDADE: " + map_entry.getKey()); j++;
                    }// End if
                }// End for
            }// End if
        }// End for
        System.out.println("[ ========================================================== ]\n");
    }// End mostrar()

    /**
     *
     * @return
     */
    public Object[] getEstados(){
        return estados.toArray();
    }// End getEstados()

    /**
     *
     * @return
     */
    public Object[] getCidades(){
        No no = this.raiz;
        for(Map.Entry<Object,No> entry : no.filho.entrySet()){
            if(entry != null && entry.getKey() != null && entry.getValue() != null){
                estados.add(entry.getKey());
                for(Map.Entry<Object,No> map_entry : no.filho.get(entry.getKey()).filho.entrySet()){
                    if(map_entry != null && map_entry.getKey() != null && map_entry.getValue() != null){
                        cidades.add(map_entry.getKey());
                    }// End if
                }// End for
            }// End if
        }// End for
        return cidades.toArray();
    }// End getCidades()
}// End class Arvore

