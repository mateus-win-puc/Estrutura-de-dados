import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Random random_number = new Random(9999);
        hashTable minhaTabela = new hashTable();
        /*ajuste o numero de elementos */
        long comeco = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            int randomNumber = (int) Math.floor(random_number.nextDouble()*(999999999 - 100000000 + 1) + 100000000);
            int index = minhaTabela.hashResto(randomNumber);
            //System.out.println(index);
            //System.out.println(randomNumber);
            minhaTabela.lista[index].inserir(randomNumber);
        }
        long fim = System.nanoTime();
        long duracao = (fim-comeco);
        int fracao = minhaTabela.tamanho/5;
        System.out.println("insercao: " + duracao/1000000+ "ms");
        System.out.println("colisoes: "+ minhaTabela.totalColisoes());
        long comeco_busca = System.nanoTime();
        for (int v=0; v<5; v++){
            if(minhaTabela.lista[v*(fracao-1)].cauda == null){}
            else{minhaTabela.busca(minhaTabela.lista[v*(fracao-1)].cauda.valor);}
        }
        long fim_busca = System.nanoTime();
        long duracao_busca = (fim_busca-comeco_busca);
        System.out.println("busca: " + duracao_busca/1000+ " Ms");
        System.out.println("comparacoes: "+ minhaTabela.totalComparacoes());
    }
}


class hashTable{
    list_dup_enc[] lista;
    int tamanho;
    final double constante;
    hashTable(){
        /*ajuste o tamanho da tabela aqui */
        this.tamanho = 10000000;
        this.lista = new list_dup_enc[tamanho];
        this.constante = 0.6180339887;
        for (int i = 0; i < tamanho; i++) {
            this.lista[i] = new list_dup_enc();
        }
    }
    int hashResto(int input){
        int resultado = input % this.tamanho;
        return resultado;
    }
    int hashMult(int input) {
        return((int)(((input * constante) % 1) * this.tamanho));
    }
    
    int hashDiv(int input){
        return((int)(((input / constante) % 1) * this.tamanho));
    }
    int totalColisoes(){
        int total = 0;
        for (int i = 0; i < tamanho; i++) {
           // System.out.println("colisoes da lista "+ i +": "+ this.lista[i].cont_colisoes);
            total = total + this.lista[i].cont_colisoes;
        }
        return(total);
    }
    int totalComparacoes(){
        int total_comp = 0;
        for (int i = 0; i < tamanho; i++) {
           // System.out.println("colisoes da lista "+ i +": "+ this.lista[i].cont_colisoes);
            total_comp = total_comp + this.lista[i].cont_comparacoes;
        }
        return(total_comp);
    }
    int busca(int entrada){
       int indice = this.hashResto(entrada);
       //System.out.println("------------------------------------");
       //this.lista[indice].imprime_lista();
        return(this.lista[indice].busca_indice(entrada));
    }
}


// --------------------------- LISTA DUPLAMENTE ENCADEADA ------------------------------------

//criamos um Registro, que alem de sua informacao, contem um Registro anterior e posterior
class Registro {
    int valor;
    Registro anterior;
    Registro proximo;
    Registro (int valor){
        this.valor = valor;
        this.anterior = null;
        this.proximo = null;
    }
} 

//criamos a lista duplamente encadeada
class list_dup_enc {
    int cont_colisoes;
    int cont_comparacoes;
    Registro cabeca;
    Registro cauda;
    list_dup_enc (){
        this.cabeca = null;
        this.cauda = null;
        this.cont_colisoes = 0;
        this.cont_comparacoes = 0;
    }
    void inserir (int info){
        if (this.cabeca != null){
            this.cont_colisoes++;
        }
        if(this.verifica_repitido(info) == false){
            Registro RegistrovoRegistro = new Registro (info);
            if(cabeca == null){
                this.cabeca = RegistrovoRegistro;
                this.cauda = RegistrovoRegistro;
            }
            else{
                this.cauda.proximo = RegistrovoRegistro;
                RegistrovoRegistro.anterior = this.cauda;
                this.cauda = RegistrovoRegistro;
            }
        }
    }

    void imprime_lista (){
        Registro i = this.cabeca;
        while(i.proximo != null ){
            System.out.println(i.valor);
            i = i.proximo;
        }
        System.out.println(i.valor);
    }
    boolean verifica_repitido(int info) { 
        Registro atual = this.cabeca; 
        while (atual != null){
            if (atual.valor == info){
                return true;
            }
            atual = atual.proximo;
        }
        return false; 
    }
    int busca_indice(int info){
        Registro atual = this.cabeca; 
        while (atual != null){
            this.cont_comparacoes = this.cont_comparacoes +1;
            if (atual.valor == info){
                //System.out.println("achou");
                return atual.valor;
            }
            atual = atual.proximo;
        }
        return -1; 
    }
}